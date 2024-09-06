package ru.mobileup.template.core.utils

fun formatPricePercentage(value: Double): String {
    return String.format("%.2f%%", value)
}