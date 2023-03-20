package com.freecast.thatmovieapp.presentation.detail.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.databinding.ActivityDetailBinding
import com.freecast.thatmovieapp.domain.entities.DetailData
import com.freecast.thatmovieapp.helper.const.ExtraBundleConst.IS_MOVIE
import com.freecast.thatmovieapp.helper.const.ExtraBundleConst.MOVIE_ID
import com.freecast.thatmovieapp.helper.extension.extra
import com.freecast.thatmovieapp.helper.extension.launch
import com.freecast.thatmovieapp.helper.extension.loadImage
import com.freecast.thatmovieapp.presentation.detail.viewmodel.DetailViewModel
import com.freecast.thatmovieapp.presentation.detail.view.adapter.CompaniesAdapter
import com.freecast.thatmovieapp.presentation.playvideo.PlayVideoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val movieId by extra<Int>(MOVIE_ID)
    private val isMovie by extra<Boolean>(IS_MOVIE)

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        setupRequest()
    }

    private fun observe() = lifecycleScope.launchWhenCreated {
        viewModel.detailFlow.collect { result ->
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

    private fun onLoading() {

    }

    private fun onSuccess(data: DetailData) = binding.apply {
        val adapter = CompaniesAdapter(data.companies)
        txtMovieRate.text = data.vote.toString()
        imgDetailTrailer.setOnClickListener {
            launch<PlayVideoActivity> {
                putExtra(MOVIE_ID, movieId)
                putExtra(IS_MOVIE, isMovie)
            }
        }
        txtMovieDetailTitle.text = data.title.ifEmpty { data.tvTitle }
        imgMovieDetail.loadImage(BuildConfig.URL_POSTER + data.poster)
        txtMovieDetailOverview.text = data.overview
        rateMovieDetailStars.rating = data.voteRate
        recyclerMovieDetail.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setupRequest() {
        movieId?.let { id -> viewModel.getDetail(id, isMovie ?: false) }
            ?: kotlin.run { finish() }
    }
}