package ru.naves.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val NaVesColorScheme = lightColorScheme(
    primary = NavesColors.tomato,
    secondary = NavesColors.basil,
    tertiary = NavesColors.honey,
    background = NavesColors.appBg,
    surface = NavesColors.surface,
    onBackground = NavesColors.text,
    onSurface = NavesColors.text,
    outlineVariant = NavesColors.line
)

@Composable
fun NaVesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NaVesColorScheme,
        typography = NaVesTypography,
        content = content
    )
}
