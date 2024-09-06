package ru.mobileup.template.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class CustomTypography(
    val title: TitleTypography,
    val body: BodyTypography,
    val caption: CaptionTypography,
    val button: ButtonTypography,
    val coinCardText: CoinCardText,
    val detailCoinText: DetailCoinText
)

data class TitleTypography(
    val regular: TextStyle
)

data class BodyTypography(
    val regular: TextStyle
)

data class CaptionTypography(
    val regular: TextStyle
)

data class ButtonTypography(
    val bold: TextStyle
)

data class CoinCardText(
    val topText: TextStyle,
    val bottomText: TextStyle
)

data class DetailCoinText(
    val baseText: TextStyle,
    val realText: TextStyle
)

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography?> { null }
