package uk.gov.android.ui.theme.m3

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * [Material3 colours](https://m3.material.io/blog/migrating-material-3)
 *
 * Colors mapped according to the UCD guideline: https://www.figma.com/design/lpR2hC1JaXR93Pqg9DnFkU/Colours-Mobile-GOV.UK-Design-System?node-id=1938-16290&t=IWTNKupKGb9RDH3O-0
 * */
val DarkColorPaletteV2 = darkColorScheme(
    background = Backgrounds.screen.dark,
    onBackground = Text.primary.dark,
    primary = Buttons.primary.dark,
    onPrimary = Buttons.primaryTextAndSymbol.dark,
    secondary = Buttons.secondaryTextAndSymbol.dark,
    // Same as onPrimary to allow compose to resolve colors correctly
    onSecondary = Buttons.primaryTextAndSymbol.dark,
    tertiary = Color.Magenta,
    onTertiary = Color.Magenta,
    error = Buttons.destructive.dark,
    onError = Buttons.destructiveTextAndSymbol.dark,
    primaryContainer = Color.Magenta,
    onPrimaryContainer = Color.Magenta,
    secondaryContainer = Color.Magenta,
    onSecondaryContainer = Color.Magenta,
    tertiaryContainer = Color.Magenta,
    onTertiaryContainer = Color.Magenta,
    errorContainer = Color.Magenta,
    onErrorContainer = Buttons.destructiveNativeButtonText.dark,
    surfaceDim = Color.Magenta,
    surface = Backgrounds.screen.dark,
    surfaceBright = Color.Magenta,
    surfaceContainerLowest = Color.Magenta,
    surfaceContainerLow = Color.Magenta,
    surfaceContainer = Color.Magenta,
    surfaceContainerHigh = Color.Magenta,
    surfaceContainerHighest = Color.Magenta,
    onSurface = Text.primary.dark,
    onSurfaceVariant = Text.secondary.dark,
    outline = Color.Magenta,
    outlineVariant = Dividers.card.dark,
    inverseSurface = Color.Magenta,
    inverseOnSurface = Color.Magenta,
    inversePrimary = Color.Magenta,
    scrim = scrim.dark,
)

/**
 * [Material3 colours](https://m3.material.io/blog/migrating-material-3)
 *
 * Colors mapped according to the UCD guideline: https://www.figma.com/design/lpR2hC1JaXR93Pqg9DnFkU/Colours-Mobile-GOV.UK-Design-System?node-id=1938-16290&t=IWTNKupKGb9RDH3O-0
 * */
val LightColorPaletteV2 = lightColorScheme(
    background = Backgrounds.screen.light,
    onBackground = Text.primary.light,
    primary = Buttons.primary.light,
    onPrimary = Buttons.primaryTextAndSymbol.light,
    secondary = Buttons.secondaryTextAndSymbol.light,
//     Same as onPrimary to allow compose to resolve colors correctly
    onSecondary = Buttons.primaryTextAndSymbol.light,
    tertiary = Color.Magenta,
    onTertiary = Color.Magenta,
    error = Buttons.destructive.light,
    onError = Buttons.destructiveTextAndSymbol.light,
    primaryContainer = Color.Magenta,
    onPrimaryContainer = Color.Magenta,
    secondaryContainer = Color.Magenta,
    onSecondaryContainer = Color.Magenta,
    tertiaryContainer = Color.Magenta,
    onTertiaryContainer = Color.Magenta,
    errorContainer = Color.Magenta,
    onErrorContainer = Buttons.destructiveNativeButtonText.light,
    surfaceDim = Color.Magenta,
    surface = Backgrounds.screen.light,
    surfaceBright = Color.Magenta,
    surfaceContainerLowest = Color.Magenta,
    surfaceContainerLow = Color.Magenta,
    surfaceContainer = Color.Magenta,
    surfaceContainerHigh = Color.Magenta,
    surfaceContainerHighest = Color.Magenta,
    onSurface = Text.primary.light,
    onSurfaceVariant = Text.secondary.light,
    outline = Color.Magenta,
    outlineVariant = Dividers.card.light,
    inverseSurface = Color.Magenta,
    inverseOnSurface = Color.Magenta,
    inversePrimary = Color.Magenta,
    scrim = scrim.light,
)
