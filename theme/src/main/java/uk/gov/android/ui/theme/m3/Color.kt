package uk.gov.android.ui.theme.m3

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Admin button
val adminButton = Color(0xFF4C2C92)
val scrim = ColorPair(Color(0x4D000000))

object Backgrounds {
    val screen = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
    val card = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val list = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val topBar = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
    val topBarScrolled = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val navigationBar = ColorPair(Color(0xFFFFFFFF), Color(0xFF262626))
    val statusOverlay = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val dialogue = ColorPair(Color(0xFFFFFFFF), Color(0xFF262626))
    val menuItem = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val menuItemHighlighted = ColorPair(Color(0xFFE7E6E5), Color(0xFF3C3C3C))
}

object Buttons {
    val primary = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val primaryTextAndSymbol = ColorPair(Color(0xFFFFFFFF))
    val primaryHighlighted = ColorPair(Color(0xFF00542D), Color(0xFF007840))
    val focusState = ColorPair(Color(0xFFFFDD00))
    val focusStateTextAndSymbol = ColorPair(Color(0xFF0B0C0C))
    val focusStateHighlighted = ColorPair(Color(0xFFBFA600))
    val secondaryTextAndSymbol = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
    val secondaryTextAndSymbolHighlighted = ColorPair(Color(0xFF00542D), Color(0xFF02A458))
    val disabled = ColorPair(Color(0xFFB1B4B6))
    val disabledTextAndSymbol = ColorPair(Color(0xFF262626))
    val destructive = ColorPair(Color(0xFFD4351C))
    val destructiveTextAndSymbol = ColorPair(Color(0xFFFFFFFF))
    val destructiveHighlighted = ColorPair(Color(0xFF9F2815))
    val nativeButtonText = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
    val destructiveNativeButtonText = ColorPair(Color(0xFFD4351C), Color(0xFFFF6961))
    val shadow = ColorPair(Color(0xFF002D18))
    val disabledShadow = ColorPair(Color(0xFF939393))
    val focusStateShadow = ColorPair(Color(0xFF665800))
    val destructiveShadow = ColorPair(Color(0xFF55150B))
}

object Dividers {
    val default = ColorPair(Color(0xFF6F777B), Color(0xFFFFFFFF))
    val card = ColorPair(Color(0xFF505A5f), Color(0xFFB1B4B6))
}

object Icons {
    // icon -> iconDefault in GdsLocalColorScheme
    val default = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val success = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val error = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val destructive = ColorPair(Color(0xFFD4351C))
    val spinner = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Links {
    val default = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Text {
    val primary = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val secondary = ColorPair(Color(0xFF505A5F), Color(0xFFFFFFFF))
    val statusOverlay = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
}

object NavigationElements {
    val topBarTitle = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val topBarIcon = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
    val navigationBarIconAndLabel = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val navigationBarSelectedState = ColorPair(Color(0xFFCCE2D8), Color(0xFF008547))
}

@Deprecated(
    message = "This is using the wrong name, please replace with Radios",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/theme/m3/Color.kt - Radios"),
    level = DeprecationLevel.WARNING,
)
object Radio {
    val unselectedRadioButton = ColorPair(Color(0xFF49454F), Color(0xFFFFFFFF))
    val selectedRadioButton = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Radios {
    val unselectedRadioButton = ColorPair(Color(0xFF49454F), Color(0xFFFFFFFF))
    val selectedRadioButton = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Switch {
    val unselectedBackground = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val unselectedBorderAndHandle = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val selectedBackground = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val selectedHandle = ColorPair(Color(0xFFFFFFFF))
}

object Menu {
    val menuItem = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val menuItemHighlighted = ColorPair(Color(0xFFE7E6E5), Color(0xFF3C3C3C))
}

@Suppress("ForbiddenComment")
// TODO: Once we have the GdsSwitch/Toggle we should replace this with that version e.g. GdsSwitch.defaultColors()
@Composable
fun Switch.defaultColors(): SwitchColors = SwitchDefaults.colors().copy(
    uncheckedTrackColor = GdsLocalColorScheme.current.unselectedBackgroundSwitch,
    uncheckedThumbColor = GdsLocalColorScheme.current.unselectedBorderAndHandleSwitch,
    uncheckedBorderColor = GdsLocalColorScheme.current.unselectedBorderAndHandleSwitch,
)

/**
 * Class that allows setting the corresponding dark and light mode colors.
 *
 * When the colors are the same for both environments, then  the dark mode can be left as null as it will default to the light color.
 */
data class ColorPair(
    val light: Color,
    var dark: Color = light,
)

/**
 * Allows for custom/ specific colors to be displayed based on the dynamic light/ dark mode
 */
@Composable
fun customDynamicColor(light: Color, dark: Color): Color {
    return if (isSystemInDarkTheme()) {
        dark
    } else {
        light
    }
}

/**
 * Maps the ColorPair to use correct color when in light or dark mode
 */
@Composable
fun ColorPair.toMappedColors(): Color {
    return customDynamicColor(this.light, this.dark)
}

// Anything below this can be removed once all the repos are using the GdsThemeV2 and all referenced to these colours have been removed

// M3 Colors (all options) - the ones using hex are mapped to the GDS Design System,
// the compose provided colors are not specified/ used

// m3 light_theme colors
val light_theme_background = Color(0xFFFFFFFF)
val light_theme_onBackground = Color(0xFF0B0C0C)

// Primary button and primary button text
val light_theme_primary = Color(0xFF00703C)
val light_theme_onPrimary = Color(0xFFFFFFFF)

// Same color as the background
val light_theme_secondary = light_theme_background

// Secondary button text
val light_theme_onSecondary = Color(0xFF00703C)

// Same color as secondary button (subject to change) - provided by UCD
val light_theme_tertiary = Color(0xFFFFFFFF)
val light_theme_onTertiary = Color(0xFF00703C)

// Button Destructive
val light_theme_error = Color(0xFFD4351C)
val light_theme_onError = Color(0xFFFFFFFF)

// Currently not used
val light_theme_primaryContainer = Color.White
val light_theme_onPrimaryContainer = Color.Black

// E.g selected option background for bottom app bar and its content (e.g. icon)
val light_theme_secondaryContainer = Color(0xFFCCE2D8)
val light_theme_onSecondaryContainer = Color(0xFF0B0C0C)

// Currently not used
val light_theme_tertiaryContainer = Color.White
val light_theme_onTertiaryContainer = Color.Black
val light_theme_errorContainer = Color.Black

// Destructive OS button text
val light_theme_onErrorContainer = Color(0xFFD4351C)

// Currently not used
val light_theme_surfaceDim = Color.White
val light_theme_surface = Color.White
val light_theme_surfaceBright = Color.White
val light_theme_surfaceContainerLowest = Color.White

// E.g GdsCard/ content tile
val light_theme_surfaceContainerLow = Color(0xFFF3F2F1)

// Top app bar background (same as background)
val light_theme_surfaceContainer = Color(0xFFFFFFFF)

// Navigation bars (also used for pop-up dialog
val light_theme_surfaceContainerHigh = Color(0xFFFFFFFF)

// Navigation bars and toggle/ switch
val light_theme_surfaceContainerHighest = Color(0xFFF3F2F1)

// Currently not used
val light_theme_onSurface = Color.Black

// Same as secondary text
val light_theme_onSurfaceVariant = Color(0xFF505A5F)

// Currently not used
val light_theme_outline = Color.White

// Decorative elements (divider -> Design term: separator line)
val light_theme_outlineVariant = Color(0xFF505A5F)

// Currently not used
val light_theme_inverseSurface = Color.White
val light_theme_inverseOnSurface = Color.White
val light_theme_inversePrimary = Color.White

// Dialog background
val light_theme_scrim = Color(0x4D000000)

// m3 dark colors
val dark_theme_background = Color(0xFF0B0C0C)
val dark_theme_onBackground = Color(0xFFFFFFFF)

// Primary button and primary button text
val dark_theme_primary = Color(0xFF008547)
val dark_theme_onPrimary = Color(0xFFFFFFFF)

// Same color as the background
val dark_theme_secondary = Color(0xFF0B0C0C)

// Secondary button text
val dark_theme_onSecondary = Color(0xFF03CD6E)

// Same as secondary (subject to change) - provided by UCD
val dark_theme_tertiary = Color(0xFF0B0C0C)
val dark_theme_onTertiary = Color(0xFF03CD6E)

// Button Destructive
val dark_theme_error = Color(0xFFD4351C)
val dark_theme_onError = Color(0xFFFFFFFF)
val dark_theme_primaryContainer = Color.Black
val dark_theme_onPrimaryContainer = Color.White

// E.g selected option background for bottom app bar and its content (e.g. icon)
val dark_theme_secondaryContainer = Color(0xFF008547)
val dark_theme_onSecondaryContainer = Color(0xFFFFFFFF)

// Currently Not used
val dark_theme_tertiaryContainer = Color.Black
val dark_theme_onTertiaryContainer = Color.White
val dark_theme_errorContainer = Color.White

// Destructive OS button text
val dark_theme_onErrorContainer = Color(0xFFFF6961)

// Currently not used
val dark_theme_surfaceDim = Color.Black
val dark_theme_surface = Color.Black
val dark_theme_surfaceBright = Color.Black
val dark_theme_surfaceContainerLowest = Color.Black

// E.g GdsCard/ content tile
val dark_theme_surfaceContainerLow = Color(0xFF262626)

// Top app bar background (same as background)
val dark_theme_surfaceContainer = Color(0xFF0B0C0C)

// Navigation bars (also used for pop-up dialog
val dark_theme_surfaceContainerHigh = Color(0xFF262626)

// Navigation bars and toggle/ switch
val dark_theme_surfaceContainerHighest = Color(0xFF262626)
val dark_theme_onSurface = Color.White

// Secondary text
val dark_theme_onSurfaceVariant = Color(0xFFFFFFFF)

// Currently not used
val dark_theme_outline = Color.White

// Decorative elements (divider -> Design term: separator line)
val dark_theme_outlineVariant = Color(0xFFB1B4B6)

// Currently not used
val dark_theme_inverseSurface = Color.Black
val dark_theme_inverseOnSurface = Color.Black
val dark_theme_inversePrimary = Color.Black

// Dialog background
val dark_theme_scrim = Color(0x4D000000)

// fixed colors - to be used as directed by design - these would need to be applied
// Button focus state and its text
val m3_theme_primaryFixed = Color(0xFFFFDD00)
val m3_theme_onPrimaryFixed = Color(0xFF0B0C0C)

// Button disabled and its text
val m3_theme_secondaryFixed = Color(0xFFB1B4B6)
val m3_theme_onSecondaryFixed = Color(0xFFFFFFFF)

// Wallet document background
val m3_theme_tertiaryFixed = Color(0xFFF3F2F1)

// Same as onPrimary as default
val m3_theme_onTertiaryFixed = Color(0xFF0B0C0C)

val temporary_list_color_light = Color(0xFFF3F2F1)
val temporary_list_color_dark = Color(0xFF262626)

val temporary_link_color_light = Color(0xFF00703C)
val temporary_link_color_dark = Color(0xFF03CD6E)
