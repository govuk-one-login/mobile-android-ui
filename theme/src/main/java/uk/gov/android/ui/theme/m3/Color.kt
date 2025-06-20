package uk.gov.android.ui.theme.m3

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colors not part of a Group
val adminButton = Color(0xFF4C2C92)
val scrim = ColorPair(Color(0x4D000000))

object Backgrounds {
    val screen = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
    val card = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val list = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val topBar = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
    val topBarScrolled = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val navigationBar = ColorPair(Color(0xFFFFFFFF), Color(0xFF262626))
    val validWalletDocument = ColorPair(Color(0xFFFFFFFF))
    val expiredWalletDocument = ColorPair(Color(0xFFF3F2F1))
    val walletDocumentHeader = ColorPair(Color(0xFFFFFFFF))
    val homeHeader = ColorPair(Color(0xFF1D70B8), Color(0xFF0F385C))
    val veteranCardInformationHeader = ColorPair(Color(0xFF532A45))
}

object Buttons {
    val primary = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val primaryIconAndText = ColorPair(Color(0xFFFFFFFF))
    val primaryFocusState = ColorPair(Color(0xFFFFDD00))
    val focusStateTextIcon = ColorPair(Color(0xFF0B0C0C))
    val secondaryTextAndIcon = ColorPair(Color(0xFF00703C), dark = Color(0xFF03CD6E))
    val disabled = ColorPair(Color(0xFFB1B4B6))
    val disabledTextAndIcon = ColorPair(Color(0xFFFFFFFF))
    val destructive = ColorPair(Color(0xFFD4351C))
    val destructiveTextAndIcon = ColorPair(Color(0xFFFFFFFF))
    val nativeButtonText = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
    val destructiveNativeButtonText = ColorPair(Color(0xFFD4351C), Color(0xFFFF6961))
    val icon = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Dividers {
    val card = ColorPair(Color(0xFF505A5f), Color(0xFFB1B4B6))
    val documentInformation = ColorPair(Color(0xFF6F777B), Color(0xFFFFFFFF))
}

object Borders {
    val walletDocument = ColorPair(Color(0xFFB1B4B6))
    val walletDocumentHeader = ColorPair(Color(0x0DFFFFFF), Color(0x0D000000))
    val statusTag = ColorPair(Color(0xFFD4351C))
    val liveDocumentFooter = ColorPair(Color(0x33FFFFFF), Color(0x33000000))
}

object Icons {
    // icon -> iconDefault in GdsLocalColorScheme
    val icon = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val success = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val error = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val destructive = ColorPair(Color(0xFFD4351C))
    val spinner = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Links {
    val link = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Text {
    val primary = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val secondary = ColorPair(Color(0xFF505A5F), Color(0xFFFFFFFF))
    val statusTag = ColorPair(Color(0xFFD4351C))
    val validWalletDocumentAction = ColorPair(Color(0xFF00703C))
    val invalidWalletDocumentAction = ColorPair(Color(0xFFD4351C))
    val walletDocument = ColorPair(Color(0xFF0B0C0C))
    val liveDocumentFooter = ColorPair(Color(0xFF0B0C0C))
    val liveDocumentHeader = ColorPair(Color(0xFFFFFFFF))
}

object Shadows {
    val walletDocument = ColorPair(Color(0x1F000000))
}

object NavigationElements {
    val topBarTitle = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val topBarIcon = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
    val navigationBarIconAndLabel = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val navigationBarSelectedState = ColorPair(Color(0xFFCCE2DB), Color(0xFF008547))
}

object Selection {
    val unselectedRadioButton = ColorPair(Color(0xFF49454F), Color(0xFFFFFFFF))
    val selectedRadioButton = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Switch {
    val unselectedBackground = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val unselectedBorderAndHandle = ColorPair(Color(0xFF0B0C0C), Color(0xFF000000))
    val selectedBackground = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val selectedHandle = ColorPair(Color(0xFFFFFFFF))
}

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
val light_theme_onBackground = Color(0xFF0B0C0C)

val dark_theme_onBackground = Color(0xFFFFFFFF)

val temporary_list_color_light = Color(0xFFF3F2F1)
val temporary_list_color_dark = Color(0xFF262626)

val temporary_link_color_light = Color(0xFF00703C)
val temporary_link_color_dark = Color(0xFF03CD6E)
