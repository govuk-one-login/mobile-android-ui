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
import androidx.compose.material3.Text
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

@Deprecated(
    message = "This theme does not align with the Design System - Use GdsThemeV2",
    replaceWith = ReplaceWith(
        "GdsThemeV2",
        imports = arrayOf("uk.gov.android.ui.theme.m3.GdsThemeV2"),
    ),
    level = DeprecationLevel.WARNING,
)
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

@Composable
fun GdsThemeV2(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    shapes: Shapes = Shapes,
    typography: Typography = Typography,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorPaletteV2 else LightColorPaletteV2

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
internal const val PALETTE_HEIGHT = 1100
internal const val PALETTE_WIDTH_V2 = (SWATCH_SIZE * 6) + (PALETTE_PADDING * 2)
internal const val PALETTE_HEIGHT_V2 = 1750

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = PALETTE_WIDTH,
    heightDp = PALETTE_HEIGHT,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = PALETTE_WIDTH,
    heightDp = PALETTE_HEIGHT,
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

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = PALETTE_WIDTH_V2,
    heightDp = PALETTE_HEIGHT_V2,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = PALETTE_WIDTH_V2,
    heightDp = PALETTE_HEIGHT_V2,
)
@Composable
@Suppress("LongMethod")
fun ThemeV2Preview() {
    GdsThemeV2(
        modifier = Modifier.padding(PALETTE_PADDING.dp),
    ) {
        with(MaterialTheme.colorScheme) {
            val backgroundColors = listOf(
                SwatchColor(background, "Background - Background Android Screen"),
                SwatchColor(onBackground, "On Background - Text Primary"),
            )
            val standardColors = listOf(
                SwatchColor(primary, "Primary - Button Primary"),
                SwatchColor(secondary, "Secondary - Button Secondary"),
                SwatchColor(tertiary, "Tertiary - Button Secondary"),
                SwatchColor(error, "Error - Button Destructive"),
            )
            val onStandardColors = listOf(
                SwatchColor(onPrimary, "On Primary - Button Primary text"),
                SwatchColor(onSecondary, "On Secondary - Button Secondary Text"),
                SwatchColor(onTertiary, "On Tertiary - Same as Button Secondary Text"),
                SwatchColor(onError, "On Error - Button Destructive Text"),
            )
            val containerColors = listOf(
                SwatchColor(primaryContainer, "Primary Container NO DESIGN COLOUR"),
                SwatchColor(secondaryContainer, "Secondary Container - Navigation Selected"),
                SwatchColor(tertiaryContainer, "Tertiary Container NO DESIGN COLOUR"),
                SwatchColor(errorContainer, "Error Container NO DESIGN COLOUR", background),
            )
            val onContainerColors = listOf(
                SwatchColor(onPrimaryContainer, "On Primary Container NO DESIGN COLOUR", background),
                SwatchColor(onSecondaryContainer, "On Secondary Container - Navigation Text and Icon"),
                SwatchColor(onTertiaryContainer, "On Tertiary Container NO DESIGN COLOUR", background),
                SwatchColor(onErrorContainer, "On Error Container - Destructive OS Button Text"),
            )

            val surfaceColors = listOf(
                SwatchColor(surfaceDim, "Surface Dim NO DESIGN COLOUR"),
                SwatchColor(surface, "Surface NO DESIGN COLOUR"),
                SwatchColor(surfaceBright, "Surface Bright NO DESIGN COLOUR"),
            )

            val surfaceContainersColors = listOf(
                SwatchColor(surfaceContainerLowest, "Surface Container Lowest NO DESIGN COLOUR"),
                SwatchColor(surfaceContainerLow, "Surface Container Low - Android Card"),
                SwatchColor(surfaceContainer, "Surface Container - Top (App Bars)"),
                SwatchColor(surfaceContainerHigh, "Surface Container High - Dialog"),
                SwatchColor(surfaceContainerHighest, "Surface Container Highest - Switch/ Toggle)"),
            )
            val onSurfaceColors = listOf(
                SwatchColor(onSurface, "On Surface NO DESIGN COLOUR", background),
                SwatchColor(onSurfaceVariant, "On Surface Variant - Secondary Text"),
            )
            val outLineColors = listOf(
                SwatchColor(outline, "Outline NO DESIGN COLOUR"),
                SwatchColor(outlineVariant, "Outline Variant - Divider/ Separator Line Android Card"),
            )

            val otherContainerColors = listOf(
                SwatchColor(inversePrimary, "Inverse Primary NO DESIGN COLOUR"),
                SwatchColor(inverseSurface, "Inverse Surface NO DESIGN COLOUR"),
                SwatchColor(inverseOnSurface, "Inverse On Surface NO DESIGN COLOUR"),
                SwatchColor(scrim, "Scrim - Used within Dialog (30% opacity)"),
            )

            val staticColors = listOf(
                SwatchColor(m3_theme_primaryFixed, "Primary Fixed - Button Focus"),
                SwatchColor(m3_theme_secondaryFixed, "Secondary Fixed - Button Disabled"),
                SwatchColor(m3_theme_tertiaryFixed, "Tertiary Fixed - Wallet Document Background"),
            )

            val staticTextColors = listOf(
                SwatchColor(m3_theme_onPrimaryFixed, "Primary Fixed - Button Focus Text"),
                SwatchColor(m3_theme_onSecondaryFixed, "Secondary Fixed - Button Disabled Text"),
                SwatchColor(m3_theme_onTertiaryFixed, "Tertiary Fixed - onPrimary"),
            )

            Column {
                Row { backgroundColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { standardColors.forEach { Swatch(data = it) } }
                Row { onStandardColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { containerColors.forEach { Swatch(data = it) } }
                Row(modifier = Modifier.height(150.dp)) {
                    onContainerColors.forEach { Swatch(data = it) }
                }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { surfaceColors.forEach { Swatch(data = it) } }
                Row { surfaceContainersColors.forEach { Swatch(data = it) } }
                Row { onSurfaceColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { outLineColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { otherContainerColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Text(
                    "Fixed/ Static Colours - DOESN'T use the Material3 " +
                        "- would need to be used variables directly - see Colors.kt",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { staticColors.forEach { Swatch(data = it) } }
                Row { staticTextColors.forEach { Swatch(data = it) } }
            }
        }
    }
}
