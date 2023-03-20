package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.DETAIL_DATA
import com.freecast.thatmovieapp.data.repository.DetailRepository
import com.freecast.thatmovieapp.domain.usecase.GetDetailUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DetailUseCaseTest {

    private val repository: DetailRepository = mockk(relaxed = true)

    private var useCase = GetDetailUseCase(repository)

    @Test
    fun `when get success return on getTVDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getTVDetail(0) } returns flowOf(DETAIL_DATA)

        // ACT
        useCase(0, isMovie = false).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(DETAIL_DATA)
            coVerify(exactly = 1) { repository.getTVDetail(0) }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getTVDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getTVDetail(0) } returns flow { throw Throwable() }

        // ACT
        useCase(0, isMovie = false).collect()
    }

    @Test
    fun `when get success return on getMovieDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getMovieDetail(0) } returns flowOf(DETAIL_DATA)

        // ACT
        useCase(0, isMovie = true).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(DETAIL_DATA)
            coVerify(exactly = 1) { repository.getMovieDetail(0) }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getMovieDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getMovieDetail(0) } returns flow { throw Throwable() }

        // ACT
        useCase(0, isMovie = true).collect()
    }
}