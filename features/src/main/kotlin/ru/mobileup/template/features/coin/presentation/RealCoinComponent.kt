package ru.mobileup.template.features.coin.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.coin.createCoinListComponent

class RealCoinComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
): ComponentContext by componentContext, CoinComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): CoinComponent.Child = when (config) {
        is ChildConfig.List -> {
            CoinComponent.Child.List(
                componentFactory.createCoinListComponent(
                    componentContext
                )
            )
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object List: ChildConfig

    }

}