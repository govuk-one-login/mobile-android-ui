package uk.gov.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.ui.components.R.drawable
import uk.gov.ui.components.R.string
import uk.gov.ui.components.images.icon.GdsIcon
import uk.gov.ui.components.images.icon.IconParameters
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.disabled_button
import uk.gov.ui.theme.minimumTouchTarget
import uk.gov.ui.theme.smallPadding

@Composable
fun GdsButton(
    buttonParameters: ButtonParameters,
    colors: Colors = MaterialTheme.colors
) {
    buttonParameters.apply {
        key(this) {
            val buttonColors = buttonType.buttonColour()

            Button(
                colors = buttonColors,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                ),
                modifier = Modifier
                    .semantics(mergeDescendants = true) {}
                    .heightIn(min = minimumTouchTarget)
                    .widthIn(min = minimumTouchTarget)
                    .then(modifier),
                onClick = onClick,
                shape = RoundedCornerShape(0.dp),
                enabled = enabled
            ) {
                buttonContent(buttonParameters, colors).invoke(this)
            }
        }
    }
}

private fun buttonContent(
    parameters: ButtonParameters,
    colors: Colors
): @Composable RowScope.() -> Unit = {
    val buttonColors = parameters.buttonType.buttonColour()

    if (parameters.buttonType is ButtonType.ICON &&
        !parameters.buttonType.iconParameters.imagePositionAtEnd
    ) {
        DisplayIcon(
            iconButtonType = parameters.buttonType,
            colors = colors,
            buttonColors = buttonColors,
            imagePositionAtEnd = false
        )
    }

    Text(
        fontWeight = parameters.buttonType.fontWeight,
        style = parameters.textStyle ?: MaterialTheme.typography.button,
        text = stringResource(id = parameters.text)
    )

    if (parameters.buttonType is ButtonType.ICON &&
        parameters.buttonType.iconParameters.imagePositionAtEnd
    ) {
        DisplayIcon(
            iconButtonType = parameters.buttonType,
            colors = colors,
            buttonColors = buttonColors,
            imagePositionAtEnd = true
        )
    }
}

@Composable
private fun DisplayIcon(
    iconButtonType: ButtonType.ICON,
    colors: Colors,
    buttonColors: ButtonColors,
    imagePositionAtEnd: Boolean
) {
    val modifier = if (imagePositionAtEnd) {
        Modifier
            .padding(start = smallPadding)
    } else {
        Modifier
            .padding(end = smallPadding)
    }
    GdsIcon(
        parameters = iconButtonType.iconParameters.copy(
            backGroundColor = buttonColors.backgroundColor(true).value,
            foreGroundColor = buttonColors.contentColor(true).value,
            modifier = modifier.then(
                iconButtonType.iconParameters.modifier
            )
        ),
        colors = colors
    )
}

@Keep
data class ButtonParameters(
    val buttonType: ButtonType,
    val textStyle: TextStyle? = null,
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    val onClick: () -> Unit,
    @StringRes
    val text: Int
) {
    override fun toString(): String = "GDS Button"
}

@Composable
internal fun primaryButtonColors() = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
    disabledBackgroundColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
)

@Composable
internal fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.secondary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.secondary),
    disabledBackgroundColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
)

sealed class ButtonType(
    val buttonColour: @Composable () -> ButtonColors,
    val fontWeight: FontWeight = FontWeight.Bold
) {
    open class PRIMARY : ButtonType(
        buttonColour = {
            primaryButtonColors()
        }
    )

    open class SECONDARY : ButtonType(
        buttonColour = {
            secondaryButtonColors()
        },
        fontWeight = FontWeight.Light
    )

    data class ICON(
        val buttonType: ButtonType,
        val iconParameters: IconParameters
    ) : ButtonType(
        buttonColour = buttonType.buttonColour,
        fontWeight = buttonType.fontWeight
    )
}

class ButtonProvider : PreviewParameterProvider<ButtonParameters> {
    override val values: Sequence<ButtonParameters> = sequenceOf(
        ButtonParameters(
            buttonType = ButtonType.PRIMARY(),
            onClick = {},
            text = string.preview__GdsButton__primary
        ),
        ButtonParameters(
            buttonType = ButtonType.PRIMARY(),
            onClick = {},
            text = string.preview__GdsButton__primary,
            enabled = false
        ),
        ButtonParameters(
            buttonType = ButtonType.SECONDARY(),
            onClick = {},
            text = string.preview__GdsButton__secondary
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.PRIMARY(),
                iconParameters = IconParameters(
                    image = drawable.ic_external_site,
                    description = string.externalSite
                )
            ),
            onClick = {},
            text = string.preview__GdsButton__primary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.SECONDARY(),
                iconParameters = IconParameters(
                    image = drawable.ic_external_site,
                    description = string.externalSite
                )
            ),
            onClick = {},
            text = string.preview__GdsButton__secondary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.PRIMARY(),
                iconParameters = IconParameters(
                    image = drawable.ic_external_site,
                    description = string.externalSite,
                    imagePositionAtEnd = false
                )
            ),
            onClick = {},
            text = string.preview__GdsButton__primary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.SECONDARY(),
                iconParameters = IconParameters(
                    image = drawable.ic_external_site,
                    description = string.externalSite,
                    imagePositionAtEnd = false
                )
            ),
            onClick = {},
            text = string.preview__GdsButton__secondary_icon
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ButtonPreview(
    @PreviewParameter(ButtonProvider::class)
    buttonParameters: ButtonParameters
) {
    GdsTheme {
        GdsButton(buttonParameters)
    }
}
