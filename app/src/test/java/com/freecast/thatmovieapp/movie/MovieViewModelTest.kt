package com.freecast.thatmovieapp.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freecast.thatmovieapp.data.MOVIE_DATA
import com.freecast.thatmovieapp.domain.usecase.GetMovieUseCase
import com.freecast.thatmovieapp.helpers.MainCoroutineRule
import com.freecast.thatmovieapp.presentation.movie.viewmodel.MovieViewModel
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.MovieData
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val useCase: GetMovieUseCase = mockk(relaxed = true)
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when fetch getMovies data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<List<MovieData>>>()
            every { useCase() } returns flowOf(MOVIE_DATA)

            //ACT
            val job = launch {
                viewModel.movieFlow.toList(stateList)
                viewModel.movieFlow.collect()
            }

            viewModel.getMovie()

            // ASSERT
            assertThat(stateList.size).isEqualTo(3)
            assertThat(stateList.first()).isEqualTo(Result.Idle)
            assertThat(stateList[1]).isEqualTo(Result.Loading)
            assertThat(stateList.last()).isEqualTo(Result.Success(MOVIE_DATA))

            job.cancel()
        }

    @Test
    fun `when fetch getMovies error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<List<MovieData>>>()
            every { useCase() } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.movieFlow.toList(stateList)
                viewModel.movieFlow.collect()
            }

            viewModel.getMovie()

            // ASSERT
            assertThat(stateList.size).isEqualTo(3)
            assertThat(stateList.first()).isEqualTo(Result.Idle)
            assertThat(stateList[1]).isEqualTo(Result.Loading)
            assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }
}