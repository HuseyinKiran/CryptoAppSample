package com.huseyinkiran.cryptoappsample.domain.model


data class CoinDetail(
    val id: String?,
    val name: String?,
    val description: String?,
    val symbol: String?,
    val rank: Int?,
    val logo: String?,
    val isActive: Boolean?,
    val tags: List<Tag>?,
    val team: List<TeamMember>,
)