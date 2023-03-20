package com.freecast.thatmovieapp.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freecast.thatmovieapp.data.VIDEO_DATA
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.VideoData
import com.freecast.thatmovieapp.domain.usecase.GetVideoUseCase
import com.freecast.thatmovieapp.helpers.MainCoroutineRule
import com.freecast.thatmovieapp.presentation.playvideo.viewmodel.PlayerViewModel
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
class VideoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val useCase: GetVideoUseCase = mockk(relaxed = true)
    private lateinit var viewModel: PlayerViewModel

    @Before
    fun setup() {
        viewModel = PlayerViewModel(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when fetch getMoviesVideo data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<VideoData>>()
            every { useCase(0, true) } returns flowOf(VIDEO_DATA)

            //ACT
            val job = launch {
                viewModel.videoFlow.toList(stateList)
                viewModel.videoFlow.collect()
            }

            viewModel.getVideo(0, true)

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(VIDEO_DATA.first()))

            job.cancel()
        }

    @Test
    fun `when fetch getMovies error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<VideoData>>()
            every { useCase(0, true) } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.videoFlow.toList(stateList)
                viewModel.videoFlow.collect()
            }

            viewModel.getVideo(0, true)

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }
}