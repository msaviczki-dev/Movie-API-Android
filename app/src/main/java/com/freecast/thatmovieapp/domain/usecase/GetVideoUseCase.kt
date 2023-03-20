package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.VideoRepository
import com.freecast.thatmovieapp.domain.entities.VideoData
import kotlinx.coroutines.flow.Flow

class GetVideoUseCase(private val repository: VideoRepository) {

    operator fun invoke(id: Int, isMovie: Boolean): Flow<List<VideoData>> {
        return if (isMovie) {
            repository.getMovieVideo(id)
        } else {
            repository.getTVVideo(id)
        }
    }
}