package com.huseyinkiran.cryptoappsample.data

import com.huseyinkiran.cryptoappsample.data.dto.CoinDetailDto
import com.huseyinkiran.cryptoappsample.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinDetail(
        @Path("coinId") coinId: String
    ): CoinDetailDto
}