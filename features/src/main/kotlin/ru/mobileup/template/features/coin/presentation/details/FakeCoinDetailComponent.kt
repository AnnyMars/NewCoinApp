package ru.mobileup.template.features.coin.presentation.details

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.domain.DetailedCoin

class FakeCoinDetailComponent : CoinDetailComponent {
    override val coinState = MutableStateFlow(
        LoadableState(
            data = DetailedCoin.MOCK
        )
    )

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit

}