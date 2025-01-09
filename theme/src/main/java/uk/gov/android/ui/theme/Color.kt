package uk.gov.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val disabled_button = Color(0xFFB1B4B6)
val hintTextGrey = Color(0xFF505A5F)

val md_theme_light_background = Color(0xFFFFFFFF)
val md_theme_light_error = Color(0xFFD4351C)
val md_theme_light_primary = Color(0xFF00703C)
val md_theme_light_primaryVariant = Color(0xFF7EDA9A)
val md_theme_light_secondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryVariant = Color(0xFFFFFFFF)
val md_theme_light_surface = Color(0xFF505A5F)
val md_theme_light_surfaceVariant = Color(0xFFF3F2F1)

val md_theme_light_onBackground = Color(0xFF000000)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_onSecondary = Color(0xFF000000)
val md_theme_light_onSurface = Color(0xFF000000)
val mc_theme_light_inverseOnSurface = Color(0xFFF3F2F1)

val md_theme_dark_background = Color(0xFF000000)
val md_theme_dark_error = Color(0xFFD4351C)
val md_theme_dark_primary = Color(0xFF008547)
val md_theme_dark_primaryVariant = Color(0xFF006D3A)
val md_theme_dark_secondary = Color(0xFF000000)
val md_theme_dark_secondaryVariant = Color(0xFF000000)
val md_theme_dark_surface = Color(0xFFB1B4B6)
val md_theme_dark_surfaceVariant = Color(0xFF262626)

val md_theme_dark_onBackground = Color(0xFFFFFFFF)
val md_theme_dark_onError = Color(0xFFFFFFFF)
val md_theme_dark_onPrimary = Color(0xFFFFFFFF)
val md_theme_dark_onSecondary = Color(0xFFFFFFFF)
val md_theme_dark_onSurface = Color(0xFFFFFFFF)
val mc_theme_dark_inverseOnSurface = Color(0xFF262626)

val md_theme_red = Color(0xFFD4351C)

val adminButton = Color(0xFF4C2C92)

val bannerSpotColor = Color(0x40000000)

// m3 colors
val m3_primary = Color(0xFF008547)
val m3_error = Color(0xFFD4351C)
val m3_disabled = disabled_button
val m3_onDisabled = Color.White

// m3 light colors
val m3_theme_light_background = Color.White
val m3_theme_light_primary = m3_primary
val m3_theme_light_secondary = Color.White
val m3_theme_light_tertiary = md_theme_light_primaryVariant
val m3_theme_light_error = m3_error

val m3_theme_light_onBackground = Color.Black
val m3_theme_light_onPrimary = Color.White
val m3_theme_light_onSecondary = Color.Black
val m3_theme_light_onTertiary = Color.White
val m3_theme_light_onError = Color.White

// m3 dark colors
val m3_theme_dark_background = Color.Black
val m3_theme_dark_primary = m3_primary
val m3_theme_dark_secondary = Color.Black
val m3_theme_dark_tertiary = md_theme_dark_primaryVariant
val m3_theme_dark_error = m3_error

val m3_theme_dark_onBackground = Color.White
val m3_theme_dark_onPrimary = Color.White
val m3_theme_dark_onSecondary = Color.White
val m3_theme_dark_onTertiary = Color.White
val m3_theme_dark_onError = Color.White

/**
 * Allows to change the [Theme] colors if requirements are different from the standard.
 */
@Composable
fun inverseOnSurface(darkMode: Color, lightMode: Color): Color {
    return if (isSystemInDarkTheme()) {
        darkMode
    } else {
        lightMode
    }
}
