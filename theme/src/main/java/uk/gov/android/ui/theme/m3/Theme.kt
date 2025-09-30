package uk.gov.android.ui.theme.m3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.swatch.Swatch
import uk.gov.android.ui.theme.swatch.SwatchColor

@Suppress("DEPRECATED")
@Composable
fun GdsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    shapes: Shapes = Shapes,
    typography: Typography = Typography,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorPaletteV2 else LightColorPaletteV2

    CompositionLocalProvider(
        GdsLocalColorScheme provides customColors(),
    ) {
        MaterialTheme(
            colorScheme = colors,
            shapes = shapes,
            typography = typography,
        ) {
            val view = LocalView.current

            if (!view.isInEditMode && view.context is Activity) {
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = Color.Transparent.toArgb()
                    WindowCompat
                        .getInsetsController(window, view)
                        .isAppearanceLightStatusBars = !darkTheme
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    val insetsController = WindowCompat.getInsetsController(window, view)
                    insetsController.isAppearanceLightNavigationBars = !darkTheme
                    insetsController.isAppearanceLightStatusBars = !darkTheme
                }
            }
            content()
        }
    }
}

data class CustomColorsScheme(
    val cardBackground: Color = Color.Unspecified,
    val listBackground: Color = Color.Unspecified,
    val topBarBackground: Color = Color.Unspecified,
    val topBarScrolledBackground: Color = Color.Unspecified,
    val statusOverlayBackground: Color = Color.Unspecified,
    val statusOverlayContent: Color = Color.Unspecified,
    val dialogBackground: Color = Color.Unspecified,
    val menuItemBackground: Color = Color.Unspecified,
    val menuItemHighlightedBackground: Color = Color.Unspecified,
    val topBarTitle: Color = Color.Unspecified,
    val topBarIcon: Color = Color.Unspecified,
    val navigationBarBackground: Color = Color.Unspecified,
    val navigationBarSelectedState: Color = Color.Unspecified,
    val navigationBarContent: Color = Color.Unspecified,
    val selectedRadioButton: Color = Color.Unspecified,
    val unselectedRadioButton: Color = Color.Unspecified,
    val buttonShadow: Color = Color.Unspecified,
    val dividerDefault: Color = Color.Unspecified,
    val primaryButtonHighlighted: Color = Color.Unspecified,
    val secondaryTextAndSymbolButtonHighlighted: Color = Color.Unspecified,
    val disabledButton: Color = Color.Unspecified,
    val disabledButtonContent: Color = Color.Unspecified,
    val disabledButtonShadow: Color = Color.Unspecified,
    val unselectedBackgroundSwitch: Color = Color.Unspecified,
    val unselectedBorderAndHandleSwitch: Color = Color.Unspecified,
    val selectedBackgroundSwitch: Color = Color.Unspecified,
    val selectedHandleSwitch: Color = Color.Unspecified,
    val focusState: Color = Color.Unspecified,
    val focusStateContent: Color = Color.Unspecified,
    val focusButtonHighlighted: Color = Color.Unspecified,
    val focusStateShadow: Color = Color.Unspecified,
    val destructiveButtonHighlighted: Color = Color.Unspecified,
    val destructiveButtonShadow: Color = Color.Unspecified,
    val nativeButtonText: Color = Color.Unspecified,
    val iconDefault: Color = Color.Unspecified,
    val successIcon: Color = Color.Unspecified,
    val destructiveIcon: Color = Color.Unspecified,
    val spinnerIcon: Color = Color.Unspecified,
    val errorIcon: Color = Color.Unspecified,
    val linkDefault: Color = Color.Unspecified,
    val unselectedBackgroundMenu: Color = Color.Unspecified,
    val selectedBackgroundMenu: Color = Color.Unspecified,
)

@SuppressLint("CompositionLocalNaming")
val GdsLocalColorScheme = staticCompositionLocalOf { CustomColorsScheme() }

/**
 * This provides a Custom Color Scheme specific GDS adhering to the Design System.
 * These colours are only the ones that could not be mapped to the Material3 ones.
 */
@Suppress("ForbiddenComment")
@Composable
private fun customColors() = CustomColorsScheme(
    cardBackground = Backgrounds.card.toMappedColors(),
    listBackground = Backgrounds.list.toMappedColors(),
    topBarBackground = Backgrounds.topBar.toMappedColors(),
    topBarScrolledBackground = Backgrounds.topBarScrolled.toMappedColors(),
    statusOverlayBackground = Backgrounds.statusOverlay.toMappedColors(),
    statusOverlayContent = Text.statusOverlay.toMappedColors(),
    dialogBackground = Backgrounds.dialogue.toMappedColors(),
    menuItemBackground = Backgrounds.menuItem.toMappedColors(),
    menuItemHighlightedBackground = Backgrounds.menuItemHighlighted.toMappedColors(),
    topBarTitle = NavigationElements.topBarTitle.toMappedColors(),
    topBarIcon = NavigationElements.topBarIcon.toMappedColors(),
    navigationBarBackground = Backgrounds.navigationBar.toMappedColors(),
    navigationBarSelectedState = NavigationElements.navigationBarSelectedState.toMappedColors(),
    navigationBarContent = NavigationElements.navigationBarIconAndLabel.toMappedColors(),
    // TODO: Once deprecated code is removed, please update these - this is kept only to avoid a breaking change
    selectedRadioButton = Radio.selectedRadioButton.toMappedColors(),
    unselectedRadioButton = Radio.unselectedRadioButton.toMappedColors(),
    primaryButtonHighlighted = Buttons.primaryHighlighted.toMappedColors(),
    secondaryTextAndSymbolButtonHighlighted =
    Buttons.secondaryTextAndSymbolHighlighted.toMappedColors(),
    buttonShadow = Buttons.shadow.toMappedColors(),
    disabledButton = Buttons.disabled.toMappedColors(),
    disabledButtonContent = Buttons.disabledTextAndSymbol.toMappedColors(),
    disabledButtonShadow = Buttons.disabledShadow.toMappedColors(),
    unselectedBackgroundSwitch = Switch.unselectedBackground.toMappedColors(),
    unselectedBorderAndHandleSwitch = Switch.unselectedBorderAndHandle.toMappedColors(),
    selectedBackgroundSwitch = Switch.selectedBackground.toMappedColors(),
    selectedHandleSwitch = Switch.selectedHandle.toMappedColors(),
    dividerDefault = Dividers.default.toMappedColors(),
    focusState = Buttons.focusState.toMappedColors(),
    focusStateContent = Buttons.focusStateTextAndSymbol.toMappedColors(),
    focusButtonHighlighted = Buttons.focusStateHighlighted.toMappedColors(),
    focusStateShadow = Buttons.focusStateShadow.toMappedColors(),
    destructiveButtonHighlighted = Buttons.destructiveHighlighted.toMappedColors(),
    destructiveButtonShadow = Buttons.destructiveShadow.toMappedColors(),
    nativeButtonText = Buttons.nativeButtonText.toMappedColors(),
    iconDefault = Icons.default.toMappedColors(),
    successIcon = Icons.success.toMappedColors(),
    destructiveIcon = Icons.destructive.toMappedColors(),
    spinnerIcon = Icons.spinner.toMappedColors(),
    errorIcon = Icons.error.toMappedColors(),
    linkDefault = Links.default.toMappedColors(),
    selectedBackgroundMenu = Menu.selectedBackground.toMappedColors(),
    unselectedBackgroundMenu = Menu.unselectedBackground.toMappedColors(),
)

internal const val SWATCH_SIZE = 200
internal const val PALETTE_PADDING = 20
internal const val PALETTE_WIDTH_V2 = (SWATCH_SIZE * 7) + (PALETTE_PADDING * 2)
internal const val PALETTE_HEIGHT_V2 = 1700

@ExcludeFromJacocoGeneratedReport
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
fun ThemeV2Material3Preview() {
    GdsTheme {
        with(MaterialTheme.colorScheme) {
            // Used existing mappings
            val backgroundColors = listOf(
                SwatchColor(background, "Background - Background Android Screen"),
                SwatchColor(onBackground, "On Background - Text Primary"),
            )

            val usedStandardMaterialColors = listOf(
                SwatchColor(primary, "Primary - Button Primary"),
                SwatchColor(secondary, "Secondary - Button Secondary"),
                SwatchColor(error, "Error - Button Destructive"),
            )
            val usedOnStandardMaterialColors = listOf(
                SwatchColor(onPrimary, "On Primary - Button Primary text"),
                SwatchColor(onSecondary, "On Secondary NOT USED"),
                SwatchColor(onError, "On Error - Button Destructive Text"),
            )

            val usedContainerMaterialColors = listOf(
                SwatchColor(
                    onErrorContainer,
                    "On Error Container - Destructive Native Button Text",
                    Color.White,
                ),
            )

            val usedAdditionalMaterialColors = listOf(
                SwatchColor(surface, "Surface - Same as Background"),
                SwatchColor(
                    outlineVariant,
                    "Outline Variant - Divider/ Separator Line Android Card",
                ),
                SwatchColor(scrim, "Scrim - Used within Dialog (30% opacity)"),
            )

            // Unused Material3 colours
            val usedOnAdditionalMaterialColors = listOf(
                SwatchColor(onSurface, "On Surface - Same as onBackground"),
                SwatchColor(onSurfaceVariant, "On Surface Variant - Secondary Text"),
            )

            val unusedStandardMaterialColors = listOf(
                SwatchColor(tertiary, "Tertiary NOT USED", Color.Black),
                SwatchColor(
                    onTertiary,
                    "On Tertiary NOT USED",
                    Color.Black,
                ),
            )

            val unusedStandardContainerMaterialColors = listOf(
                SwatchColor(
                    primaryContainer,
                    "Primary Container NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    secondaryContainer,
                    "Secondary Container NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    tertiaryContainer,
                    "Tertiary Container NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    errorContainer,
                    "Error Container NOT USED",
                    Color.Black,
                ),
            )

            val unusedOnStandardContainerMaterialColors = listOf(
                SwatchColor(
                    onPrimaryContainer,
                    "On Primary Container NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    onSecondaryContainer,
                    "On Secondary Container - NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    onTertiaryContainer,
                    "On Tertiary Container NOT USED",
                    Color.Black,
                ),
            )

            val unusedContainerMaterialColors = listOf(
                SwatchColor(
                    surfaceContainerLowest,
                    "Surface Container Lowest NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    surfaceContainerLow,
                    "Surface Container Low NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    surfaceContainer,
                    "Surface Container - NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    surfaceContainerHigh,
                    "Surface Container High NOT USED",
                    Color.Black,
                ),
                SwatchColor(
                    surfaceContainerHighest,
                    "Surface Container Highest NOT USED",
                    Color.Black,
                ),
            )

            val unusedAdditionalMaterialColors = listOf(
                SwatchColor(outline, "Outline NOT USED", Color.Black),
                SwatchColor(inversePrimary, "Inverse Primary NOT USED", Color.Black),
                SwatchColor(inverseSurface, "Inverse Surface NOT USED", Color.Black),
                SwatchColor(inverseOnSurface, "Inverse On Surface NOT USED", Color.Black),
                SwatchColor(surfaceDim, "Surface Dim NOT USED", Color.Black),
                SwatchColor(surfaceBright, "Surface Bright NOt USED", Color.Black),
            )

            Column {
                Text(
                    "Material 3 Mappings to GDS Colors",
                    textAlign = TextAlign.Center,
                    style = Typography.displayMedium,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { backgroundColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { usedStandardMaterialColors.forEach { Swatch(data = it) } }
                Row { usedOnStandardMaterialColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { usedContainerMaterialColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { usedAdditionalMaterialColors.forEach { Swatch(data = it) } }
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { usedOnAdditionalMaterialColors.forEach { Swatch(data = it) } }
                Text(
                    "Unused Material3 Colors",
                    textAlign = TextAlign.Center,
                    style = Typography.displayMedium,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(PALETTE_PADDING.dp))
                Row { unusedStandardMaterialColors.forEach { Swatch(data = it) } }
                Row { unusedContainerMaterialColors.forEach { Swatch(data = it) } }
                Row { unusedStandardContainerMaterialColors.forEach { Swatch(data = it) } }
                Row { unusedOnStandardContainerMaterialColors.forEach { Swatch(data = it) } }
                Row { unusedAdditionalMaterialColors.forEach { Swatch(data = it) } }
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
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
@Suppress("LongMethod", "CyclomaticComplexMethod")
fun ThemeV2CustomPreview() {
    GdsTheme {
        // Custom colors
        val backgroundCustomColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(cardBackground, "Backgrounds - Card"),
                SwatchColor(listBackground, "Backgrounds - List"),
                SwatchColor(topBarBackground, "Backgrounds - Top Bar"),
                SwatchColor(topBarScrolledBackground, "Backgrounds - Top Bar Scrolled"),
                SwatchColor(navigationBarBackground, "Backgrounds - Navigation Bar"),
            )
        }

        val backgroundCustomColorsPt2 = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(statusOverlayBackground, "Backgrounds - Status Overlay"),
                SwatchColor(dialogBackground, "Backgrounds - Dialog"),
                SwatchColor(menuItemBackground, "Backgrounds - Menu Item"),
                SwatchColor(menuItemHighlightedBackground, "Backgrounds - Menu Item Highlighted/ Tapped"),
            )
        }

        val contentCustomColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(statusOverlayContent, "Text - Status Overlay Content"),
            )
        }

        val buttonsColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(focusState, "Buttons - Focus State"),
                SwatchColor(focusStateContent, "Buttons - Focus State Text/ Content"),
                SwatchColor(disabledButton, "Buttons - Disabled Button"),
                SwatchColor(disabledButtonContent, "Buttons - Disabled Text/ Content"),
                SwatchColor(nativeButtonText, "Buttons - Native Text/ Content"),
            )
        }

        val buttonsShadowColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(buttonShadow, "Buttons Shadow - Default"),
                SwatchColor(disabledButtonShadow, "Buttons Shadow - Disabled"),
                SwatchColor(focusStateShadow, "Buttons - Focus State Shadow"),
                SwatchColor(destructiveButtonShadow, "Buttons - Destructive Button Shadow"),
            )
        }

        val iconsAndLinksColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(iconDefault, "Icons - Default"),
                SwatchColor(successIcon, "Icons - Success"),
                SwatchColor(destructiveIcon, "Icons - Destructive"),
                SwatchColor(spinnerIcon, "Icons - Spinner"),
                SwatchColor(errorIcon, "Icons - Error"),
                SwatchColor(linkDefault, "Links - Default"),
            )
        }

        val navigationElementsColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(topBarTitle, "Navigation Elements - Top Bar Title"),
                SwatchColor(topBarIcon, "Navigation Elements - Top Bar Icon"),
                SwatchColor(navigationBarContent, "Navigation Elements - Navigation Bar Icon and Label"),
                SwatchColor(navigationBarSelectedState, "Navigation Elements - Navigation Bar Selected State"),
            )
        }

        val selectionAndSwitchColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(unselectedRadioButton, "Radio - Unselected Radio Button"),
                SwatchColor(selectedRadioButton, "Radio - Selected Radio Button"),
                SwatchColor(selectedBackgroundSwitch, "Switch - Selected Background"),
                SwatchColor(selectedHandleSwitch, "Switch - Selected Handle"),
                SwatchColor(unselectedBackgroundSwitch, "Switch - Unselected Background"),
                SwatchColor(unselectedBorderAndHandleSwitch, "Switch - Unselected Border and Handle"),
            )
        }

        val dividersCustomColors = with(GdsLocalColorScheme.current) {
            listOf(
                SwatchColor(dividerDefault, "Divider - Default"),
            )
        }

        Column {
            Text(
                "Custom Colors",
                textAlign = TextAlign.Center,
                style = Typography.displayMedium,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Background Common Colours",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { backgroundCustomColors.forEach { Swatch(data = it) } }
            Row { backgroundCustomColorsPt2.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Text Content Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { contentCustomColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Buttons Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { buttonsColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Buttons Shadow Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { buttonsShadowColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Icons and Links Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { iconsAndLinksColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Navigation Elements Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { navigationElementsColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Radio and Switch Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { selectionAndSwitchColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
            Text(
                "Divider Colors",
                style = Typography.headlineSmall,
                color = Color.White,
            )
            Row { dividersCustomColors.forEach { Swatch(data = it) } }
            Spacer(Modifier.height(PALETTE_PADDING.dp))
        }
    }
}
