package com.freecast.thatmovieapp.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.DetailData
import com.freecast.thatmovieapp.domain.usecase.GetDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: GetDetailUseCase) : ViewModel() {

    private val _movieDetailFlow = MutableStateFlow<Result<DetailData>>(Result.Idle)
    val movieDetailFlow: StateFlow<Result<DetailData>> get() = _movieDetailFlow

    fun getMovieDetail(id: Int, isMovie: Boolean) {
        viewModelScope.launch {
            useCase(id, isMovie).onStart { _movieDetailFlow.value = Result.Loading }
                .catch { error -> _movieDetailFlow.value = Result.Error(error) }
                .collect { result -> _movieDetailFlow.value = Result.Success(result) }
        }
    }
}