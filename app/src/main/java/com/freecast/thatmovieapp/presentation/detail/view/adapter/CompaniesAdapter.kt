package com.freecast.thatmovieapp.presentation.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.BuildConfig
import com.freecast.thatmovieapp.databinding.CompaniesItemBinding
import com.freecast.thatmovieapp.domain.entities.DetailData
import com.freecast.thatmovieapp.helper.extension.loadImageCircle

class CompaniesAdapter(private val list: List<DetailData.ProductionCompaniesData>) :
    RecyclerView.Adapter<CompaniesAdapter.CompaniesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesViewHolder {
        val binding = CompaniesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompaniesViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CompaniesViewHolder(private val binding: CompaniesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DetailData.ProductionCompaniesData) = binding.apply {
            txtCompanie.text = item.name
            imgCompanie.loadImageCircle(BuildConfig.URL_POSTER + item.logo)
        }
    }

}