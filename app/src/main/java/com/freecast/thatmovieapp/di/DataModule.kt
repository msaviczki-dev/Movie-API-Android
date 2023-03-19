package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.data.repository.MovieRepository
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.network.api.MoviesApi
import com.freecast.thatmovieapp.network.core.ApiClient
import org.koin.dsl.module

fun provideDataModule() = listOf(module {
    // API
    single { ApiClient().client.create(MoviesApi::class.java) }

    //  REPOSITORY
    single<MovieRepository> { MovieRepositoryImpl(api = get()) }
})