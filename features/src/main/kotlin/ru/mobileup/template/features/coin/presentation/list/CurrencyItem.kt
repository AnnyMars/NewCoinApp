package ru.mobileup.template.features.coin.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.common_domain.Currency

@Composable
fun CurrencyItem(
    currency: Currency,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(48.dp),
        color = when (isSelected) {
            true -> CustomTheme.colors.currencyButton.selected
            else -> CustomTheme.colors.currencyButton.unselected
        },
        shadowElevation = 6.dp
    ) {
        Text(
            text = currency.name,
            style = CustomTheme.typography.body.regular,
            color = if (isSelected) {
                CustomTheme.colors.text.primary
            } else {
                CustomTheme.colors.text.invert
            },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun CurrencyItemPreview() {
    var isSelected by remember { mutableStateOf(true) }
    AppTheme {
        CurrencyItem(
            currency = Currency.RUB,
            isSelected = isSelected,
            onClick = { isSelected = !isSelected })
    }
}