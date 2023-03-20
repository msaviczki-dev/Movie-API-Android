package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.domain.entities.VideoData
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getTVVideo(id: Int): Flow<List<VideoData>>
    fun getMovieVideo(id: Int): Flow<List<VideoData>>
}