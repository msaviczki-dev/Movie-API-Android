package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.domain.usecase.GetMovieUseCase
import org.koin.dsl.module

fun provideDomainModule() = listOf(module {
    factory { GetMovieUseCase(repository = get()) }
})