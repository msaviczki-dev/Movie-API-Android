package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.TVRepository

class GetTVUseCase(private val repository: TVRepository) {
    operator fun invoke() = repository.getTV()
}