package com.huseyinkiran.cryptoappsample.presentation.adapter.coin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huseyinkiran.cryptoappsample.databinding.ItemCoinBinding
import com.huseyinkiran.cryptoappsample.domain.model.Coin

class CoinAdapter(
    private val callback: CoinListener
): RecyclerView.Adapter<CoinViewHolder>() {

    interface CoinListener {
        fun onCoinClick(coinId: String)
    }

    private var coinList: List<Coin> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun getItemCount(): Int = coinList.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinList[position]
        holder.bind(coin, callback)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Coin>) {
        coinList = newList
        notifyDataSetChanged()
    }

}