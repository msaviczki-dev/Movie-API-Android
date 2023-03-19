package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.presentation.movie.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = listOf(module {
    viewModel { MovieViewModel(useCase = get()) }
})