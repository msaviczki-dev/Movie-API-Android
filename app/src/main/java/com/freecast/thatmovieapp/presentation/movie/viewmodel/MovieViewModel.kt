package com.freecast.thatmovieapp.presentation.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.domain.usecase.GetMovieUseCase
import com.freecast.thatmovieapp.data.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: GetMovieUseCase) : ViewModel() {

    private val _movieFlow = MutableStateFlow<Result<List<MovieData>>>(Result.Idle)
    val movieFlow: StateFlow<Result<List<MovieData>>> get() = _movieFlow

    fun getMovie() {
        viewModelScope.launch {
            useCase().onStart { _movieFlow.value = Result.Loading }
                .catch { error -> _movieFlow.value = Result.Error(error) }
                .collect { result -> _movieFlow.value = Result.Success(result) }
        }
    }
}