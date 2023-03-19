package com.freecast.thatmovieapp.presentation.movie.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.freecast.thatmovieapp.databinding.ActivityMoviesBinding
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.presentation.movie.viewmodel.MovieViewModel
import com.freecast.thatmovieapp.presentation.movie.view.adapter.MovieAdapter
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.helper.extension.launch
import com.freecast.thatmovieapp.presentation.moviedetail.MovieDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity(), MovieAdapter.MovieClickListener {

    private lateinit var binding: ActivityMoviesBinding

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        val adapter = MovieAdapter(list, this@MovieActivity)
        recyclerMovies.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onLoading() = binding.apply {
        loadingMovies.isVisible = true
        groupMovies.isVisible = false
    }

    override fun onClick(movieId: Int) {
        launch<MovieDetailActivity> {
            putExtra(MovieDetailActivity.MOVIE_ID, movieId)
        }
    }
}