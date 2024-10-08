package ru.mobileup.template.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val isLight: Boolean,
    val background: BackgroundColors,
    val text: TextColors,
    val icon: IconColors,
    val button: ButtonColors,
    val currencyButton: CurrencyColors
)

data class BackgroundColors(
    val screen: Color,
    val toast: Color
)

data class TextColors(
    val primary: Color,
    val secondary: Color,
    val invert: Color
)

data class IconColors(
    val primary: Color,
    val secondary: Color,
    val invert: Color
)

data class ButtonColors(
    val primary: Color,
    val secondary: Color
)

data class CurrencyColors(
    val selected: Color,
    val unselected: Color
)

val LocalCustomColors = staticCompositionLocalOf<CustomColors?> { null }
