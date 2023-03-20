package com.freecast.thatmovieapp.tests

import com.freecast.thatmovieapp.data.TV_GENRE_DATA
import com.freecast.thatmovieapp.data.repository.TVRepository
import com.freecast.thatmovieapp.domain.usecase.GetTVGenreUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GenderUseCaseTest {

    private val repository: TVRepository = mockk(relaxed = true)

    private var useCase = GetTVGenreUseCase(repository)

    @Test
    fun `when get success return on getTVGender repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getGenre() } returns flowOf(TV_GENRE_DATA)

        // ACT
        useCase().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(TV_GENRE_DATA)
            coVerify(exactly = 1) { repository.getGenre() }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getTVGender repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getGenre() } returns flow { throw Throwable() }

        // ACT
        useCase().collect()
    }
}