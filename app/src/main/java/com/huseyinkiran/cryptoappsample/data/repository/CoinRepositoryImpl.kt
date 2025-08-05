package com.huseyinkiran.cryptoappsample.data.repository

import com.huseyinkiran.cryptoappsample.data.CryptoApi
import com.huseyinkiran.cryptoappsample.data.dto.toCoin
import com.huseyinkiran.cryptoappsample.data.dto.toCoinDetail
import com.huseyinkiran.cryptoappsample.domain.model.Coin
import com.huseyinkiran.cryptoappsample.domain.model.CoinDetail
import com.huseyinkiran.cryptoappsample.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CryptoApi
): CoinRepository {

    override suspend fun getCoins(): List<Coin> {
        return api.getCoins().map { it.toCoin() }
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetail {
        return api.getCoinDetail(coinId).toCoinDetail()
    }
}