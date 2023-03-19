package com.freecast.thatmovieapp.data

import com.freecast.thatmovieapp.data.response.MovieResponse
import com.freecast.thatmovieapp.domain.entities.MovieData

val MOVIE_RESPONSE_NULL = MovieResponse(
    result = null
)

val MOVIE_RESPONSE = MovieResponse(
    result = listOf(
        MovieResponse.MovieDataResponse(
            id = 0,
            title = "Caverna do Dragão",
            poster = "https://ogimg.infoglobo.com.br/in/8211328-b01-300/FT1086A/caverna-1.jpg",
            vote = 8.9F
        ), MovieResponse.MovieDataResponse(
            id = 3,
            title = "Jackie Chan",
            poster = "https://p2.trrsf.com/image/fget/cf/1200/900/middle/images.terra.com/2022/12/09/jackie-chan-qe12e2h4ejap.jpg",
            vote = 9.2F
        )
    )
)

val MOVIE_DATA = listOf(
    MovieData(
        id = 0,
        title = "Caverna do Dragão",
        poster = "https://ogimg.infoglobo.com.br/in/8211328-b01-300/FT1086A/caverna-1.jpg",
        vote = 8.9F,
        voteRate = 8.9F / 2
    ), MovieData(
        id = 3,
        title = "Jackie Chan",
        poster = "https://p2.trrsf.com/image/fget/cf/1200/900/middle/images.terra.com/2022/12/09/jackie-chan-qe12e2h4ejap.jpg",
        vote = 9.2F,
        voteRate = 9.2F / 2
    )
)