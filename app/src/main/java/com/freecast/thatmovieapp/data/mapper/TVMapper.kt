package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.data.response.TVGenreResponse
import com.freecast.thatmovieapp.data.response.TVResponse
import com.freecast.thatmovieapp.domain.entities.TVGenreData
import com.freecast.thatmovieapp.domain.entities.TVData

private const val DEFAULT_ID = -1
private const val DEFAULT_NAME = "All"

fun List<TVGenreResponse.GenreDataResponse>?.toMapGenre(): List<TVGenreData> {
    val list = mutableListOf<TVGenreData>()
    list.add(TVGenreData(id = DEFAULT_ID, name = DEFAULT_NAME))
    this?.let { responseList ->
        responseList.map { response ->
            list.add(
                TVGenreData(
                    id = response.id ?: 0, name = response.name.orEmpty(), isSelected = true
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