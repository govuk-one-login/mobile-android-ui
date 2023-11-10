package uk.gov.android.ui.theme

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import uk.gov.android.ui.ext.isDark
import uk.gov.android.ui.ext.toHexString

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
    secondaryVariant = md_theme_dark_secondaryVariant,
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
    secondaryVariant = md_theme_light_secondaryVariant,
    surface = md_theme_light_surface
)

@Composable
fun GdsTheme(
    modifier: Modifier = Modifier,
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
        shapes = Shapes
    ) {
        val backgroundColor = colors.background
        val view = LocalView.current

        if (!view.isInEditMode && view.context is Activity) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = backgroundColor.toArgb()

                WindowCompat
                    .getInsetsController(window, view)
                    .isAppearanceLightStatusBars = !darkTheme
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .then(modifier)
        ) {
            content()
        }
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    heightDp = 600,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 840
)
@Preview(
    backgroundColor = 0xFF000000,
    heightDp = 600,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 840
)
@Composable
fun Theme() {
    GdsTheme(
        modifier = Modifier.padding(20.dp)
    ) {
        Column {
            Row {
                Swatch(MaterialTheme.colors.primary, "Primary")
                Swatch(MaterialTheme.colors.primaryVariant, "Primary Variant")
                Swatch(MaterialTheme.colors.secondary, "Secondary")
                Swatch(MaterialTheme.colors.secondaryVariant, "Secondary Variant")
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row {
                Swatch(MaterialTheme.colors.background, "Background", 100, 800 / 3)
                Swatch(MaterialTheme.colors.surface, "Surface", 100, 800 / 3)
                Swatch(MaterialTheme.colors.error, "Error", 100, 800 / 3)
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row {
                Swatch(MaterialTheme.colors.onPrimary, "On Primary", 100, 800 / 2)
                Swatch(MaterialTheme.colors.onSecondary, "On Secondary", 100, 800 / 2)
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row {
                Swatch(MaterialTheme.colors.onBackground, "On Background", 100, 800 / 3)
                Swatch(MaterialTheme.colors.onSurface, "On Surface", 100, 800 / 3)
                Swatch(MaterialTheme.colors.onError, "On Error", 100, 800 / 3)
            }
        }
    }
}

@Composable
private fun Swatch(
    backgroundColor:Color,
    label: String,
    height: Int = 200,
    width: Int = 200
) {
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .height(height.dp)
            .width(width.dp)
            .padding(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                color = MaterialTheme.colors.contentColorFor(backgroundColor).let {
                    if (it == Color.Unspecified) {
                        if (backgroundColor.isDark()) {
                            Color.White
                        } else {
                            Color.Black
                        }
                    } else {
                        it
                    }
                }
            )
            Text(
                text = backgroundColor.toHexString(),
                color = MaterialTheme.colors.contentColorFor(backgroundColor).let {
                    if (it == Color.Unspecified) {
                        if (backgroundColor.isDark()) {
                            Color.White
                        } else {
                            Color.Black
                        }
                    } else {
                        it
                    }
                }
            )
        }
    }
}
