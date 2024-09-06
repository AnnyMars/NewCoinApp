package ru.mobileup.template.features.coin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.utils.pricePercentageFormatter
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId

@Serializable
data class CoinsResponse(
    @SerialName("id") val id: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("name") val name: String,
    @SerialName("current_price") val currentPrice: Double,
    @SerialName("image") val image: String,
    @SerialName("price_change_percentage_24h") val priceChangePercentage24h: Double
) {
    companion object {
        fun CoinsResponse.toDomain(): Coin {
            return Coin(
                id = CoinId(id),
                symbol = symbol,
                name = name,
                currentPrice = currentPrice.toString(),
                image = image,
//                priceChangePercentage24h = pricePercentageFormatter(priceChangePercentage24h)
                priceChangePercentage24h = priceChangePercentage24h
            )
        }
    }
}

