package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.*
import com.freecast.thatmovieapp.data.repository.DetailRepositoryImpl
import com.freecast.thatmovieapp.network.api.DetailApi
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class DetailRepositoryTest {
    private val api: DetailApi = mockk(relaxed = true)

    private var repository = DetailRepositoryImpl(api)

    @Test
    fun `when get success return on getTVDetails api`() = runBlocking {

        // ARRANGE
        coEvery { api.getTVDetail(0, any()) } returns Response.success(DETAIL_RESPONSE)

        // ACT
        repository.getTVDetail(0).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(DETAIL_DATA)
            coVerify(exactly = 1) { api.getTVDetail(0, any()) }
        }
    }

    @Test
    fun `when get success return on getMovieDetails api`() = runBlocking {

        // ARRANGE
        coEvery { api.getMovieDetail(0, any()) } returns Response.success(DETAIL_RESPONSE)

        // ACT
        repository.getMovieDetail(0).collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(DETAIL_DATA)
            coVerify(exactly = 1) { api.getMovieDetail(0, any()) }
        }
    }
}