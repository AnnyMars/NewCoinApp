package ru.mobileup.template.features.coin.domain

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class CoinId(val value: String)

data class Coin(
    val id: CoinId,
    val symbol: String,
    val name: String,
    val currentPrice: Double,
    val image: String,
    val priceChangePercentage24h: Double
) {
    companion object {
        val MOCKS = listOf(
            Coin(
                id = CoinId("bitcoin"),
                symbol = "btc",
                name = "Bitcoin",
                currentPrice = 100.00,
                image = "",
                priceChangePercentage24h = 100.50
            ),
            Coin(
                id = CoinId("ethereum"),
                symbol = "eth",
                name = "Ethereum",
                currentPrice = 100.00,
                image = "",
                priceChangePercentage24h = 100.50
            ),
            Coin(
                id = CoinId("tether"),
                symbol = "usdt",
                name = "Tether",
                currentPrice = 100.00,
                image = "",
                priceChangePercentage24h = 100.50
            )
        )
    }
}
