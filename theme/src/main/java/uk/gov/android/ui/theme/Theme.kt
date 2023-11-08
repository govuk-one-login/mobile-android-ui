package uk.gov.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = md_theme_dark_background,
    error = md_theme_dark_error,
    onBackground = md_theme_dark_onBackground,
    onError = md_theme_dark_onError,
    onPrimary = md_theme_dark_onPrimary,
    onSecondary = md_theme_dark_onSecondary,
    onSurface = md_theme_dark_onSurface,
    primary = md_theme_dark_primary,
    primaryVariant = md_theme_dark_primaryVariant,
    secondary = md_theme_dark_secondary,
    secondaryVariant = md_theme_dark_secondary,
    surface = md_theme_dark_surface
)

private val LightColorPalette = lightColors(
    background = md_theme_light_background,
    error = md_theme_light_error,
    onBackground = md_theme_light_onBackground,
    onError = md_theme_light_onError,
    onPrimary = md_theme_light_onPrimary,
    onSecondary = md_theme_light_onSecondary,
    onSurface = md_theme_light_onSurface,
    primary = md_theme_light_primary,
    primaryVariant = md_theme_light_primaryVariant,
    secondary = md_theme_light_secondary,
    secondaryVariant = md_theme_light_secondary,
    surface = md_theme_light_surface
)

@Composable
fun GdsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
