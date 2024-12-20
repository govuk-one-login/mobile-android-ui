package uk.gov.android.ui.theme.m3

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.contentColorFor
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

@Composable
fun GdsTheme(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    shapes: Shapes = Shapes,
    typography: Typography = Typography,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        typography = typography,
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
internal const val SWATCH_PADDING = 16
internal const val PALETTE_PADDING = 20
internal const val PALETTE_WIDTH = (SWATCH_SIZE * 4) + (PALETTE_PADDING * 2)
internal const val PALLETTE_HEIGHT = 920

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = PALETTE_WIDTH,
    heightDp = PALLETTE_HEIGHT,
)
@Preview(
    backgroundColor = 0xFF000000,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = PALETTE_WIDTH,
    heightDp = PALLETTE_HEIGHT,
)
@Composable
@Suppress("LongMethod")
fun Theme() {
    GdsTheme(
        modifier = Modifier.padding(PALETTE_PADDING.dp),
    ) {
        Column {
            Row {
                Swatch(
                    MaterialTheme.colorScheme.primary,
                    "Primary",
                )
                Swatch(
                    MaterialTheme.colorScheme.secondary,
                    "Secondary",
                )
                Swatch(
                    MaterialTheme.colorScheme.tertiary,
                    "Tertiary",
                )
                Swatch(
                    MaterialTheme.colorScheme.error,
                    "Error",
                )
            }
            Row {
                Swatch(
                    MaterialTheme.colorScheme.onPrimary,
                    "On Primary",
                    color = MaterialTheme.colorScheme.primary,
                )
                Swatch(
                    MaterialTheme.colorScheme.onSecondary,
                    "On Secondary",
                    color = MaterialTheme.colorScheme.secondary,
                )
                Swatch(
                    MaterialTheme.colorScheme.onTertiary,
                    "On Tertiary",
                    color = MaterialTheme.colorScheme.tertiary,
                )
                Swatch(
                    MaterialTheme.colorScheme.onError,
                    "On Error",
                    color = MaterialTheme.colorScheme.error,
                )
            }
            PaletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colorScheme.primaryContainer,
                    "Primary Container",
                )
                Swatch(
                    MaterialTheme.colorScheme.secondaryContainer,
                    "Secondary Container",
                )
                Swatch(
                    MaterialTheme.colorScheme.tertiaryContainer,
                    "Tertiary Container",
                )
                Swatch(
                    MaterialTheme.colorScheme.errorContainer,
                    "Error Container",
                )
            }
            Row {
                Swatch(
                    MaterialTheme.colorScheme.inversePrimary,
                    "Inverse Primary",
                )
                Swatch(
                    MaterialTheme.colorScheme.inverseSurface,
                    "Inverse Surface",
                )
                Swatch(
                    MaterialTheme.colorScheme.inverseOnSurface,
                    "Inverse On Surface",
                )
                Swatch(
                    MaterialTheme.colorScheme.scrim,
                    "Scrim",
                )
            }
            PaletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colorScheme.onPrimaryContainer,
                    "On Primary Container",
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
                Swatch(
                    MaterialTheme.colorScheme.onSecondaryContainer,
                    "On Secondary Container",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                )
                Swatch(
                    MaterialTheme.colorScheme.onTertiaryContainer,
                    "On Tertiary Container",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                )
                Swatch(
                    MaterialTheme.colorScheme.onErrorContainer,
                    "On Error Container",
                    color = MaterialTheme.colorScheme.errorContainer,
                )
            }
            PaletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colorScheme.surface,
                    "Surface",
                )
                Swatch(
                    MaterialTheme.colorScheme.surfaceVariant,
                    "Surface Variant",
                )
                Swatch(
                    MaterialTheme.colorScheme.surfaceTint,
                    "Surface Tint",
                )
            }
            Row {
                Swatch(
                    MaterialTheme.colorScheme.onSurface,
                    "On Surface",
                    color = MaterialTheme.colorScheme.surface,
                )
                Swatch(
                    MaterialTheme.colorScheme.onSurfaceVariant,
                    "On Surface Variant",
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            PaletteSpacer()
            Row {
                Swatch(
                    MaterialTheme.colorScheme.outline,
                    "Outline",
                )
                Swatch(
                    MaterialTheme.colorScheme.outlineVariant,
                    "Outline Variant",
                )
            }
        }
    }
}

@Composable
private fun Swatch(
    backgroundColor: Color,
    label: String,
    height: Int = SWATCH_SIZE_HALF,
    width: Int = SWATCH_SIZE,
    color: Color = Color.Unspecified,
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
                color = if (color != Color.Unspecified) {
                    color
                } else {
                    textColorForColor(MaterialTheme.colorScheme.contentColorFor(backgroundColor))
                },
            )
            Text(
                text = backgroundColor.toHexString(),
                color = if (color == Color.Unspecified) {
                    color
                } else {
                    textColorForColor(MaterialTheme.colorScheme.contentColorFor(backgroundColor))
                },
            )
        }
    }
}

@Composable
private fun textColorForColor(color: Color) = if (color == Color.Unspecified) {
    if (color.isDark()) Color.White else Color.Black
} else {
    color
}

@Composable
private fun PaletteSpacer() {
    Spacer(
        modifier = Modifier.height(PALETTE_PADDING.dp),
    )
}
