package com.freecast.thatmovieapp.presentation.movie.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.databinding.FragmentMovieBinding
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.helper.base.FragmentViewBinding
import com.freecast.thatmovieapp.helper.extension.launch
import com.freecast.thatmovieapp.presentation.movie.view.adapter.MovieAdapter
import com.freecast.thatmovieapp.presentation.movie.viewmodel.MovieViewModel
import com.freecast.thatmovieapp.presentation.detail.view.activity.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : FragmentViewBinding<FragmentMovieBinding>(), MovieAdapter.MovieClickListener {

    private val viewModel: MovieViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewModel.getMovie()
    }

    private fun observe() = lifecycleScope.launchWhenCreated {
        viewModel.movieFlow.collect { result ->
            when (result) {
                is Result.Error -> onError()
                is Result.Loading -> onLoading()
                is Result.Success -> onSuccess(result.data)
                else -> {}
            }
        }
    }

    private fun onError() {

    }

    private fun onSuccess(list: List<MovieData>) = binding.apply {
        loadingMovies.isVisible = false
        groupMovies.isVisible = true

        val adapter = MovieAdapter(list, this@MovieFragment)
        recyclerMovies.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onLoading() = binding.apply {
        loadingMovies.isVisible = true
        groupMovies.isVisible = false
    }

    override fun onClick(movieId: Int) {
        requireActivity().launch<DetailActivity> {
            putExtra(DetailActivity.MOVIE_ID, movieId)
            putExtra(DetailActivity.IS_MOVIE, true)
        }
    }

}