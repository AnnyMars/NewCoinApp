package ru.mobileup.template.features.coin.presentation.list

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.core.common_domain.Currency

interface CoinListComponent {

    val currencies: List<Currency>

    val selectedCurrency: StateFlow<Currency>

    val coinsState: StateFlow<LoadableState<List<Coin>>>

    fun onCurrencyClick(currency: Currency)

    fun onCoinClick(coinId: CoinId)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CoinDetailRequested(val coinId: CoinId): Output
    }

}