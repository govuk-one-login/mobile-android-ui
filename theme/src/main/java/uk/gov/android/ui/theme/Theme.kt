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
import uk.gov.android.ui.theme.ext.isDark
import uk.gov.android.ui.theme.ext.toHexString

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
    surface = md_theme_dark_surface,
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
    surface = md_theme_light_surface,
)

@Composable
fun GdsTheme(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
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
                .then(modifier),
        ) {
            content()
        }
    }
}

internal const val SWATCH_SIZE = 200
internal const val SWATCH_SIZE_HALF = SWATCH_SIZE / 2
internal const val SWATCH_COL_4 = (SWATCH_SIZE * 4)
internal const val SWATCH_COL_3 = (SWATCH_COL_4) / 3
internal const val SWATCH_COL_2 = SWATCH_COL_4 / 2
internal const val SWATCH_PADDING = 16
internal const val PALETTE_PADDING = 20
internal const val PALETTE_WIDTH = SWATCH_COL_4 + (PALETTE_PADDING * 2)
internal const val PALLETTE_HEIGHT = 600

@Preview(
    backgroundColor = 0xFFFFFFFF,
    heightDp = PALLETTE_HEIGHT,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = PALETTE_WIDTH,
)
@Preview(
    backgroundColor = 0xFF000000,
    heightDp = PALLETTE_HEIGHT,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = PALETTE_WIDTH,
)
@Composable
@Suppress("LongMethod")
fun Theme() {
    GdsTheme(
        modifier = Modifier.padding(PALETTE_PADDING.dp),
    ) {
        Column {
            Row {
                Swatch(MaterialTheme.colors.primary, "Primary")
                Swatch(MaterialTheme.colors.primaryVariant, "Primary Variant")
                Swatch(MaterialTheme.colors.secondary, "Secondary")
                Swatch(MaterialTheme.colors.secondaryVariant, "Secondary Variant")
            }
            PalletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colors.background,
                    "Background",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_3,
                )
                Swatch(
                    MaterialTheme.colors.surface,
                    "Surface",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_3,
                )
                Swatch(
                    MaterialTheme.colors.error,
                    "Error",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_3,
                )
            }
            PalletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colors.onPrimary,
                    "On Primary",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_2,
                )
                Swatch(
                    MaterialTheme.colors.onSecondary,
                    "On Secondary",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_2,
                )
            }
            PalletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colors.onBackground,
                    "On Background",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_3,
                )
                Swatch(
                    MaterialTheme.colors.onSurface,
                    "On Surface",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_3,
                )
                Swatch(
                    MaterialTheme.colors.onError,
                    "On Error",
                    SWATCH_SIZE_HALF,
                    SWATCH_COL_3,
                )
            }
        }
    }
}

@Composable
private fun Swatch(
    backgroundColor: Color,
    label: String,
    height: Int = 200,
    width: Int = 200,
) {
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .height(height.dp)
            .width(width.dp)
            .padding(SWATCH_PADDING.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
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
                },
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
                },
            )
        }
    }
}

@Composable
private fun PalletteSpacer() {
    Spacer(
        modifier = Modifier.height(PALETTE_PADDING.dp),
    )
}
