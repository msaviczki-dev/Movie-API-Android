package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.data.repository.*
import com.freecast.thatmovieapp.network.api.DetailApi
import com.freecast.thatmovieapp.network.api.MoviesApi
import com.freecast.thatmovieapp.network.api.TVApi
import com.freecast.thatmovieapp.network.api.VideoApi
import com.freecast.thatmovieapp.network.core.ApiClient
import org.koin.dsl.module

fun provideDataModule() = listOf(module {
    // API
    single { ApiClient().client.create(MoviesApi::class.java) }
    single { ApiClient().client.create(DetailApi::class.java) }
    single { ApiClient().client.create(TVApi::class.java) }
    single { ApiClient().client.create(VideoApi::class.java) }

    //  REPOSITORY
    single<TVRepository> { TVRepositoryImpl(api = get()) }
    single<MovieRepository> { MovieRepositoryImpl(api = get()) }
    single<DetailRepository> { DetailRepositoryImpl(api = get()) }
    single<VideoRepository> { VideoRepositoryImpl(api = get()) }
})