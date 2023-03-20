package com.freecast.thatmovieapp.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freecast.thatmovieapp.data.TV_DATA
import com.freecast.thatmovieapp.data.VIDEO_DATA
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.domain.entities.VideoData
import com.freecast.thatmovieapp.domain.usecase.GetTVGenreUseCase
import com.freecast.thatmovieapp.domain.usecase.GetTVUseCase
import com.freecast.thatmovieapp.domain.usecase.GetVideoUseCase
import com.freecast.thatmovieapp.helpers.MainCoroutineRule
import com.freecast.thatmovieapp.presentation.playvideo.viewmodel.PlayerViewModel
import com.freecast.thatmovieapp.presentation.tv.viewmodel.TVViewModel
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TVViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val useCase: GetTVUseCase = mockk(relaxed = true)
    private val genreUseCase: GetTVGenreUseCase = mockk(relaxed = true)

    private lateinit var viewModel: TVViewModel

    @Before
    fun setup() {
        viewModel = TVViewModel(useCase, genreUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when fetch getTV data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<List<TVData>>>()
            every { useCase() } returns flowOf(TV_DATA)

            //ACT
            val job = launch {
                viewModel.tvFlow.toList(stateList)
                viewModel.tvFlow.collect()
            }

            viewModel.getTV()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(TV_DATA))

            job.cancel()
        }

    @Test
    fun `when fetch filterTVByGender data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<List<TVData>>>()
            viewModel.list.addAll(TV_DATA)

            //ACT
            val job = launch {
                viewModel.tvFlow.toList(stateList)
                viewModel.tvFlow.collect()
            }

            viewModel.filtredList(-1)

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(2)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(TV_DATA))

            job.cancel()
        }


    @Test
    fun `when fetch getTV error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<List<TVData>>>()
            every { useCase() } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.tvFlow.toList(stateList)
                viewModel.tvFlow.collect()
            }

            viewModel.getTV()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }
}