package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.*
import com.freecast.thatmovieapp.data.repository.VideoRepositoryImpl
import com.freecast.thatmovieapp.domain.entities.VideoData
import com.freecast.thatmovieapp.network.api.VideoApi
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class VideoRepositoryTest {

    private val api: VideoApi = mockk(relaxed = true)

    private var repository = VideoRepositoryImpl(api)

    @Test
    fun `when get success return on getTVVideo api`() = runBlocking {

        // ARRANGE
        coEvery { api.getTVVideo(0, any()) } returns Response.success(VIDEO_RESPONSE)

        // ACT
        repository.getTVVideo(0).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(VIDEO_DATA)
            coVerify(exactly = 1) { api.getTVVideo(0, any()) }
        }
    }

    @Test
    fun `when get success return on getMovieVideo api`() = runBlocking {

        // ARRANGE
        coEvery { api.getMovieVideo(0, any()) } returns Response.success(VIDEO_RESPONSE)

        // ACT
        repository.getMovieVideo(0).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(VIDEO_DATA)
            coVerify(exactly = 1) { api.getMovieVideo(0, any()) }
        }
    }

    @Test
    fun `when get success return on getTVVideo api, but returns list null`() = runBlocking {

        // ARRANGE
        coEvery { api.getTVVideo(0, any()) } returns Response.success(VIDEO_RESPONSE_NULL)

        // ACT
        repository.getTVVideo(0).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(listOf<VideoData>())
            coVerify(exactly = 1) { api.getTVVideo(0, any()) }
        }
    }

    @Test
    fun `when get success return on getMovieVideo api, but returns list null`() = runBlocking {

        // ARRANGE
        coEvery { api.getMovieVideo(0, any()) } returns Response.success(VIDEO_RESPONSE_NULL)

        // ACT
        repository.getMovieVideo(0).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(listOf<VideoData>())
            coVerify(exactly = 1) { api.getMovieVideo(0, any()) }
        }
    }
}