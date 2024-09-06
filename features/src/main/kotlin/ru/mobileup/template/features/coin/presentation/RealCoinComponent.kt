package ru.mobileup.template.features.coin.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.coin.createCoinListComponent
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.presentation.list.CoinListComponent

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
                    componentContext,
                    ::onCoinListOutput
                )
            )
        }

        is ChildConfig.Details -> TODO()
    }

    private fun onCoinListOutput (output: CoinListComponent.Output){
        when (output){
            is CoinListComponent.Output.CoinDetailRequested -> {
                navigation.push(ChildConfig.Details(output.coinId))
            }
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object List : ChildConfig

        @Serializable
        data class Details(val coinId: CoinId) : ChildConfig

    }

}