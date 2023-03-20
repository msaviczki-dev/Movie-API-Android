package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.TVRespository

class GetTVUseCase(private val repository: TVRespository) {
    operator fun invoke() = repository.getTV()
}