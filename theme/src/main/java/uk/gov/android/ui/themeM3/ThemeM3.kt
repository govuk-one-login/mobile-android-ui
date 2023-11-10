package uk.gov.android.ui.themeM3

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import uk.gov.android.ui.theme.md_theme_dark_background
import uk.gov.android.ui.theme.md_theme_dark_error
import uk.gov.android.ui.theme.md_theme_dark_onBackground
import uk.gov.android.ui.theme.md_theme_dark_onError
import uk.gov.android.ui.theme.md_theme_dark_onPrimary
import uk.gov.android.ui.theme.md_theme_dark_onSecondary
import uk.gov.android.ui.theme.md_theme_dark_onSurface
import uk.gov.android.ui.theme.md_theme_dark_primary
import uk.gov.android.ui.theme.md_theme_dark_primaryVariant
import uk.gov.android.ui.theme.md_theme_dark_secondary
import uk.gov.android.ui.theme.md_theme_dark_surface
import uk.gov.android.ui.theme.md_theme_light_background
import uk.gov.android.ui.theme.md_theme_light_error
import uk.gov.android.ui.theme.md_theme_light_onBackground
import uk.gov.android.ui.theme.md_theme_light_onError
import uk.gov.android.ui.theme.md_theme_light_onPrimary
import uk.gov.android.ui.theme.md_theme_light_onSecondary
import uk.gov.android.ui.theme.md_theme_light_onSurface
import uk.gov.android.ui.theme.md_theme_light_primary
import uk.gov.android.ui.theme.md_theme_light_primaryVariant
import uk.gov.android.ui.theme.md_theme_light_secondary
import uk.gov.android.ui.theme.md_theme_light_surface

private val DarkColorPalette = darkColorScheme(
    background = md_theme_dark_background,
    error = md_theme_dark_error,
    onBackground = md_theme_dark_onBackground,
    onError = md_theme_dark_onError,
    onPrimary = md_theme_dark_onPrimary,
    onSecondary = md_theme_dark_onSecondary,
    onSurface = md_theme_dark_onSurface,
    primary = md_theme_dark_primary,
    tertiary = md_theme_dark_primaryVariant,
    secondary = md_theme_dark_secondary,
    surface = md_theme_dark_surface
)

private val LightColorPalette = lightColorScheme(
    background = md_theme_light_background,
    error = md_theme_light_error,
    onBackground = md_theme_light_onBackground,
    onError = md_theme_light_onError,
    onPrimary = md_theme_light_onPrimary,
    onSecondary = md_theme_light_onSecondary,
    onSurface = md_theme_light_onSurface,
    primary = md_theme_light_primary,
    tertiary = md_theme_light_primaryVariant,
    secondary = md_theme_light_secondary,
    surface = md_theme_light_surface
)

@Composable
fun GdsThemeM3(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = TypographyM3,
        shapes = ShapesM3
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            val statusBarColor = MaterialTheme.colorScheme.surface.toArgb()
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = statusBarColor
                WindowCompat
                    .getInsetsController(window, view)
                    .isAppearanceLightStatusBars = !darkTheme
            }
        }

        content()
    }
}
