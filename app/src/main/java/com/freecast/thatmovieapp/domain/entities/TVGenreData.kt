package com.freecast.thatmovieapp.domain.entities

data class TVGenreData(
    val id: Int, val name: String, var isNotSelected: Boolean = false
)