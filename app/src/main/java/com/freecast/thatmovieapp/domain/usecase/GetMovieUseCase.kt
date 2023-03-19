package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.MovieRepository

class GetMovieUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getMovies()
}