package ru.mobileup.template.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.message.presentation.MessageComponent
import ru.mobileup.template.features.coin.presentation.CoinComponent
import ru.mobileup.template.features.coin.presentation.details.CoinDetailComponent

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    val messageComponent: MessageComponent

    sealed interface Child {
//        class Pokemons(val component: PokemonsComponent) : Child
        class Coin(val component: CoinComponent): Child
//        class DetailCoin(val component: CoinDetailComponent): Child
    }
}
