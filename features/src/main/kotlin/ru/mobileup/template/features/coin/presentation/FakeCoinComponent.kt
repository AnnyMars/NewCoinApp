package ru.mobileup.template.features.coin.presentation

import ru.mobileup.template.core.utils.createFakeChildStackStateFlow
import ru.mobileup.template.features.coin.presentation.list.FakeCoinListComponent

class FakeCoinComponent: CoinComponent {
    override val childStack = createFakeChildStackStateFlow(
        CoinComponent.Child.List(FakeCoinListComponent())
    )

}