package com.freecast.thatmovieapp.presentation.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.freecast.thatmovieapp.databinding.ActivityMovieDetailBinding
import com.freecast.thatmovieapp.helper.extension.extra

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieId by extra<Int>(MOVIE_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRequest()
    }

    private fun setupRequest() {
        movieId?.let { id -> } ?: kotlin.run { finish() }
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }
}