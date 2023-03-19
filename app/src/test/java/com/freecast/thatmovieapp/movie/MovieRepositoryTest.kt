package com.freecast.thatmovieapp.movie

import com.freecast.thatmovieapp.data.MOVIE_DATA
import com.freecast.thatmovieapp.data.MOVIE_RESPONSE
import com.freecast.thatmovieapp.data.MOVIE_RESPONSE_NULL
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.network.api.MoviesApi
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    private val api: MoviesApi = mockk(relaxed = true)

    private var repository = MovieRepositoryImpl(api)

    @Test
    fun `when get success return on getMovies api`() = runBlocking {

        // ARRANGE
        coEvery { api.getMovies(any()) } returns Response.success(MOVIE_RESPONSE)

        // ACT
        repository.getMovies().collect {

            // ASSERT
            assertThat(it).isEqualTo(MOVIE_DATA)
            coVerify(exactly = 1) { api.getMovies(any()) }
        }
    }

    @Test
    fun `when get success return on getMovies api, but list is null`() = runBlocking {

        // ARRANGE
        coEvery { api.getMovies(any()) } returns Response.success(MOVIE_RESPONSE_NULL)

        // ACT
        repository.getMovies().collect {

            // ASSERT
            assertThat(it).isEqualTo(listOf<MovieData>())
            coVerify(exactly = 1) { api.getMovies(any()) }
        }
    }
}