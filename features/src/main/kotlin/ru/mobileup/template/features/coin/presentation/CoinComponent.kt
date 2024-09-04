package ru.mobileup.template.features.coin.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.features.coin.presentation.list.CoinListComponent

interface CoinComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child{
        class List(val component: CoinListComponent): Child
    }

}