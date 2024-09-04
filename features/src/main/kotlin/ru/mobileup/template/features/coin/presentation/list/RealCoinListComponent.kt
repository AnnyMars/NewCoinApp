package ru.mobileup.template.features.coin.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import me.aartikov.replica.algebra.normal.withKey
import me.aartikov.replica.keyed.keepPreviousData
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.core.utils.persistent
import ru.mobileup.template.features.coin.data.CoinRepository
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.domain.Currency

class RealCoinListComponent(
    componentContext: ComponentContext,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler
): ComponentContext by componentContext, CoinListComponent {

    override val currencies = listOf(
        Currency.USD,
        Currency.RUB
    )
    override val selectedCurrency = MutableStateFlow(currencies[0])

    private val coinReplica = coinRepository.coinList.keepPreviousData().withKey(selectedCurrency)

    init {
        persistent(
            serializer = PersistentState.serializer(),
            save = { PersistentState(selectedCurrency.value) },
            restore = { state -> selectedCurrency.value = state.selectedCurrency }
        )
    }

    override val coinsState = coinReplica.observe(this, errorHandler)

    override fun onCurrencyClick(currency: Currency) {
        TODO("Not yet implemented")
    }

    override fun onCoinClick(coinId: CoinId) {
        TODO("Not yet implemented")
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

    @Serializable
    private data class PersistentState(
        val selectedCurrency: Currency
    )

}