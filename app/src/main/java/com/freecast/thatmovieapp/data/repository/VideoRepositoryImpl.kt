package com.freecast.thatmovieapp.data.repository

import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.data.mapper.toVideoData
import com.freecast.thatmovieapp.data.response.data
import com.freecast.thatmovieapp.domain.entities.VideoData
import com.freecast.thatmovieapp.network.api.VideoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoRepositoryImpl(private val api: VideoApi) : VideoRepository {

    override fun getTVVideo(id: Int): Flow<List<VideoData>> = flow {
        emit(api.getTVVideo(tvId = id, key = BuildConfig.API_KEY).data().result.toVideoData())
    }

    override fun getMovieVideo(id: Int): Flow<List<VideoData>> = flow {
        emit(api.getMovieVideo(movieId = id, key = BuildConfig.API_KEY).data().result.toVideoData())
    }
}