package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.data.response.MovieDetailResponse
import com.freecast.thatmovieapp.data.response.MovieResponse
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.domain.entities.MovieDetailData

fun List<MovieResponse.MovieDataResponse>?.toMap(): List<MovieData> {
    val list = mutableListOf<MovieData>()
    this?.let { responseList ->
        responseList.map { response ->
            list.add(
                MovieData(
                    id = response.id ?: 0,
                    title = response.title.orEmpty(),
                    poster = response.poster.orEmpty(),
                    voteRate = (response.vote ?: 0.0F) / 2,
                    vote = response.vote ?: 0.0F
                )
            )
        }
    }
    return list
}

fun MovieDetailResponse?.toMap(): MovieDetailData {
    val companies = mutableListOf<MovieDetailData.ProductionCompaniesData>()
    this?.let { response ->
        response.companies?.let { companiesList ->
            companiesList.map { item ->
                companies.add(
                    MovieDetailData.ProductionCompaniesData(
                        logo = item.logo.orEmpty(), name = item.name.orEmpty()
                    )
                )
            }
            return MovieDetailData(
                title = response.title.orEmpty(),
                vote = response.vote ?: 0.0f,
                overview = response.overview.orEmpty(),
                companies = companies
            )
        }
    } ?: kotlin.run { return MovieDetailData() }
}