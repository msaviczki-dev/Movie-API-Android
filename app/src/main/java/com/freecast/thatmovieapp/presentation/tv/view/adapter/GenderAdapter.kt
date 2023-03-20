package com.freecast.thatmovieapp.presentation.tv.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.databinding.GenderItemBinding
import com.freecast.thatmovieapp.domain.entities.TVGenreData

class GenderAdapter(
    private val list: MutableList<TVGenreData>,
    private val listener: OnGenderClickListener
) :
    RecyclerView.Adapter<GenderAdapter.GenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        val binding = GenderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenderViewHolder(binding)
    }

    fun setData(data: List<TVGenreData>) {
        list.clear()
        list.addAll(data)
    }

    private fun refreshSelectedItem() {
        list.forEach { item -> item.isNotSelected = true }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class GenderViewHolder(private val binding: GenderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TVGenreData, position: Int) = binding.apply {
            root.isSelected = item.isNotSelected
            txtGender.isSelected = item.isNotSelected

            txtGender.text = item.name

            root.setOnClickListener {
                refreshSelectedItem()
                list[position].isNotSelected = false
                notifyDataSetChanged()
                listener.onGenderClick(item.id)
            }
        }
    }

    interface OnGenderClickListener {
        fun onGenderClick(genderId: Int)
    }
}