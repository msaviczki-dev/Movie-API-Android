package com.freecast.thatmovieapp.presentation.tv.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.databinding.MoviesItemBinding
import com.freecast.thatmovieapp.domain.entities.TVData
import com.freecast.thatmovieapp.helper.extension.loadImage

class TVAdapter(
    private val list: List<TVData>,
    private val listener: TVClickListener
) :
    RecyclerView.Adapter<TVAdapter.TVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val binding = MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TVViewHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TVData) = binding.apply {
            root.setOnClickListener { listener.onClick(item.id) }
            txtMovieRate.text = item.vote.toString()
            txtMovieTitle.text = item.title
            rateMovieStars.rating = item.voteRate
            txtMovieOriginalTitle.text = item.title
            imgMovies.loadImage(BuildConfig.URL_POSTER + item.poster)
        }
    }

    interface TVClickListener {
        fun onClick(movieId: Int)
    }
}