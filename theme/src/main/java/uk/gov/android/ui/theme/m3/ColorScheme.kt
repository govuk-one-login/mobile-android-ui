package uk.gov.android.ui.theme.m3

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import uk.gov.android.ui.theme.m3_theme_dark_background
import uk.gov.android.ui.theme.m3_theme_dark_error
import uk.gov.android.ui.theme.m3_theme_dark_onBackground
import uk.gov.android.ui.theme.m3_theme_dark_onError
import uk.gov.android.ui.theme.m3_theme_dark_onPrimary
import uk.gov.android.ui.theme.m3_theme_dark_onSecondary
import uk.gov.android.ui.theme.m3_theme_dark_onTertiary
import uk.gov.android.ui.theme.m3_theme_dark_primary
import uk.gov.android.ui.theme.m3_theme_dark_secondary
import uk.gov.android.ui.theme.m3_theme_dark_tertiary
import uk.gov.android.ui.theme.m3_theme_light_background
import uk.gov.android.ui.theme.m3_theme_light_error
import uk.gov.android.ui.theme.m3_theme_light_onBackground
import uk.gov.android.ui.theme.m3_theme_light_onError
import uk.gov.android.ui.theme.m3_theme_light_onPrimary
import uk.gov.android.ui.theme.m3_theme_light_onSecondary
import uk.gov.android.ui.theme.m3_theme_light_onTertiary
import uk.gov.android.ui.theme.m3_theme_light_primary
import uk.gov.android.ui.theme.m3_theme_light_secondary
import uk.gov.android.ui.theme.m3_theme_light_tertiary
import uk.gov.android.ui.theme.mc_theme_dark_inverseOnSurface
import uk.gov.android.ui.theme.mc_theme_light_inverseOnSurface
import uk.gov.android.ui.theme.md_theme_dark_onSurface
import uk.gov.android.ui.theme.md_theme_dark_surface
import uk.gov.android.ui.theme.md_theme_dark_surfaceVariant
import uk.gov.android.ui.theme.md_theme_light_onSurface
import uk.gov.android.ui.theme.md_theme_light_surface
import uk.gov.android.ui.theme.md_theme_light_surfaceVariant

/**
 * [Material3 colours](https://m3.material.io/blog/migrating-material-3)
 *
 * Material                  -> Material3
 * `android:backgroundColor` -> `android:backgroundColor`
 * `colorPrimary`            -> `colorPrimary`
 * `colorOnPrimary`          -> `colorOnPrimary`
 * `N/A`                     -> `colorPrimaryContainer`
 * `N/A`                     -> `colorOnPrimaryContainer`
 * `N/A`                     -> `colorPrimaryInverse`
 * `colorPrimaryVariant`     -> `DEPRECATED`
 * `colorPrimarySurface`     -> `colorSurface`
 * `colorOnPrimarySurface`   -> `colorOnSurface`
 * `colorSecondary`          -> `colorSecondary`
 * `colorOnSecondary`        -> `colorOnSecondary`
 * `N/A`                     -> `colorSecondaryContainer`
 * `N/A`                     -> `colorOnSecondaryContainer`
 * `colorSecondaryVariant`   -> `DEPRECATED`
 * `N/A`                     -> `colorTertiary`
 * `N/A`                     -> `colorOnTertiary`
 * `N/A `                    -> `colorTertiaryContainer`
 * `N/A`                     -> `colorOnTertiaryContainer`
 * `colorError`              -> `colorError`
 * `colorOnError`            -> `colorOnError`
 * `N/A`                     -> `colorErrorContainer`
 * `N/A`                     -> `colorOnErrorContainer`
 * `colorSurface`            -> `colorSurface`
 * `colorOnSurface`          -> `colorOnSurface`
 * `N/A`                     -> `colorSurfaceVariant`
 * `N/A`                     -> `colorOnSurfaceVariant`
 * `N/A`                     -> `colorSurfaceInverse`
 * `N/A`                     -> `colorOnSurfaceInverse`
 * `N/A`                     -> `colorOutline`
 * */
val DarkColorPalette = darkColorScheme(
    background = m3_theme_dark_background,
    onBackground = m3_theme_dark_onBackground,
    primary = m3_theme_dark_primary,
    onPrimary = m3_theme_dark_onPrimary,
    secondary = m3_theme_dark_secondary,
    onSecondary = m3_theme_dark_onSecondary,
    tertiary = m3_theme_dark_tertiary,
    onTertiary = m3_theme_dark_onTertiary,
    error = m3_theme_dark_error,
    onError = m3_theme_dark_onError,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    inverseOnSurface = mc_theme_dark_inverseOnSurface,
)

val LightColorPalette = lightColorScheme(
    background = m3_theme_light_background,
    onBackground = m3_theme_light_onBackground,
    primary = m3_theme_light_primary,
    onPrimary = m3_theme_light_onPrimary,
    secondary = m3_theme_light_secondary,
    onSecondary = m3_theme_light_onSecondary,
    tertiary = m3_theme_light_tertiary,
    onTertiary = m3_theme_light_onTertiary,
    error = m3_theme_light_error,
    onError = m3_theme_light_onError,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    inverseOnSurface = mc_theme_light_inverseOnSurface,
)

/**
 * [Material3 colours](https://m3.material.io/blog/migrating-material-3)
 *
 * GDS Design System          ->  Material3
 * Background Android Screen  -> `backgroundColor`
 *
 * Button Primary             -> `primary`
 *
 * Button Primary Text        -> `onPrimary`
 *
 * Button Secondary           -> `secondary`
 *
 * Button Secondary Text      -> `onSecondary`
 *
 * Button Tertiary (same as Button Secondary) -> `tertiary`
 *
 * Button Tertiary Text       -> `onTertiary`
 *
 * Button Error               -> `error`
 *
 * Button Error Text          -> `onError`
 *
 * N/A                        -> `primaryContainer`
 *
 * N/A                        -> `onPrimaryContainer`
 *
 * Navigation Selected        -> `secondaryContainer`
 *
 * Navigation Text and Icon   -> `onSecondaryContainer`
 *
 * N/A                        -> `tertiaryContainer`
 *
 * N/A                        -> `onTertiaryContainer`
 *
 * N/A                        -> `errorContainer`
 *
 * Destructive OS Button Text -> `onErrorContainer`
 *
 * N/A                        -> `surfaceDim`
 *
 * N/A                        -> `surfaceDim`
 *
 * N/A                        -> `surface`
 *
 * N/A                        -> `surfaceBright`
 *
 * N/A                        -> `surfaceContainerLowest`
 *
 * Android Card               -> `surfaceContainerLow`
 *
 * Top (App Bars)             -> `surfaceContainer`
 *
 * Dialog                     -> `surfaceContainerHigh`
 *
 * Switch/ Toggle             -> `surfaceContainerHighest`
 *
 * N/A                        -> `onSurface`
 *
 * Secondary Text             -> `onSurfaceVariant`
 *
 * N/A                        -> `outline`
 *
 * Divider/ Separator Line (on Android Card) -> `outlineVariant`
 *
 * N/A                     -> `inversePrimary`
 *
 * N/A                     -> `inverseSurface`
 *
 * N/A                     -> `inverseOnSurface`
 *
 * N/A                     -> `scrim`
 *
 * **The FIXED colours would need to be applied manually (for now) to map background to content colours.**
 *
 * N/A                     -> `m3_theme_primaryFixed`
 *
 * N/A                     -> `m3_theme_onPrimaryFixed`
 *
 * N/A                     -> `m3_theme_secondaryFixed`
 *
 * N/A                     -> `m3_theme_onSecondaryFixed`
 *
 * N/A                     -> `m3_theme_tertiaryFixed`
 *
 * N/A                     -> `m3_theme_onTertiaryFixed`
 *
 * */
val DarkColorPaletteV2 = darkColorScheme(
    background = dark_theme_background,
    onBackground = dark_theme_onBackground,
    primary = dark_theme_primary,
    onPrimary = dark_theme_onPrimary,
    secondary = dark_theme_secondary,
    onSecondary = dark_theme_onSecondary,
    tertiary = dark_theme_tertiary,
    onTertiary = dark_theme_onTertiary,
    error = dark_theme_error,
    onError = dark_theme_onError,
    primaryContainer = dark_theme_primaryContainer,
    onPrimaryContainer = dark_theme_onPrimaryContainer,
    secondaryContainer = dark_theme_secondaryContainer,
    onSecondaryContainer = dark_theme_onSecondaryContainer,
    tertiaryContainer = dark_theme_tertiaryContainer,
    onTertiaryContainer = dark_theme_onTertiaryContainer,
    errorContainer = dark_theme_errorContainer,
    onErrorContainer = dark_theme_onErrorContainer,
    surfaceDim = dark_theme_surfaceDim,
    surface = dark_theme_surface,
    surfaceBright = dark_theme_surfaceBright,
    surfaceContainerLowest = dark_theme_surfaceContainerLowest,
    surfaceContainerLow = dark_theme_surfaceContainerLow,
    surfaceContainer = dark_theme_surfaceContainer,
    surfaceContainerHigh = dark_theme_surfaceContainerHigh,
    surfaceContainerHighest = dark_theme_surfaceContainerHighest,
    onSurface = dark_theme_onSurface,
    onSurfaceVariant = dark_theme_onSurfaceVariant,
    outline = dark_theme_outline,
    outlineVariant = dark_theme_outlineVariant,
    inverseSurface = dark_theme_inverseSurface,
    inverseOnSurface = dark_theme_inverseOnSurface,
    inversePrimary = dark_theme_inversePrimary,
    scrim = dark_theme_scrim,
)

/**
 * [Material3 colours](https://m3.material.io/blog/migrating-material-3)
 *
 * GDS Design System          ->  Material3
 * Background Android Screen  -> `backgroundColor`
 *
 * Button Primary             -> `primary`
 *
 * Button Primary Text        -> `onPrimary`
 *
 * Button Secondary           -> `secondary`
 *
 * Button Secondary Text      -> `onSecondary`
 *
 * Button Tertiary (same as Button Secondary) -> `tertiary`
 *
 * Button Tertiary Text       -> `onTertiary`
 *
 * Button Error               -> `error`
 *
 * Button Error Text          -> `onError`
 *
 * N/A                        -> `primaryContainer`
 *
 * N/A                        -> `onPrimaryContainer`
 *
 * Navigation Selected        -> `secondaryContainer`
 *
 * Navigation Text and Icon   -> `onSecondaryContainer`
 *
 * N/A                        -> `tertiaryContainer`
 *
 * N/A                        -> `onTertiaryContainer`
 *
 * N/A                        -> `errorContainer`
 *
 * Destructive OS Button Text -> `onErrorContainer`
 *
 * N/A                        -> `surfaceDim`
 *
 * N/A                        -> `surfaceDim`
 *
 * N/A                        -> `surface`
 *
 * N/A                        -> `surfaceBright`
 *
 * N/A                        -> `surfaceContainerLowest`
 *
 * Android Card               -> `surfaceContainerLow`
 *
 * Top (App Bars)             -> `surfaceContainer`
 *
 * Dialog                     -> `surfaceContainerHigh`
 *
 * Switch/ Toggle             -> `surfaceContainerHighest`
 *
 * N/A                        -> `onSurface`
 *
 * Secondary Text             -> `onSurfaceVariant`
 *
 * N/A                        -> `outline`
 *
 * Divider/ Separator Line (on Android Card) -> `outlineVariant`
 *
 * N/A                     -> `inversePrimary`
 *
 * N/A                     -> `inverseSurface`
 *
 * N/A                     -> `inverseOnSurface`
 *
 * N/A                     -> `scrim`
 *
 * **The FIXED colours would need to be applied manually (for now) to map background to content colours.**
 *
 * N/A                     -> `m3_theme_primaryFixed`
 *
 * N/A                     -> `m3_theme_onPrimaryFixed`
 *
 * N/A                     -> `m3_theme_secondaryFixed`
 *
 * N/A                     -> `m3_theme_onSecondaryFixed`
 *
 * N/A                     -> `m3_theme_tertiaryFixed`
 *
 * N/A                     -> `m3_theme_onTertiaryFixed`
 *
 * */
val LightColorPaletteV2 = lightColorScheme(
    background = light_theme_background,
    onBackground = light_theme_onBackground,
    primary = light_theme_primary,
    onPrimary = light_theme_onPrimary,
    secondary = light_theme_secondary,
    onSecondary = light_theme_onSecondary,
    tertiary = light_theme_tertiary,
    onTertiary = light_theme_onTertiary,
    error = light_theme_error,
    onError = light_theme_onError,
    primaryContainer = light_theme_primaryContainer,
    onPrimaryContainer = light_theme_onPrimaryContainer,
    secondaryContainer = light_theme_secondaryContainer,
    onSecondaryContainer = light_theme_onSecondaryContainer,
    tertiaryContainer = light_theme_tertiaryContainer,
    onTertiaryContainer = light_theme_onTertiaryContainer,
    errorContainer = light_theme_errorContainer,
    onErrorContainer = light_theme_onErrorContainer,
    surfaceDim = light_theme_surfaceDim,
    surface = light_theme_surface,
    surfaceBright = light_theme_surfaceBright,
    surfaceContainerLowest = light_theme_surfaceContainerLowest,
    surfaceContainerLow = light_theme_surfaceContainerLow,
    surfaceContainer = light_theme_surfaceContainer,
    surfaceContainerHigh = light_theme_surfaceContainerHigh,
    surfaceContainerHighest = light_theme_surfaceContainerHighest,
    onSurface = light_theme_onSurface,
    onSurfaceVariant = light_theme_onSurfaceVariant,
    outline = light_theme_outline,
    outlineVariant = light_theme_outlineVariant,
    inverseSurface = light_theme_inverseSurface,
    inverseOnSurface = light_theme_inverseOnSurface,
    inversePrimary = light_theme_inversePrimary,
    scrim = light_theme_scrim,
)
