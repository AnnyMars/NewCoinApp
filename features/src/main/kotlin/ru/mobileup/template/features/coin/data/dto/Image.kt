package ru.mobileup.template.features.coin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("large")
    val large: String,
    @SerialName("small")
    val small: String,
    @SerialName("thumb")
    val thumb: String
)
