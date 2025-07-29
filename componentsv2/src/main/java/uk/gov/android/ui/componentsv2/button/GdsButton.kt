package uk.gov.android.ui.componentsv2.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.text.GdsAnnotatedString
import uk.gov.android.ui.componentsv2.utils.customBottomShadow
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.buttonContentVertical
import uk.gov.android.ui.theme.m3.Buttons
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.toMappedColors

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

@Composable
private fun setFocusStateColors(
    focusStateEnabled: Boolean,
    buttonType: ButtonType,
) = if (focusStateEnabled) focusStateButtonColors() else buttonType.buttonColors()

@Suppress("ForbiddenComment")
@Composable
private fun setShadowColors(
    buttonType: ButtonType,
    isEnabled: Boolean,
    isInFocus: Boolean,
): Color {
    return if (!isEnabled) {
        Buttons.disabledShadow.toMappedColors()
    } else if (isInFocus) {
        // TODO: Amend once Design provides the correct colors for Focus shadow
        Color.Transparent
    } else {
        when (buttonType) {
            ButtonType.Primary -> Buttons.shadow.toMappedColors()
            ButtonType.Error -> {
                // TODO: Amend once Design provides the correct colors for Button Destructive shadow
                Color.Transparent
            }
            is ButtonType.Icon -> {
                buttonType.shadowColor
            }
            else -> Color.Transparent
        }
    }
}

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
                iconColor = buttonColors.contentColor,
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
    Primary, Secondary, Tertiary, Quaternary, Admin, Error, Custom, Icon
}

@Composable
internal fun ButtonTypePreview.toButtonType(): ButtonType = when (this) {
    ButtonTypePreview.Primary -> ButtonType.Primary
    ButtonTypePreview.Secondary -> ButtonType.Secondary
    ButtonTypePreview.Tertiary -> ButtonType.Tertiary
    ButtonTypePreview.Quaternary -> ButtonType.Quaternary
    ButtonTypePreview.Admin -> ButtonType.Admin
    ButtonTypePreview.Error -> ButtonType.Error
    ButtonTypePreview.Custom -> ButtonType.Custom(
        contentColor = Color.Red,
        containerColor = Color.Cyan,
    )

    ButtonTypePreview.Icon -> ButtonType.Icon(
        buttonColors = ButtonType.Primary.buttonColors(),
        iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
        fontWeight = FontWeight.Bold,
        contentDescription = stringResource(R.string.icon_content_desc),
        shadowColor = Buttons.shadow.toMappedColors(),
    )
}

internal data class ButtonParameters(
    @StringRes
    val text: Int,
    val buttonType: ButtonTypePreview,
    val modifier: Modifier = Modifier,
    val contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    val contentModifier: Modifier = Modifier,
    val enabled: Boolean = true,
)

internal class ButtonParameterPreviewProvider : PreviewParameterProvider<ButtonParameters> {
    override val values: Sequence<ButtonParameters> = sequenceOf(
        ButtonParameters(
            text = R.string.primary_button,
            buttonType = ButtonTypePreview.Primary,
        ),
        ButtonParameters(
            R.string.secondary_button,
            ButtonTypePreview.Secondary,
        ),
        ButtonParameters(
            R.string.tertiary_button,
            ButtonTypePreview.Tertiary,
        ),
        ButtonParameters(
            R.string.quaternary_button,
            ButtonTypePreview.Quaternary,
        ),
        ButtonParameters(
            R.string.admin_button,
            ButtonTypePreview.Admin,
        ),
        ButtonParameters(
            R.string.error_button,
            ButtonTypePreview.Error,
        ),
        ButtonParameters(
            R.string.text_button,
            ButtonTypePreview.Icon,
        ),
        ButtonParameters(
            text = R.string.disabled_button,
            buttonType = ButtonTypePreview.Primary,
            enabled = false,
        ),
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
