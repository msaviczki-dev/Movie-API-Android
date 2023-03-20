package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.TV_DATA
import com.freecast.thatmovieapp.data.repository.TVRepository
import com.freecast.thatmovieapp.domain.usecase.GetTVUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TVUseCaseTest {

    private val repository: TVRepository = mockk(relaxed = true)

    private var useCase = GetTVUseCase(repository)

    @Test
    fun `when get success return on getTV repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getTV() } returns flowOf(TV_DATA)

        // ACT
        useCase().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(TV_DATA)
            coVerify(exactly = 1) { repository.getTV() }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getTV repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getTV() } returns flow { throw Throwable() }

        // ACT
        useCase().collect()
    }
}
