package com.freecast.thatmovieapp.presentation.tv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.domain.entities.TVGenreData
import com.freecast.thatmovieapp.domain.usecase.GetTVGenreUseCase
import com.freecast.thatmovieapp.domain.usecase.GetTVUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TVViewModel(
    private val useCase: GetTVUseCase, private val genreUseCase: GetTVGenreUseCase
) : ViewModel() {

    private val _tvFlow = MutableStateFlow<Result<List<TVData>>>(Result.Idle)
    val tvFlow: StateFlow<Result<List<TVData>>> get() = _tvFlow

    private val _tvGenreFlow = MutableStateFlow<Result<List<TVGenreData>>>(Result.Idle)
    val tvGenreFlow: StateFlow<Result<List<TVGenreData>>> get() = _tvGenreFlow

    val list: MutableList<TVData> = mutableListOf()

    fun getTV() {
        viewModelScope.launch {
            useCase().onStart { _tvFlow.value = Result.Loading }
                .catch { error -> _tvFlow.value = Result.Error(error) }.collect { result ->
                    list.clear()
                    list.addAll(result)
                    _tvFlow.value = Result.Success(result)
                }
        }
    }

    fun getTVGenre() {
        viewModelScope.launch {
            genreUseCase().onStart { _tvGenreFlow.value = Result.Loading }
                .catch { error -> _tvGenreFlow.value = Result.Error(error) }.collect { result ->
                    _tvGenreFlow.value = Result.Success(result)
                }
        }
    }

    fun filtredList(id: Int) {
        viewModelScope.launch {
            if (id == -1) {
                _tvFlow.value = Result.Success(list)
            } else {
                val filtredList = mutableListOf<TVData>()
                list.forEach { item ->
                    if (item.genresIds.contains(id)) {
                        filtredList.add(item)
                    }
                }
                _tvFlow.value = Result.Success(filtredList)
            }
        }
    }
}