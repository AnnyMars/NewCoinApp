package ru.mobileup.template.features.coin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.coin.presentation.list.CoinListUi

@Composable
fun CoinUI(
    component: CoinComponent,
    modifier: Modifier = Modifier
){
    val childStack by component.childStack.collectAsState()

    Children(stack = childStack, modifier) { child ->
        when (val instance = child.instance){
            is CoinComponent.Child.List -> CoinListUi(component = instance.component)
        }
    }
}

@Preview
@Composable
fun CoinUIPreview(){
    AppTheme {
        CoinUI(component = FakeCoinComponent())
    }
}