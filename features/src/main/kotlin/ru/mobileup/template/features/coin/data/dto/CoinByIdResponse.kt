package ru.mobileup.template.features.coin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.features.coin.domain.DetailedCoin

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
    val categories: List<String>,
)

fun CoinByIdResponse.toDomain(): DetailedCoin {
    return DetailedCoin(
        id = id,
        name = name,
        image = image.large,
        description = description.en,
        categories = categories
    )
}