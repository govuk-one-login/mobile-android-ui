package uk.gov.android.ui.theme.m3

import androidx.compose.ui.graphics.Color

// Admin button
val adminButton = Color(0xFF4C2C92)

object Backgrounds {
    val screen = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
    val card = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val list = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val topBar = ColorPair(Color(0xFFFFFFFF), Color(0xFF0B0C0C))
    val topBarScrolled = ColorPair(Color(0xFFF3F2F1), Color(0xFF262626))
    val navigationBar = ColorPair(Color(0xFFFFFFFF), Color(0xFF262626))
    val validWallet = ColorPair(Color(0xFFFFFFFF))
    val expiredWallet = ColorPair(Color(0xFFF3F2F1))
    val walletDocumentHeader = ColorPair(Color(0xFFFFFFFF))
    val homeHeader = ColorPair(Color(0xFF1D70B8), Color(0xFF0F385C))
    val veteranCardInformationHeader = ColorPair(Color(0xFF532A45), Color(0xFF532A45))
}

object Buttons {
    val primary = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val primaryIconAndText = ColorPair(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
    val primaryFocusState = ColorPair(Color(0xFFFFDD00), Color(0xFFFFDD00))
    val primaryTextAndIconInFocusState = ColorPair(light = Color(0xFF0B0C0C))
    val secondaryTextAndIcon = ColorPair(light = Color(0xFF00703C), dark = Color(0xFF03CD6E))
    val disableText = ColorPair(light = Color(0xFFB1B4B6))
    val disabledTextAndIcon = Color(0xFFFFFFFF)
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
    val icon = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val success = ColorPair(Color(0xFF00703C), Color(0xFF008547))
    val error = ColorPair(Color(0xFF0B0C0C), Color(0xFFFFFFFF))
    val destructive = ColorPair(Color(0xFFD4351C))
    val spinner = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
}

object Links {
    val link = ColorPair(light = Color(0xFF00703C), dark = Color(0xFF03CD6E))
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
    val selectedBackground = ColorPair(Color(0xFF00703C), Color(0xFF03CD6E))
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
