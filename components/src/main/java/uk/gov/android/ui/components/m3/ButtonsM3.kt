package uk.gov.android.ui.components.m3

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.images.icon.GdsIconM3
import uk.gov.android.ui.theme.disabled_button
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget
import uk.gov.android.ui.theme.smallPadding

@Composable
fun GdsButtonM3(
    buttonParameters: ButtonParametersM3,
    colors: ColorScheme = MaterialTheme.colorScheme
) {
    buttonParameters.apply {
        key(this) {
            val buttonColors = buttonType.buttonColour()
            Button(
                colors = buttonColors,
                elevation = ButtonDefaults.buttonElevation(
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
    parameters: ButtonParametersM3,
    colors: ColorScheme
): @Composable RowScope.() -> Unit = {
    if (parameters.buttonType is ButtonTypeM3.ICON &&
        !parameters.buttonType.iconParameters.imagePositionAtEnd
    ) {
        DisplayIcon(
            iconButtonType = parameters.buttonType,
            colors = colors,
            imagePositionAtEnd = false,
            parentButtonType = parameters.buttonType.buttonType
        )
    }

    Text(
        fontWeight = parameters.buttonType.fontWeight,
        style = parameters.textStyle ?: MaterialTheme.typography.labelMedium,
        text = stringResource(id = parameters.text)
    )

    if (parameters.buttonType is ButtonTypeM3.ICON &&
        parameters.buttonType.iconParameters.imagePositionAtEnd
    ) {
        DisplayIcon(
            iconButtonType = parameters.buttonType,
            colors = colors,
            imagePositionAtEnd = true,
            parentButtonType = parameters.buttonType.buttonType
        )
    }
}

@Composable
private fun DisplayIcon(
    iconButtonType: ButtonTypeM3.ICON,
    colors: ColorScheme,
    imagePositionAtEnd: Boolean,
    parentButtonType: ButtonTypeM3
) {
    val modifier = if (imagePositionAtEnd) {
        Modifier
            .padding(start = smallPadding)
    } else {
        Modifier
            .padding(end = smallPadding)
    }
    val fgColor = when (parentButtonType) {
        is ButtonTypeM3.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        is ButtonTypeM3.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        else -> MaterialTheme.colorScheme.primary
    }

    GdsIconM3(
        parameters = iconButtonType.iconParameters.copy(
            foreGroundColor = fgColor,
            modifier = modifier.then(
                iconButtonType.iconParameters.modifier
            )
        ),
        colors = colors
    )
}

@Keep
data class ButtonParametersM3(
    val buttonType: ButtonTypeM3,
    val textStyle: TextStyle? = null,
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    val onClick: () -> Unit,
    @StringRes
    val text: Int
) {
    override fun toString(): String = "GDS Button"
}

sealed class ButtonTypeM3(
    val buttonColour: @Composable () -> ButtonColors,
    val fontWeight: FontWeight = FontWeight.Bold
) {
    open class PRIMARY : ButtonTypeM3(
        buttonColour = {
            primaryButtonColorsM3()
        }
    )

    open class SECONDARY : ButtonTypeM3(
        buttonColour = {
            secondaryButtonColorsM3()
        },
        fontWeight = FontWeight.Light
    )

    open class TERTIARY : ButtonTypeM3(
        buttonColour = {
            tertiaryButtonColorsM3()
        },
        fontWeight = FontWeight.Light
    )

    data class ICON(
        val buttonType: ButtonTypeM3,
        val iconParameters: IconParameters
    ) : ButtonTypeM3(
        buttonColour = buttonType.buttonColour,
        fontWeight = buttonType.fontWeight
    )
}

@Composable
internal fun primaryButtonColorsM3() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
    disabledContainerColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
)

@Composable
internal fun secondaryButtonColorsM3() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary),
    disabledContainerColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
)

@Composable
internal fun tertiaryButtonColorsM3() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
)

class ButtonProviderM3 : PreviewParameterProvider<ButtonParametersM3> {
    override val values: Sequence<ButtonParametersM3> = sequenceOf(
        ButtonParametersM3(
            buttonType = ButtonTypeM3.PRIMARY(),
            onClick = {},
            text = R.string.preview__GdsButton__primary
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.PRIMARY(),
            onClick = {},
            text = R.string.preview__GdsButton__primary,
            enabled = false
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.SECONDARY(),
            onClick = {},
            text = R.string.preview__GdsButton__secondary
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.ICON(
                buttonType = ButtonTypeM3.PRIMARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__primary_icon
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.ICON(
                buttonType = ButtonTypeM3.SECONDARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__secondary_icon
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.ICON(
                buttonType = ButtonTypeM3.PRIMARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    imagePositionAtEnd = false,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__primary_icon,
            textStyle = TextStyle()
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.ICON(
                buttonType = ButtonTypeM3.SECONDARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    imagePositionAtEnd = false,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__secondary_icon
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.TERTIARY(),
            onClick = {},
            text = R.string.preview__GdsButton__tertiary
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.ICON(
                buttonType = ButtonTypeM3.TERTIARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__tertiary_icon
        ),
        ButtonParametersM3(
            buttonType = ButtonTypeM3.ICON(
                buttonType = ButtonTypeM3.TERTIARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    imagePositionAtEnd = false,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__tertiary_icon
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
fun ButtonM3Preview(
    @PreviewParameter(ButtonProviderM3::class)
    buttonParameters: ButtonParametersM3
) {
    GdsTheme {
        GdsButtonM3(buttonParameters)
    }
}
