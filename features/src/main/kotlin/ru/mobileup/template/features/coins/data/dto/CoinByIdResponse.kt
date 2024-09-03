package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinByIdResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: Image,
    @SerialName("description")
    val description: Description,
    @SerialName("categories")
    val categories: List<String>
)
