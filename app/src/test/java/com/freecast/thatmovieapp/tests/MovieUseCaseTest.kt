package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.MOVIE_DATA
import com.freecast.thatmovieapp.data.repository.MovieRepository
import com.freecast.thatmovieapp.domain.usecase.GetMovieUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieUseCaseTest {


    private val repository: MovieRepository = mockk(relaxed = true)

    private var useCase = GetMovieUseCase(repository)

    @Test
    fun `when get success return on getMovies repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getMovies() } returns flowOf(MOVIE_DATA)

        // ACT
        useCase().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(MOVIE_DATA)
            coVerify(exactly = 1) { repository.getMovies() }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getMovies repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getMovies() } returns flow { throw Throwable() }

        // ACT
        useCase().collect()
    }
}