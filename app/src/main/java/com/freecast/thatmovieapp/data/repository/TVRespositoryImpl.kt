package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.data.mapper.toMapGenre
import com.freecast.thatmovieapp.data.mapper.toMapTV
import com.freecast.thatmovieapp.data.response.data
import com.freecast.thatmovieapp.domain.entities.TVGenreData
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.network.api.TVApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TVRespositoryImpl(private val api: TVApi) : TVRespository {

    override fun getTV(): Flow<List<TVData>> = flow {
        emit(api.getTV(BuildConfig.API_KEY).data().result.toMapTV())
    }

    override fun getGenre(): Flow<List<TVGenreData>> = flow {
        emit(api.getTVGenres(BuildConfig.API_KEY).data().genres.toMapGenre())
    }
}