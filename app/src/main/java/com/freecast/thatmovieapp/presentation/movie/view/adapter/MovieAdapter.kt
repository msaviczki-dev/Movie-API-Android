package com.freecast.thatmovieapp.presentation.movie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.databinding.MoviesItemBinding
import com.freecast.thatmovieapp.domain.entities.MovieData
import com.freecast.thatmovieapp.helper.extension.loadImage

class MovieAdapter(
    private val list: List<MovieData>,
    private val listener: MovieClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MovieViewHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieData) = binding.apply {
            root.setOnClickListener { listener.onClick(item.id) }
            txtMovieRate.text = item.vote.toString()
            txtMovieTitle.text = item.title
            rateMovieStars.rating = item.voteRate
            txtMovieOriginalTitle.text = item.title
            imgMovies.loadImage(BuildConfig.URL_POSTER + item.poster)
        }
    }

    interface MovieClickListener {
        fun onClick(movieId: Int)
    }
}