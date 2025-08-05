package com.huseyinkiran.cryptoappsample.domain.repository

import com.huseyinkiran.cryptoappsample.domain.model.Coin
import com.huseyinkiran.cryptoappsample.domain.model.CoinDetail

interface CoinRepository {

    suspend fun getCoins(): List<Coin>

    suspend fun getCoinDetail(coinId: String): CoinDetail
}