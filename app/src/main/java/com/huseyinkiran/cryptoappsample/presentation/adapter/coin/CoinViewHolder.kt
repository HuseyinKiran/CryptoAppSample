package com.huseyinkiran.cryptoappsample.presentation.adapter.coin

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.huseyinkiran.cryptoappsample.databinding.ItemCoinBinding
import com.huseyinkiran.cryptoappsample.domain.model.Coin

class CoinViewHolder(private val binding: ItemCoinBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        coin: Coin,
        callback: CoinAdapter.CoinListener
    ) = with(binding) {

        txtName.text = coin.name
        txtRank.text = "${coin.rank}. "
        txtSymbol.text = " (${coin.symbol})"
        txtIsActive.text = if (coin.isActive) "active" else "inactive"

        txtIsActive.setTextColor(
            if (coin.isActive) Color.GREEN else Color.RED
        )

        itemCoin.setOnClickListener {
            callback.onCoinClick(coin.id)
        }

    }
}