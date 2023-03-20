package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.presentation.detail.viewmodel.DetailViewModel
import com.freecast.thatmovieapp.presentation.movie.viewmodel.MovieViewModel
import com.freecast.thatmovieapp.presentation.playvideo.viewmodel.PlayerViewModel
import com.freecast.thatmovieapp.presentation.tv.viewmodel.TVViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = listOf(module {
    viewModel { MovieViewModel(useCase = get()) }
    viewModel { DetailViewModel(useCase = get()) }
    viewModel { TVViewModel(useCase = get(), genreUseCase = get()) }
    viewModel { PlayerViewModel(useCase = get()) }
})