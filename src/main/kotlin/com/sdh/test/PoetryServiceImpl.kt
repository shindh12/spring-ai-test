package com.sdh.test

import mu.KotlinLogging
import org.springframework.ai.chat.ChatClient
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.parser.BeanOutputParser
import org.springframework.stereotype.Service

@Service
class PoetryServiceImpl(val chatClient: ChatClient) : PoetryService {
    val log = KotlinLogging.logger {}

    override fun getCatHaiku(): String {
        return chatClient.call(WRITE_ME_HAIKU_ABOUT_CAT)
    }

    override fun getPoetryByGenreAndTheme(genre: String, theme: String): PoetryDto {
        val outputParser = BeanOutputParser(PoetryDto::class.java)
        val promptString = """
                Write me {genre} poetry about {theme}
                {format}
                """.trimIndent()
        val promptTemplate = PromptTemplate(
            promptString,
            mapOf("genre" to genre, "theme" to theme, "format" to outputParser.getFormat())
        )
        val response = chatClient.call(promptTemplate.create())
        log.info { response }
        val generation = response.result
        return outputParser.parse(generation.output.content)
    }

    companion object {
        val WRITE_ME_HAIKU_ABOUT_CAT = """
        Write me Haiku about cat,
        haiku should start with the word cat obligatory
        """.trimIndent()
    }

}