package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.DetailRepository
import com.freecast.thatmovieapp.domain.entities.DetailData
import kotlinx.coroutines.flow.Flow

class GetDetailUseCase(private val repository: DetailRepository) {
    operator fun invoke(id: Int, isMovie: Boolean): Flow<DetailData> {
        return if (isMovie) {
            repository.getMovieDetail(id)
        } else {
            repository.getTVDetail(id)
        }
    }
}