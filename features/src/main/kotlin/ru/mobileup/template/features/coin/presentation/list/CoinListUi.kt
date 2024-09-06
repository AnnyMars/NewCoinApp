package ru.mobileup.template.features.coin.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.utils.formatCurrency
import ru.mobileup.template.core.utils.formatPricePercentage
import ru.mobileup.template.core.widget.EmptyPlaceholder
import ru.mobileup.template.core.widget.PullRefreshLceWidget
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
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        color = CustomTheme.colors.background.screen
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CurrencyRow(
                currencies = component.currencies,
                selectedCurrency = selectedCurrency,
                onCurrencyClick = component::onCurrencyClick
            )

            PullRefreshLceWidget(
                state = coinState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { coins, _ ->
                if (coins.isNotEmpty()) {
                    CoinListContent(
                        coins = coins,
                        onCoinClick = component::onCoinClick,
                        selectedCurrency = selectedCurrency
                    )
                } else {
                    EmptyPlaceholder(description = stringResource(id = R.string.coin_ui_error))
                }
            }
        }
    }
}

@Composable
fun CurrencyRow(
    currencies: List<Currency>,
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
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
                text = stringResource(id = R.string.coin_ui_title),
                style = CustomTheme.typography.title.regular,
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                currencies.forEach {
                    CurrencyItem(
                        currency = it,
                        isSelected = it == selectedCurrency,
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
    selectedCurrency: Currency,
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
            CoinItem(coin = coin, onClick = { onCoinClick(coin.id) }, currency = selectedCurrency)
        }
    }
}

@Composable
private fun CoinItem(
    coin: Coin,
    onClick: () -> Unit,
    currency: Currency,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 1.dp)
            .fillMaxWidth()
            .background(color = Color.White.copy(alpha = 0.5f), shape = RectangleShape)
    ) {
        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = ImageRequest.Builder(context)
                .data(coin.image)
                .crossfade(true)
                .build(),
            contentDescription =  "Coin item image"
        )

        Spacer(modifier = Modifier.width(3.dp))

        Column(
            modifier = Modifier.padding(start = 3.dp)
        ) {
            Text(
                text = coin.name,
                style = CustomTheme.typography.coinCardText.topText
            )
            Text(
                text = coin.symbol,
                style = CustomTheme.typography.coinCardText.bottomText
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column {
            Text(
                modifier = Modifier.align(Alignment.End),
                text = formatCurrency(coin.currentPrice, currency.name),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.align(Alignment.End),
                text = formatPricePercentage(coin.priceChangePercentage24h),
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp
                )
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun CoinListUiPreview() {
    AppTheme {
        CoinListUi(component = FakeCoinListComponent())
    }
}