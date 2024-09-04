package ru.mobileup.template.features.coin.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.widget.EmptyPlaceholder
import ru.mobileup.template.core.widget.PullRefreshLceWidget
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.features.R
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.domain.Currency

@Composable
fun CoinListUi(
    component: CoinListComponent,
    modifier: Modifier = Modifier
) {
    val coinState by component.coinsState.collectAsState()
    val selectedCurrency by component.selectedCurrency.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        color = CustomTheme.colors.background.screen
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CurrencyRow(
                currencys = component.currencys,
                selectedCurrency = selectedCurrency,
                onCurrencyClick = component::onCurrencyClick
            )

            PullRefreshLceWidget(
                state = coinState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { coins, refreshing ->
                if (coins.isNotEmpty()) {
                    CoinListContent(
                        coins = coins,
                        onCoinClick = component::onCoinClick
                    )
                } else {
                    EmptyPlaceholder(description = "Coins not found")
                }
                RefreshingProgress(active = refreshing)
            }
        }
    }
}

@Composable
fun CurrencyRow(
    currencys: List<Currency>,
    selectedCurrency: Currency,
    onCurrencyClick: (Currency) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CustomTheme.colors.background.screen,
        shadowElevation = 4.dp
    ) {
        Column(Modifier.statusBarsPadding()) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
//                text = stringResource(id = R.string.coin_ui_title),
                text = stringResource(id = R.string.coin_ui_title),
                style = CustomTheme.typography.title.regular,
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                currencys.forEach {
                    CurrencyItem(
                        currency = it,
                        isSelected = it.name == selectedCurrency.name,
                        onClick = { onCurrencyClick(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CoinListContent(
    coins: List<Coin>,
    onCoinClick: (CoinId) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = coins,
            key = { it.id.value }
        ) { coin ->
            CoinItem(coin = coin, onClick = { onCoinClick(coin.id) })
        }
    }
}

@Composable
private fun CoinItem(
    coin: Coin,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color.Red)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = coin.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = coin.symbol,
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Column {
                Text(
                    text = coin.currentPrice,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp
                    )
                )
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = coin.priceChangePercentage24h,
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CoinListUiPreview(){
    AppTheme {
        CoinListUi(component = FakeCoinListComponent())
    }
}