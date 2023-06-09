package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.domain.entities.VideoData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<MovieData>>
}