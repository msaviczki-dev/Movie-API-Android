package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.data.mapper.toMap
import com.freecast.thatmovieapp.data.response.data
import com.freecast.thatmovieapp.domain.entities.DetailData
import com.freecast.thatmovieapp.network.api.DetailApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailRepositoryImpl(private val api: DetailApi) : DetailRepository {

    override fun getTVDetail(movieId: Int): Flow<DetailData> = flow {
        emit(api.getTVDetail(movieId, BuildConfig.API_KEY).data().toMap())
    }

    override fun getMovieDetail(movieId: Int): Flow<DetailData> = flow {
        emit(api.getMovieDetail(movieId, BuildConfig.API_KEY).data().toMap())
    }
}