package com.huseyinkiran.cryptoappsample.presentation.coinDetail

import com.huseyinkiran.cryptoappsample.domain.model.CoinDetail

data class CoinState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)