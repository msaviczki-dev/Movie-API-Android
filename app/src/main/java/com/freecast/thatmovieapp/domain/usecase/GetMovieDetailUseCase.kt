package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.data.repository.MovieRepository

class GetMovieDetailUseCase(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int) = repository.getMovieDetail(movieId)
}