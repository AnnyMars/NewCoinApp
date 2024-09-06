package ru.mobileup.template.features.coin.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.core.common_domain.Currency

class FakeCoinListComponent: CoinListComponent {

    override val currencies: List<Currency> = listOf(
        Currency.USD,
        Currency.RUB
    )

    override val selectedCurrency: StateFlow<Currency> = MutableStateFlow(currencies[0])

    override val coinsState: StateFlow<LoadableState<List<Coin>>> = MutableStateFlow(
        LoadableState(
            data = Coin.MOCKS
        )
    )

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onCoinClick(coinId: CoinId) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit

}