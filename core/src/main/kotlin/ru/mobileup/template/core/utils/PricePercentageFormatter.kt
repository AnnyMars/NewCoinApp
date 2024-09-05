package ru.mobileup.template.core.utils

fun pricePercentageFormatter(value: Double): String {
    return String.format("%.2f%%", value)
}