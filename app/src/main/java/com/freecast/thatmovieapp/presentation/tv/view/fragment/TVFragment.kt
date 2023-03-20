package com.freecast.thatmovieapp.presentation.tv.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.databinding.FragmentSerieBinding
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.domain.entities.TVGenreData
import com.freecast.thatmovieapp.helper.base.FragmentViewBinding
import com.freecast.thatmovieapp.helper.extension.launch
import com.freecast.thatmovieapp.presentation.detail.view.activity.DetailActivity
import com.freecast.thatmovieapp.presentation.tv.view.adapter.GenderAdapter
import com.freecast.thatmovieapp.presentation.tv.view.adapter.TVAdapter
import com.freecast.thatmovieapp.presentation.tv.viewmodel.TVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TVFragment : FragmentViewBinding<FragmentSerieBinding>(), TVAdapter.TVClickListener,
    GenderAdapter.OnGenderClickListener {

    private val viewModel: TVViewModel by viewModel()

    private val adapter = GenderAdapter(mutableListOf(), this@TVFragment)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSerieBinding = FragmentSerieBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTV()
        observeGenderTV()
        setupRequests()
    }

    private fun setupRequests() {
        viewModel.getTV()
        viewModel.getTVGenre()
    }

    private fun observeTV() = lifecycleScope.launchWhenCreated {
        viewModel.tvFlow.collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> onTVSuccess(result.data)
                else -> {}
            }
        }
    }

    private fun observeGenderTV() = lifecycleScope.launchWhenCreated {
        viewModel.tvGenreFlow.collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> onTVGenreSuccess(result.data)
                else -> {}
            }
        }
    }

    private fun onTVGenreSuccess(data: List<TVGenreData>) = binding.apply {
        recyclerGenderTv.isVisible = true
        recyclerGenderTv.adapter = adapter
        adapter.setData(data)
    }

    private fun onTVSuccess(list: List<TVData>) = binding.apply {
        loadingTv.isVisible = false
        groupTv.isVisible = true

        val adapter = TVAdapter(list, this@TVFragment)
        recyclerTv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onClick(movieId: Int) {
        requireActivity().launch<DetailActivity> {
            putExtra(DetailActivity.MOVIE_ID, movieId)
            putExtra(DetailActivity.IS_MOVIE, false)
        }
    }

    override fun onGenderClick(genderId: Int) {
        viewModel.filtredList(genderId)
    }
}