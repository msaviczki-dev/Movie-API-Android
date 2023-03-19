package com.freecast.thatmovieapp.domain.entities


data class MovieData(
    val id: Int,
    val title: String,
    val poster: String,
    val vote: Float,
    val voteRate: Float
)