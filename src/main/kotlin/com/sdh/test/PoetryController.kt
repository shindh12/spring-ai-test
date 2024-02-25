package com.sdh.test

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("ai")
class PoetryController(
    val poetryService: PoetryService,
) {

    @GetMapping("/cathaiku")
    fun generateHaiku(): ResponseEntity<String> {
        return ResponseEntity.ok(poetryService.getCatHaiku())
    }

    @GetMapping("/poetry")
    fun generatePoetry(
        @RequestParam("genre") genre: String,
        @RequestParam("theme") theme: String
    ): ResponseEntity<PoetryDto> {
        return ResponseEntity.ok(poetryService.getPoetryByGenreAndTheme(genre, theme))
    }
}