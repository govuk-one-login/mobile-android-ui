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
)
