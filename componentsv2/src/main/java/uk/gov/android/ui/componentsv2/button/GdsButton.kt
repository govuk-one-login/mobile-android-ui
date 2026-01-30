@file:Suppress("TooManyFunctions")

package uk.gov.android.ui.componentsv2.button

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParameters
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.componentsv2.button.previewparameterprovider.ButtonParameterPreviewProvider
import uk.gov.android.ui.componentsv2.button.previewparameterprovider.ButtonParameterPreviewProviderV2
import uk.gov.android.ui.componentsv2.text.GdsAnnotatedString
import uk.gov.android.ui.componentsv2.utils.customBottomShadow
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.buttonContentVertical
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography

/**
 * Gds Button that meets Design System specs
 *
 * @param text - text to be displayed
 * @param buttonType - allows for pre-defined and custom states
 * @param onClick - action when click/ tap is performed
 * @param modifier - default: Modifier that takes the whole width and adds padding horizontally and top of 16.dp - Modifier applied to the button
 * @param contentModifier - default: plain Modifier - Modifier applied to the Text composable within the button container
 * @param contentPosition - default: Absolute Centre - position of content inside the button
 * @param enabled - controls the UI state allowing the button to be tapped or when disabled to not be tappable
 * @param loading - controls if the content should display a Loading Spinner and disable the button
 * @param textAlign - default: Centre - controls the text alignment
 * @param shape - default: Rectangle - controls the button shape
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsButton(
    text: String,
    buttonType: ButtonTypeV2,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    enabled: Boolean = true,
    loading: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    shape: Shape = GdsButtonDefaults.defaultShape,
) {
    var focusStateEnabled by remember { mutableStateOf(false) }
    val colors = setFocusStateColors(focusStateEnabled, buttonType)
    val shadowColor = setShadowColors(buttonType, enabled, focusStateEnabled)
    val interactionSource = remember { MutableInteractionSource() }
    val checkIfDisabled = !(!enabled || loading)
    val loadingContentDescription = stringResource(R.string.loading_content_desc)
    val colour = getRippleColour(buttonType, focusStateEnabled)
    CompositionLocalProvider(
        LocalRippleConfiguration provides GdsButtonDefaults.gdsRippleConfig(colour),
    ) {
        Button(
            colors = colors,
            modifier = modifier
                .customBottomShadow(shadowColor)
                .minimumInteractiveComponentSize()
                .semantics(mergeDescendants = true) {
                    if (loading) {
                        contentDescription = loadingContentDescription
                    }
                }
                .onFocusChanged { focusStateEnabled = it.isFocused },
            onClick = onClick,
            shape = shape,
            enabled = checkIfDisabled,
            interactionSource = interactionSource,
            contentPadding = getContentPadding(
                contentPosition = contentPosition,
            ),
        ) {
            Content(
                text = text,
                buttonType = buttonType,
                loading = loading,
                buttonColors = colors,
                modifier = contentModifier,
                enabled = enabled,
                contentPosition = contentPosition,
                textAlign = textAlign,
            )
        }
    }
}

@Deprecated(
    message = "This is outdated, as specs and references have been changed as per new Deign requirements",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/GdsButton.kt"),
    level = DeprecationLevel.WARNING,
)
@Composable
fun GdsButton(
    text: String,
    buttonType: ButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    enabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
) {
    var focusStateEnabled by remember { mutableStateOf(false) }
    val colors = setFocusStateColors(focusStateEnabled, buttonType)
    val shadowColor = setShadowColors(buttonType, enabled, focusStateEnabled)
    Button(
        colors = colors,
        modifier = modifier
            .customBottomShadow(shadowColor)
            .minimumInteractiveComponentSize()
            .semantics(mergeDescendants = true) { }
            .onFocusChanged { focusStateEnabled = it.isFocused },
        onClick = onClick,
        shape = RectangleShape,
        enabled = enabled,
        contentPadding = getContentPadding(
            contentPosition = contentPosition,
        ),
    ) {
        Content(
            text = text,
            buttonType = buttonType,
            buttonColors = colors,
            modifier = contentModifier,
            contentPosition = contentPosition,
            textAlign = textAlign,
        )
    }
}

@Deprecated(
    message = "This is outdated, as specs and references have been changed as per new Design " +
        "requirements",
    replaceWith = ReplaceWith(
        "java/uk/gov/android/ui/componentsv2/button/GdsButton.kt " +
            "- setFocusStateColors() using ButtonTypeV2",
    ),
    level = DeprecationLevel.WARNING,
)
@Composable
private fun setFocusStateColors(
    focusStateEnabled: Boolean,
    buttonType: ButtonType,
) = if (focusStateEnabled) focusStateButtonColors() else buttonType.buttonColors()

@Deprecated(
    message = "This is outdated, as specs and references have been changed as per new Design " +
        "requirements",
    replaceWith = ReplaceWith(
        "java/uk/gov/android/ui/componentsv2/button/GdsButton.kt " +
            "- setShadowColors() using ButtonTypeV2",
    ),
    level = DeprecationLevel.WARNING,
)
@Composable
private fun setShadowColors(
    buttonType: ButtonType,
    isEnabled: Boolean,
    isInFocus: Boolean,
): Color {
    return if (!isEnabled) {
        GdsLocalColorScheme.current.disabledButtonShadow
    } else if (isInFocus) {
        GdsLocalColorScheme.current.focusStateShadow
    } else {
        when (buttonType) {
            ButtonType.Primary -> GdsLocalColorScheme.current.buttonShadow
            ButtonType.Error -> {
                GdsLocalColorScheme.current.destructiveButtonShadow
            }

            is ButtonType.Icon -> {
                buttonType.shadowColor
            }

            else -> Color.Transparent
        }
    }
}

@Composable
private fun setFocusStateColors(
    focusStateEnabled: Boolean,
    buttonType: ButtonTypeV2,
) = if (focusStateEnabled) GdsButtonDefaults.defaultFocusColors() else buttonType.buttonColors()

@Composable
private fun setShadowColors(
    buttonType: ButtonTypeV2,
    isEnabled: Boolean,
    isInFocus: Boolean,
): Color {
    return if (!isEnabled) {
        GdsLocalColorScheme.current.disabledButtonShadow
    } else if (isInFocus) {
        GdsLocalColorScheme.current.focusStateShadow
    } else {
        when (buttonType) {
            is ButtonTypeV2.Primary -> GdsLocalColorScheme.current.buttonShadow
            is ButtonTypeV2.Destructive -> {
                GdsLocalColorScheme.current.destructiveButtonShadow
            }

            is ButtonTypeV2.Icon -> {
                buttonType.shadowColor
            }

            else -> Color.Transparent
        }
    }
}

@Deprecated(
    message = "This is outdated, as specs and references have been changed as per new Design " +
        "requirements superceeded by the new Content function used for updated GdsButton",
    replaceWith = ReplaceWith(
        "java/uk/gov/android/ui/componentsv2/button/GdsButton.kt " +
            "- Content() using ButtonTypeV2",
    ),
    level = DeprecationLevel.WARNING,
)
@Composable
private fun Content(
    text: String,
    buttonType: ButtonType,
    buttonColors: ButtonColors,
    modifier: Modifier = Modifier,
    contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    textAlign: TextAlign = TextAlign.Center,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = contentPosition,
    ) {
        if (buttonType is ButtonType.Icon) {
            GdsAnnotatedString(
                text = text,
                fontWeight = buttonType.fontWeight,
                icon = buttonType.iconImage,
                iconContentDescription = buttonType.contentDescription,
                isIconTrailing = buttonType.isIconTrailing,
                color = buttonColors.contentColor,
                iconBackgroundColor = buttonColors.containerColor,
                textAlign = textAlign,
            )
        } else {
            Text(
                text = text,
                fontWeight = buttonType.fontWeight(),
                style = Typography.labelLarge,
                textAlign = textAlign,
            )
        }
    }
}

@Composable
private fun Content(
    text: String,
    buttonType: ButtonTypeV2,
    buttonColors: ButtonColors,
    loading: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    textAlign: TextAlign = TextAlign.Center,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = contentPosition,
    ) {
        if (loading) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = text,
                    fontWeight = buttonType.textStyle.fontWeight,
                    style = buttonType.textStyle,
                    textAlign = textAlign,
                    modifier = Modifier.alpha(0f),
                )
                CircularProgressIndicator(
                    modifier = Modifier.size(
                        width = GdsButtonDefaults.spinnerDefaultSize,
                        height = GdsButtonDefaults.spinnerDefaultSize,
                    ),
                    color = buttonColors.disabledContentColor,
                )
            }
        } else if (buttonType is ButtonTypeV2.Icon) {
            GdsAnnotatedString(
                text = text,
                fontWeight = buttonType.textStyle.fontWeight ?: FontWeight.Bold,
                icon = buttonType.icon ?: ImageVector.vectorResource(R.drawable.ic_external_site),
                iconContentDescription = buttonType.icon?.let { buttonType.contentDescription }
                    ?: stringResource(R.string.opens_in_external_browser),
                isIconTrailing = buttonType.isIconTrailing,
                color = if (enabled) buttonColors.contentColor else buttonColors.disabledContentColor,
                iconBackgroundColor = Color.Transparent,
                textAlign = textAlign,
            )
        } else {
            Text(
                text = text,
                fontWeight = buttonType.textStyle.fontWeight,
                style = buttonType.textStyle,
                textAlign = textAlign,
                modifier = Modifier.padding(top = 2.dp),
            )
        }
    }
}

private fun getContentPadding(
    contentPosition: Arrangement.Horizontal,
) =
    if (contentPosition == Arrangement.Start) {
        PaddingValues(
            end = buttonContentHorizontal,
            top = buttonContentVertical,
            bottom = buttonContentVertical,
        )
    } else {
        PaddingValues(
            horizontal = buttonContentHorizontal,
            vertical = buttonContentVertical,
        )
    }

internal enum class ButtonTypePreview {
    Primary, Secondary, Tertiary, Quaternary, Admin, Error, ErrorSecondary, Custom, Icon, IconLeading, IconSecondary
}

@Composable
internal fun ButtonTypePreview.toButtonType(): ButtonType = when (this) {
    ButtonTypePreview.Primary -> ButtonType.Primary
    ButtonTypePreview.Secondary -> ButtonType.Secondary
    ButtonTypePreview.Tertiary -> ButtonType.Tertiary
    ButtonTypePreview.Quaternary -> ButtonType.Quaternary
    ButtonTypePreview.Admin -> ButtonType.Admin
    ButtonTypePreview.Error, ButtonTypePreview.ErrorSecondary -> ButtonType.Error
    ButtonTypePreview.Custom -> ButtonType.Custom(
        contentColor = Color.Red,
        containerColor = Color.Cyan,
    )

    ButtonTypePreview.Icon -> ButtonType.Icon(
        buttonColors = ButtonType.Primary.buttonColors(),
        iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
        fontWeight = FontWeight.Bold,
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = GdsLocalColorScheme.current.buttonShadow,
    )

    ButtonTypePreview.IconSecondary -> ButtonType.Icon(
        buttonColors = ButtonType.Secondary.buttonColors(),
        iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
        fontWeight = FontWeight.Bold,
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = GdsLocalColorScheme.current.buttonShadow,
    )

    ButtonTypePreview.IconLeading -> ButtonType.Icon(
        buttonColors = ButtonType.Primary.buttonColors(),
        iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
        fontWeight = FontWeight.Bold,
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = GdsLocalColorScheme.current.buttonShadow,
        isIconTrailing = false,
    )
}

@Composable
internal fun ButtonTypePreview.toButtonTypeV2(): ButtonTypeV2 = when (this) {
    ButtonTypePreview.Primary -> ButtonTypeV2.Primary()
    ButtonTypePreview.Secondary -> ButtonTypeV2.Secondary()
    ButtonTypePreview.Tertiary -> ButtonTypeV2.Tertiary()
    ButtonTypePreview.Quaternary -> ButtonTypeV2.Quaternary()
    ButtonTypePreview.Admin -> ButtonTypeV2.Admin()
    ButtonTypePreview.Error -> ButtonTypeV2.Destructive()
    ButtonTypePreview.ErrorSecondary -> ButtonTypeV2.SecondaryDestructive()
    ButtonTypePreview.Custom -> ButtonTypeV2.Custom(
        contentColor = Color.Red,
        containerColor = Color.Cyan,
    )

    ButtonTypePreview.Icon -> ButtonTypeV2.Icon(
        buttonColors = GdsButtonDefaults.defaultPrimaryColors(),
        icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
        textStyle = Typography.titleSmall.copy(fontWeight = FontWeight.Light),
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = GdsLocalColorScheme.current.buttonShadow,
    )

    ButtonTypePreview.IconLeading -> ButtonTypeV2.Icon(
        buttonColors = GdsButtonDefaults.defaultPrimaryColors(),
        icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
        textStyle = Typography.titleSmall.copy(fontWeight = FontWeight.Light),
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = GdsLocalColorScheme.current.buttonShadow,
        isIconTrailing = false,
    )

    ButtonTypePreview.IconSecondary -> ButtonTypeV2.Icon(
        buttonColors = GdsButtonDefaults.defaultSecondaryColors(),
        icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
        textStyle = Typography.titleSmall.copy(fontWeight = FontWeight.Light),
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = GdsLocalColorScheme.current.buttonShadow,
    )
}

@Composable
@PreviewLightDark
internal fun ButtonPreview(
    @PreviewParameter(ButtonParameterPreviewProvider::class)
    parameters: ButtonParameters,
) {
    GdsTheme {
        GdsButton(
            text = stringResource(parameters.text),
            buttonType = parameters.buttonType.toButtonType(),
            modifier = parameters.modifier,
            contentPosition = parameters.contentPosition,
            contentModifier = parameters.contentModifier,
            enabled = parameters.enabled,
            onClick = {},
        )
    }
}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
internal fun ButtonPreviewV2(
    @PreviewParameter(ButtonParameterPreviewProviderV2::class)
    parameters: ButtonParametersV2,
) {
    GdsTheme {
        parameters.modifier?.let {
            GdsButton(
                text = stringResource(parameters.text),
                buttonType = parameters.buttonType.toButtonTypeV2(),
                modifier = parameters.modifier,
                contentPosition = parameters.contentPosition,
                contentModifier = parameters.contentModifier,
                enabled = parameters.enabled,
                loading = parameters.loading,
                onClick = {},
                shape = parameters.shape,
            )
        } ?: GdsButton(
            text = stringResource(parameters.text),
            buttonType = parameters.buttonType.toButtonTypeV2(),
            contentPosition = parameters.contentPosition,
            contentModifier = parameters.contentModifier,
            enabled = parameters.enabled,
            loading = parameters.loading,
            onClick = {},
            shape = parameters.shape,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getRippleColour(buttonType: ButtonTypeV2, isInFocus: Boolean): Color {
    return when {
        isInFocus -> GdsLocalColorScheme.current.focusButtonHighlighted
        buttonType is ButtonTypeV2.Primary -> GdsLocalColorScheme.current.primaryButtonHighlighted
        buttonType is ButtonTypeV2.Secondary ->
            GdsLocalColorScheme.current.secondaryTextAndSymbolButtonHighlighted

        buttonType is ButtonTypeV2.Destructive -> GdsLocalColorScheme.current.destructiveButtonHighlighted
        buttonType is ButtonTypeV2.SecondaryDestructive ->
            GdsLocalColorScheme.current.destructiveNativeButtonTextHighlighted
        buttonType is ButtonTypeV2.Icon -> GdsLocalColorScheme.current.primaryButtonHighlighted
        else -> LocalRippleConfiguration.current?.color ?: Color.Unspecified
    }
}
