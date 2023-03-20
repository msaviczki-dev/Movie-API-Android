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

    private val _detailFlow = MutableStateFlow<Result<DetailData>>(Result.Idle)
    val detailFlow: StateFlow<Result<DetailData>> get() = _detailFlow

    fun getDetail(id: Int, isMovie: Boolean) {
        viewModelScope.launch {
            useCase(id, isMovie).onStart { _detailFlow.value = Result.Loading }
                .catch { error -> _detailFlow.value = Result.Error(error) }
                .collect { result -> _detailFlow.value = Result.Success(result) }
        }
    }
}