package uk.gov.android.ui.components.buttons

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.images.icon.GdsIcon
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget
import uk.gov.android.ui.theme.smallPadding

@Composable
fun GdsButton(
    buttonParameters: ButtonParameters,
    colors: Colors = MaterialTheme.colors,
) {
    buttonParameters.apply {
        key(this) {
            val buttonColors = buttonType.buttonColour()

            Button(
                colors = buttonColors,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                ),
                modifier = Modifier
                    .semantics(mergeDescendants = true) {}
                    .heightIn(min = minimumTouchTarget)
                    .widthIn(min = minimumTouchTarget)
                    .then(modifier),
                onClick = onClick,
                shape = RoundedCornerShape(0.dp),
                enabled = enabled,
            ) {
                buttonContent(buttonParameters, colors).invoke(this)
            }
        }
    }
}

private fun buttonContent(
    parameters: ButtonParameters,
    colors: Colors,
): @Composable RowScope.() -> Unit = {
    val buttonColors = parameters.buttonType.buttonColour()

    if (parameters.buttonType is ButtonType.ICON &&
        !parameters.buttonType.iconParameters.imagePositionAtEnd
    ) {
        DisplayIcon(
            iconButtonType = parameters.buttonType,
            colors = colors,
            buttonColors = buttonColors,
            imagePositionAtEnd = false,
        )
    }

    Text(
        fontWeight = parameters.buttonType.fontWeight,
        style = parameters.textStyle ?: MaterialTheme.typography.button,
        text = stringResource(id = parameters.text),
        textAlign = TextAlign.Center,
    )

    if (parameters.buttonType is ButtonType.ICON &&
        parameters.buttonType.iconParameters.imagePositionAtEnd
    ) {
        DisplayIcon(
            iconButtonType = parameters.buttonType,
            colors = colors,
            buttonColors = buttonColors,
            imagePositionAtEnd = true,
        )
    }
}

@Composable
private fun DisplayIcon(
    iconButtonType: ButtonType.ICON,
    colors: Colors,
    buttonColors: ButtonColors,
    imagePositionAtEnd: Boolean,
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
                iconButtonType.iconParameters.modifier,
            ),
        ),
        colors = colors,
    )
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun ButtonPreview(
    @PreviewParameter(ButtonProvider::class)
    buttonParameters: ButtonParameters,
) {
    GdsTheme {
        GdsButton(buttonParameters)
    }
}
