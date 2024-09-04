package ru.mobileup.template.features.coin.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.domain.Currency

class FakeCoinListComponent: CoinListComponent {

    override val currencys: List<Currency> = listOf(
        Currency.USD,
        Currency.RUB
    )

    override val selectedCurrency: StateFlow<Currency> = MutableStateFlow(currencys[0])

    override val coinsState: StateFlow<LoadableState<List<Coin>>> = MutableStateFlow(
        LoadableState(
            loading = true,
            data = listOf(
                Coin(
                    id = CoinId("bitcoin"),
                    symbol = "btc",
                    name = "Bitcoin",
                    currentPrice = "100",
                    image = "",
                    priceChangePercentage24h = "1.2"
                ),
                Coin(
                    id = CoinId("ethereum"),
                    symbol = "eth",
                    name = "Ethereum",
                    currentPrice = "200",
                    image = "",
                    priceChangePercentage24h = "5.111"
                ),
                Coin(
                    id = CoinId("tether"),
                    symbol = "usdt",
                    name = "Tether",
                    currentPrice = "300",
                    image = "",
                    priceChangePercentage24h = "-6.0"
                ),
            )
        )
    )

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onCoinClick(coinId: CoinId) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit

}