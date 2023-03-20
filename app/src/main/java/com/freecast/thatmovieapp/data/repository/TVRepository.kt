package com.freecast.thatmovieapp.data.repository


import com.freecast.thatmovieapp.domain.entities.TVGenreData
import com.freecast.thatmovieapp.domain.entities.TVData
import kotlinx.coroutines.flow.Flow

interface TVRepository {
    fun getTV(): Flow<List<TVData>>
    fun getGenre(): Flow<List<TVGenreData>>
}