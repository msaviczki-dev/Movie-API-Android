package com.freecast.thatmovieapp.data

import com.freecast.thatmovieapp.data.response.*
import com.freecast.thatmovieapp.domain.entities.*

val MOVIE_RESPONSE_NULL = MovieResponse(
    result = null
)

val TV_RESPONSE_NULL = TVResponse(
    result = null
)

val TV_GENRE_NULL = TVGenreResponse(
    genres = null
)

val VIDEO_RESPONSE_NULL = VideoResponse(
    result = null
)

val TV_GENRE_RESPONSE = TVGenreResponse(
    genres = listOf(
        TVGenreResponse.GenreDataResponse(
            id = 20, name = "kids"
        )
    )
)

val VIDEO_RESPONSE = VideoResponse(
    result = listOf(
        VideoResponse.VideoDataResponse(
            videoKey = "V75dMMIW2B4"
        )
    )
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

val DETAIL_RESPONSE = DetailResponse(
    title = "The Godfather",
    name = "The Godfather",
    poster = "https://academiawashington.com.br/wp-content/uploads/2017/10/filmes-para-assistir-sem-legenda-the-godfather.jpg",
    vote = 9.9f,
    overview = "Michel corleone is a fucking man",
    companies = listOf()
)

val TV_RESPONSE = TVResponse(
    result = listOf(
        TVResponse.TVDataResponse(
            id = 0,
            title = "Breaking Bad",
            poster = "https://ogimg.infoglobo.com.br/in/8211328-b01-300/FT1086A/caverna-1.jpg",
            vote = 8.9F,
            genreIds = listOf()
        ), TVResponse.TVDataResponse(
            id = 3,
            title = "Jackie Chan",
            poster = "https://p2.trrsf.com/image/fget/cf/1200/900/middle/images.terra.com/2022/12/09/jackie-chan-qe12e2h4ejap.jpg",
            vote = 9.2F,
            genreIds = listOf()
        )
    )
)

val TV_GENRE_DATA = listOf(
    TVGenreData(
        id = -1, name = "All", isNotSelected = false
    ), TVGenreData(
        id = 20, name = "kids", isNotSelected = true
    )
)

val TV_GENRE_DATA_ONLY_ALL = listOf(
    TVGenreData(
        id = -1, name = "All", isNotSelected = false
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

val DETAIL_DATA = DetailData(
    title = "The Godfather",
    tvTitle = "The Godfather",
    poster = "https://academiawashington.com.br/wp-content/uploads/2017/10/filmes-para-assistir-sem-legenda-the-godfather.jpg",
    vote = 9.9f,
    voteRate = 9.9f / 2,
    overview = "Michel corleone is a fucking man",
    companies = listOf()
)

val VIDEO_DATA = listOf(
    VideoData(
        videoKey = "https://www.youtube.com/watch?v=V75dMMIW2B4"
    )
)

val TV_DATA = listOf(
    TVData(
        id = 0,
        title = "Breaking Bad",
        poster = "https://ogimg.infoglobo.com.br/in/8211328-b01-300/FT1086A/caverna-1.jpg",
        vote = 8.9F,
        voteRate = 8.9F / 2,
        genresIds = listOf()
    ), TVData(
        id = 3,
        title = "Jackie Chan",
        poster = "https://p2.trrsf.com/image/fget/cf/1200/900/middle/images.terra.com/2022/12/09/jackie-chan-qe12e2h4ejap.jpg",
        vote = 9.2F,
        voteRate = 9.2F / 2,
        genresIds = listOf()
    )
)