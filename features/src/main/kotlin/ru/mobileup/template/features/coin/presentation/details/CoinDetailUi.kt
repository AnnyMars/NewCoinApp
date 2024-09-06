package ru.mobileup.template.features.coin.presentation.details

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.utils.dispatchOnBackPressed
import ru.mobileup.template.core.widget.CoilImageWidget
import ru.mobileup.template.core.widget.PullRefreshLceWidget
import ru.mobileup.template.features.R
import ru.mobileup.template.features.coin.domain.DetailedCoin

@Composable
fun CoinDetailUi(
    component: CoinDetailComponent,
    modifier: Modifier = Modifier
) {
    val coinState by component.coinState.collectAsState()
    val context = LocalContext.current

    Surface(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = CustomTheme.colors.background.screen
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = { dispatchOnBackPressed(context) }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }

            PullRefreshLceWidget(
                state = coinState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { coin, _ ->
                CoinDetailContent(
                    coin = coin
                )
            }
        }
    }
}

@Composable
private fun CoinDetailContent(
    coin: DetailedCoin,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CoilImageWidget(
            url = coin.image,
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 10.dp),
            text = stringResource(id = R.string.detail_coin_description),
            style = CustomTheme.typography.detailCoinText.baseText
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 5.dp),
            text = coin.description,
            style = CustomTheme.typography.detailCoinText.realText
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 10.dp),
            text = stringResource(id = R.string.detail_coin_category),
            style = CustomTheme.typography.detailCoinText.baseText
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 5.dp),
            text = coin.categories.joinToString(", ").trim(),
            style = CustomTheme.typography.detailCoinText.realText
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CoinDetailUiPreview() {
    AppTheme {
        CoinDetailUi(component = FakeCoinDetailComponent())
    }
}