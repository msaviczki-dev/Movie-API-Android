package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.data.response.DetailResponse
import com.freecast.thatmovieapp.data.response.MovieResponse
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.domain.entities.DetailData

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

fun DetailResponse?.toMap(): DetailData {
    val companies = mutableListOf<DetailData.ProductionCompaniesData>()
    this?.let { response ->
        response.companies?.let { companiesList ->
            companiesList.map { item ->
                companies.add(
                    DetailData.ProductionCompaniesData(
                        logo = item.logo.orEmpty(), name = item.name.orEmpty()
                    )
                )
            }
            return DetailData(
                title = response.title.orEmpty(),
                tvTitle = response.name.orEmpty(),
                poster = response.poster.orEmpty(),
                vote = response.vote ?: 0.0f,
                voteRate = (response.vote ?: 0.0F) / 2,
                overview = response.overview.orEmpty(),
                companies = companies
            )
        }
    } ?: kotlin.run { return DetailData() }
}