package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.data.mapper.toMap
import com.freecast.thatmovieapp.data.response.data
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.domain.entities.MovieDetailData
import com.freecast.thatmovieapp.network.api.MoviesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val api: MoviesApi) : MovieRepository {

    override fun getMovies(): Flow<List<MovieData>> =
        flow { emit(api.getMovies(BuildConfig.API_KEY).data().result.toMap()) }

    override fun getMovieDetail(movieId: Int): Flow<MovieDetailData> = flow {
        emit(api.getMovieDetail(movieId, BuildConfig.API_KEY).data().toMap())
    }
}