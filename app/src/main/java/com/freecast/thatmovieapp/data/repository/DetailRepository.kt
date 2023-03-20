package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.domain.entities.DetailData
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getTVDetail(movieId: Int): Flow<DetailData>
    fun getMovieDetail(movieId: Int): Flow<DetailData>
}