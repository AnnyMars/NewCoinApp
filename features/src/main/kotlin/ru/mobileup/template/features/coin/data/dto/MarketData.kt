package ru.mobileup.template.features.coin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketData(
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)
