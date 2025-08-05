package com.huseyinkiran.cryptoappsample.presentation.adapter.tag

import androidx.recyclerview.widget.RecyclerView
import com.huseyinkiran.cryptoappsample.databinding.ItemTagBinding
import com.huseyinkiran.cryptoappsample.domain.model.Tag

class TagViewHolder(private val binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        tag: Tag
    ) = with(binding) {

        txtTag.text = tag.name
    }

}