package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.VIDEO_DATA
import com.freecast.thatmovieapp.data.repository.VideoRepository
import com.freecast.thatmovieapp.domain.usecase.GetVideoUseCase
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
class VideoUseCaseTest {
    private val repository: VideoRepository = mockk(relaxed = true)

    private var useCase = GetVideoUseCase(repository)

    @Test
    fun `when get success return on getTVVideos repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getTVVideo(0) } returns flowOf(VIDEO_DATA)

        // ACT
        useCase(0, false).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(VIDEO_DATA)
            coVerify(exactly = 1) { repository.getTVVideo(0) }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getTVVideos repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getTVVideo(0) } returns flow { throw Throwable() }

        // ACT
        useCase(0, false).collect()
    }

    @Test
    fun `when get success return on getMovieVideos repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getMovieVideo(0) } returns flowOf(VIDEO_DATA)

        // ACT
        useCase(0, true).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(VIDEO_DATA)
            coVerify(exactly = 1) { repository.getMovieVideo(0) }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getMovieVideos repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getMovieVideo(0) } returns flow { throw Throwable() }

        // ACT
        useCase(0, true).collect()
    }
}