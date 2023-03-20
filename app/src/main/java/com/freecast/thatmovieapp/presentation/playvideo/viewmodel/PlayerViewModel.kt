package com.freecast.thatmovieapp.presentation.playvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.VideoData
import com.freecast.thatmovieapp.domain.usecase.GetVideoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PlayerViewModel(private val useCase: GetVideoUseCase) : ViewModel() {

    private val _videoFlow = MutableStateFlow<Result<VideoData>>(Result.Idle)
    val videoFlow: StateFlow<Result<VideoData>> get() = _videoFlow

    fun getVideo(id: Int, isMovie: Boolean) {
        viewModelScope.launch {
            useCase(id, isMovie).onStart { _videoFlow.value = Result.Loading }
                .catch { error -> _videoFlow.value = Result.Error(error) }
                .collect { result -> _videoFlow.value = Result.Success(result.first()) }
        }
    }
}