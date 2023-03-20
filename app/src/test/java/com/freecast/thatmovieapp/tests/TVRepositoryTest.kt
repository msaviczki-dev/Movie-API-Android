package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.*
import com.freecast.thatmovieapp.data.repository.TVRepositoryImpl
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.network.api.TVApi
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class TVRepositoryTest {
    private val api: TVApi = mockk(relaxed = true)

    private var repository = TVRepositoryImpl(api)

    @Test
    fun `when get success return on getTV api`() = runBlocking {

        // ARRANGE
        coEvery { api.getTV(any()) } returns Response.success(TV_RESPONSE)

        // ACT
        repository.getTV().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(TV_DATA)
            coVerify(exactly = 1) { api.getTV(any()) }
        }
    }

    @Test
    fun `when get success return on getTV api, but list is null`() = runBlocking {

        // ARRANGE
        coEvery { api.getTV(any()) } returns Response.success(TV_RESPONSE_NULL)

        // ACT
        repository.getTV().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(listOf<TVData>())
            coVerify(exactly = 1) { api.getTV(any()) }
        }
    }

    @Test
    fun `when get success return on getGenre api`() = runBlocking {

        // ARRANGE
        coEvery { api.getTVGenres(any()) } returns Response.success(TV_GENRE_RESPONSE)

        // ACT
        repository.getGenre().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(TV_GENRE_DATA)
            coVerify(exactly = 1) { api.getTVGenres(any()) }
        }
    }

    @Test
    fun `when get success return on getGenre api, but list is null`() = runBlocking {

        // ARRANGE
        coEvery { api.getTVGenres(any()) } returns Response.success(TV_GENRE_NULL)

        // ACT
        repository.getGenre().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(TV_GENRE_DATA_ONLY_ALL)
            coVerify(exactly = 1) { api.getTVGenres(any()) }
        }
    }
}