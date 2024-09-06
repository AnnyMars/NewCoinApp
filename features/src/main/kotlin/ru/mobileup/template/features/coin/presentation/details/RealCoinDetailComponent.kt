package ru.mobileup.template.features.coin.presentation.details

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.message.data.MessageService
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.coin.data.CoinRepository
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.domain.DetailedCoin

class RealCoinDetailComponent(
    componentContext: ComponentContext,
    coinId: CoinId,
    coinRepository: CoinRepository,
    private val messageService: MessageService,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CoinDetailComponent {

    private val coinReplica = coinRepository.coinDetailsReplica.withKey(coinId)

    override val coinState: StateFlow<LoadableState<DetailedCoin>> = coinReplica.observe(this, errorHandler)

    override fun onRetryClick() {
        coinReplica.refresh()
    }

    override fun onRefresh() {
        coinReplica.refresh()
    }
}