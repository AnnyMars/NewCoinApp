package ru.mobileup.template.features.coin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import java.text.NumberFormat
import java.util.Locale

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
    val image: String,
    @SerialName("market_data")
    val marketData: MarketData
) {
    companion object {
        fun CoinsResponse.toDomain(): Coin {
            return Coin(
                id = CoinId(id),
                symbol = symbol,
                name = name,
                currentPrice = currentPrice.toString(),
                image = image,
                priceChangePercentage24h = "${marketData.priceChangePercentage24h}%"
            )
        }
    }
}

