package com.freecast.thatmovieapp.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freecast.thatmovieapp.data.DETAIL_DATA
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.DetailData
import com.freecast.thatmovieapp.domain.usecase.GetDetailUseCase
import com.freecast.thatmovieapp.helpers.MainCoroutineRule
import com.freecast.thatmovieapp.presentation.detail.viewmodel.DetailViewModel
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
class DetailViewModel {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val useCase: GetDetailUseCase = mockk(relaxed = true)

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when fetch getTV data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<DetailData>>()
            every { useCase(0, false) } returns flowOf(DETAIL_DATA)

            //ACT
            val job = launch {
                viewModel.detailFlow.toList(stateList)
                viewModel.detailFlow.collect()
            }

            viewModel.getDetail(0, false)

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(DETAIL_DATA))

            job.cancel()
        }

    @Test
    fun `when fetch getTV error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<DetailData>>()
            every { useCase(0, false) } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.detailFlow.toList(stateList)
                viewModel.detailFlow.collect()
            }

            viewModel.getDetail(0, false)

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }
}