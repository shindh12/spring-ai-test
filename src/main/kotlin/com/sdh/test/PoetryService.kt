package com.sdh.test

interface PoetryService {

    fun getCatHaiku(): String
    fun getPoetryByGenreAndTheme(genre: String, theme: String): PoetryDto
}
