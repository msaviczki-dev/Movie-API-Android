package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.domain.usecase.GetDetailUseCase
import com.freecast.thatmovieapp.domain.usecase.GetMovieUseCase
import com.freecast.thatmovieapp.domain.usecase.GetTVGenreUseCase
import com.freecast.thatmovieapp.domain.usecase.GetTVUseCase
import org.koin.dsl.module

fun provideDomainModule() = listOf(module {
    factory { GetMovieUseCase(repository = get()) }
    factory { GetDetailUseCase(repository = get()) }
    factory { GetTVUseCase(repository = get()) }
    factory { GetTVGenreUseCase(repository = get()) }
})