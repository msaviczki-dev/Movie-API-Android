package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.TVRespository

class GetTVGenreUseCase(private val repository: TVRespository) {
    operator fun invoke() = repository.getGenre()
}