package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.TVRepository

class GetTVGenreUseCase(private val repository: TVRepository) {
    operator fun invoke() = repository.getGenre()
}