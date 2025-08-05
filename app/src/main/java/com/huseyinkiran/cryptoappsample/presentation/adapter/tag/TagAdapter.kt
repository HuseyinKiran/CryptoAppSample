package com.huseyinkiran.cryptoappsample.presentation.adapter.tag

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huseyinkiran.cryptoappsample.databinding.ItemTagBinding
import com.huseyinkiran.cryptoappsample.domain.model.Tag

class TagAdapter: RecyclerView.Adapter<TagViewHolder>() {

    private var tags: List<Tag> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val tag = tags[position]
        holder.bind(tag)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(tags: List<Tag>) {
        this.tags = tags
        notifyDataSetChanged()
    }
}