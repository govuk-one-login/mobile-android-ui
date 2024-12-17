package uk.gov.android.ui.theme.m3

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import uk.gov.android.ui.theme.swatch.Swatch
import uk.gov.android.ui.theme.swatch.SwatchColor

@Composable
fun GdsTheme(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    shapes: Shapes = Shapes,
    typography: Typography = Typography,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

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
internal const val PALETTE_PADDING = 20
internal const val PALETTE_WIDTH = (SWATCH_SIZE * 4) + (PALETTE_PADDING * 2)
internal const val PALLETTE_HEIGHT = 920

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = PALETTE_WIDTH,
    heightDp = PALLETTE_HEIGHT,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = PALETTE_WIDTH,
    heightDp = PALLETTE_HEIGHT,
)
@Composable
@Suppress("LongMethod")
fun ThemePreview() {
    GdsTheme(
        modifier = Modifier.padding(PALETTE_PADDING.dp),
    ) {
        with(MaterialTheme.colorScheme) {
            val standardColors = listOf(
                SwatchColor(primary, "Primary"),
                SwatchColor(secondary, "Secondary"),
                SwatchColor(tertiary, "Tertiary"),
                SwatchColor(error, "Error"),
            )
            val onStandardColors = listOf(
                SwatchColor(onPrimary, "On Primary", primary),
                SwatchColor(onSecondary, "On Secondary", secondary),
                SwatchColor(onTertiary, "On Tertiary", tertiary),
                SwatchColor(onError, "On Error", error),
            )
            val containerColors = listOf(
                SwatchColor(primaryContainer, "Primary Container"),
                SwatchColor(secondaryContainer, "Secondary Container"),
                SwatchColor(tertiaryContainer, "Tertiary Container"),
                SwatchColor(errorContainer, "Error Container"),
            )
            val onContainerColors = listOf(
                SwatchColor(onPrimaryContainer, "On Primary Container", primaryContainer),
                SwatchColor(onSecondaryContainer, "On Secondary Container", secondaryContainer),
                SwatchColor(onTertiaryContainer, "On Tertiary Container", tertiaryContainer),
                SwatchColor(onErrorContainer, "On Error Container", errorContainer),
            )
            val otherContainerColors = listOf(
                SwatchColor(inversePrimary, "Inverse Primary"),
                SwatchColor(inverseSurface, "Inverse Surface"),
                SwatchColor(inverseOnSurface, "Inverse On Surface"),
                SwatchColor(scrim, "Scrim"),
            )
            val surfaceColors = listOf(
                SwatchColor(surface, "Surface"),
                SwatchColor(surfaceVariant, "Surface Variant"),
                SwatchColor(surfaceTint, "Surface Tint"),
            )
            val onSurfaceColors = listOf(
                SwatchColor(onSurface, "On Surface", surface),
                SwatchColor(onSurfaceVariant, "On Surface Variant", surfaceVariant),
            )
            val outLineColors = listOf(
                SwatchColor(outline, "Outline"),
                SwatchColor(outlineVariant, "Outline Variant"),
            )
            Column {
                Row { standardColors.forEach { Swatch(data = it) } }
                Row { onStandardColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { containerColors.forEach { Swatch(data = it) } }
                Row { onContainerColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { otherContainerColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { surfaceColors.forEach { Swatch(data = it) } }
                Row { onSurfaceColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { outLineColors.forEach { Swatch(data = it) } }
            }
        }
    }
}
