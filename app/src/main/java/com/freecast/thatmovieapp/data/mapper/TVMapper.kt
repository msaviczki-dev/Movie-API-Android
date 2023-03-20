package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.data.response.TVGenreResponse
import com.freecast.thatmovieapp.data.response.TVResponse
import com.freecast.thatmovieapp.data.response.VideoResponse
import com.freecast.thatmovieapp.domain.entities.TVGenreData
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.domain.entities.VideoData

private const val DEFAULT_ID = -1
private const val DEFAULT_NAME = "All"
private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="

fun List<TVGenreResponse.GenreDataResponse>?.toMapGenre(): List<TVGenreData> {
    val list = mutableListOf<TVGenreData>()
    list.add(TVGenreData(id = DEFAULT_ID, name = DEFAULT_NAME))
    this?.let { responseList ->
        responseList.map { response ->
            list.add(
                TVGenreData(
                    id = response.id ?: 0, name = response.name.orEmpty(), isNotSelected = true
                )
            )
        }
    }
    return list
}

fun List<TVResponse.TVDataResponse>?.toMapTV(): List<TVData> {
    val list = mutableListOf<TVData>()
    this?.let { responseList ->
        responseList.map { response ->
            list.add(
                TVData(
                    id = response.id ?: 0,
                    title = response.title.orEmpty(),
                    poster = response.poster.orEmpty(),
                    voteRate = (response.vote ?: 0.0F) / 2,
                    vote = response.vote ?: 0.0F,
                    genresIds = response.genreIds ?: listOf()
                )
            )
        }
    }
    return list
}

fun List<VideoResponse.VideoDataResponse>?.toVideoData(): List<VideoData> {
    val list = mutableListOf<VideoData>()
    this?.let { responseList ->
        responseList.map { response ->
            list.add(VideoData(videoKey = YOUTUBE_BASE_URL + response.videoKey.orEmpty()))
        }
    }
    return list
}