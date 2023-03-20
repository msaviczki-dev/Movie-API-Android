package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.domain.usecase.*
import org.koin.dsl.module

fun provideDomainModule() = listOf(module {
    factory { GetMovieUseCase(repository = get()) }
    factory { GetDetailUseCase(repository = get()) }
    factory { GetTVUseCase(repository = get()) }
    factory { GetTVGenreUseCase(repository = get()) }
    factory { GetVideoUseCase(repository = get()) }
})