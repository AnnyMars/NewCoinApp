package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponse(
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("current_price")
    val currentPrice: Int,
    @SerialName("image")
    val image: String
)
