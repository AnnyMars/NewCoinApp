package ru.mobileup.template.features.root.presentation

import ru.mobileup.template.core.message.presentation.FakeMessageComponent
import ru.mobileup.template.core.utils.createFakeChildStackStateFlow
import ru.mobileup.template.features.coin.presentation.FakeCoinComponent
import ru.mobileup.template.features.coin.presentation.details.FakeCoinDetailComponent

class FakeRootComponent : RootComponent {

    override val childStack = createFakeChildStackStateFlow(
        RootComponent.Child.Coin(FakeCoinComponent())
    )

    override val messageComponent = FakeMessageComponent()
}
