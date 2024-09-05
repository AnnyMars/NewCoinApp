package ru.mobileup.template.features.coin.domain

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class CoinId(val value: String)

data class Coin(
    val id: CoinId,
    val symbol: String,
    val name: String,
    val currentPrice: String,
    val image: String,
    val priceChangePercentage24h: String
)
