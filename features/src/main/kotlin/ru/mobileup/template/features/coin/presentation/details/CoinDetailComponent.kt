package ru.mobileup.template.features.coin.presentation.details

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coin.domain.DetailedCoin

interface CoinDetailComponent {

    val coinState: StateFlow<LoadableState<DetailedCoin>>

    fun onRetryClick()

    fun onRefresh()
}