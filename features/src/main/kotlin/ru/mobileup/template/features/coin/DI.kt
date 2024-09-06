package ru.mobileup.template.features.coin

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.coin.data.CoinApi
import ru.mobileup.template.features.coin.data.CoinRepository
import ru.mobileup.template.features.coin.data.CoinRepositoryImpl
import ru.mobileup.template.features.coin.presentation.CoinComponent
import ru.mobileup.template.features.coin.presentation.RealCoinComponent
import ru.mobileup.template.features.coin.presentation.list.CoinListComponent
import ru.mobileup.template.features.coin.presentation.list.RealCoinListComponent

val coinModule = module {
    single<CoinApi> { get<NetworkApiFactory>().unauthorizedKtorfit.create() }
    single<CoinRepository> { CoinRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createCoinComponent(
    componentContext: ComponentContext
): CoinComponent{
    return RealCoinComponent(componentContext, get())
}

fun ComponentFactory.createCoinListComponent(
    componentContext: ComponentContext,
    onOutput: (CoinListComponent.Output) -> Unit
): CoinListComponent{
    return RealCoinListComponent(componentContext, onOutput, get(), get())
}