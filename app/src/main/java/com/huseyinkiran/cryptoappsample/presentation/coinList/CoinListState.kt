package com.huseyinkiran.cryptoappsample.presentation.coinList

import com.huseyinkiran.cryptoappsample.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
