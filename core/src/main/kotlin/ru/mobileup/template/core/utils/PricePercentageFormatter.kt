package ru.mobileup.template.core.utils

import ru.mobileup.template.core.common_domain.Currency
import java.text.NumberFormat
import java.util.Locale

fun formatPricePercentage(value: Double): String {
    return String.format("%.2f%%", value)
}

fun formatCurrency(amount: Double, currencyCode: Currency): String {
    return when (currencyCode) {
        Currency.USD -> {
            val dollarFormat = NumberFormat.getCurrencyInstance(Locale.US)
            dollarFormat.maximumFractionDigits = 2
            dollarFormat.minimumFractionDigits = 2
            dollarFormat.format(amount)
        }
        Currency.RUB -> {
            val rubleFormat = NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
            rubleFormat.maximumFractionDigits = 2
            rubleFormat.minimumFractionDigits = 2
            rubleFormat.format(amount)
        }
        else -> throw IllegalArgumentException("Unknown currency code")
    }
}