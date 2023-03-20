package com.freecast.thatmovieapp.domain.entities

data class TVData(
    val id: Int,
    val title: String,
    val poster: String,
    val vote: Float,
    val voteRate: Float,
    val genresIds: List<Int>
)