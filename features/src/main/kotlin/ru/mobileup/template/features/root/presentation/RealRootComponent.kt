package ru.mobileup.template.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.createMessageComponent
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.coin.createCoinComponent
import ru.mobileup.template.features.coin.createCoinDetailComponent
import ru.mobileup.template.features.coin.domain.CoinId

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Coin,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is ChildConfig.Coin -> {
            RootComponent.Child.Coin(
                componentFactory.createCoinComponent(componentContext)
            )
        }
//        is ChildConfig.DetailCoin -> {
//            RootComponent.Child.DetailCoin(
//                componentFactory.createCoinDetailComponent(componentContext, config.coinId)
//            )
//        }
    }


    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object Coin: ChildConfig

//        @Serializable
//        data class DetailCoin(val coinId: CoinId): ChildConfig

    }
}
