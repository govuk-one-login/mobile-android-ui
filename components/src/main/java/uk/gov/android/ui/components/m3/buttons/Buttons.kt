package uk.gov.android.ui.components.m3.buttons

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.m3.images.icon.GdsIcon
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget
import uk.gov.android.ui.theme.smallPadding

@Composable
fun GdsButton(
    buttonParameters: ButtonParameters,
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
    parameters: ButtonParameters,
    colors: ColorScheme
): @Composable RowScope.() -> Unit = {
    if (parameters.buttonType is ButtonType.ICON &&
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
        text = stringResource(id = parameters.text),
        textAlign = TextAlign.Center
    )

    if (parameters.buttonType is ButtonType.ICON &&
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
    iconButtonType: ButtonType.ICON,
    colors: ColorScheme,
    imagePositionAtEnd: Boolean,
    parentButtonType: ButtonType
) {
    val modifier = if (imagePositionAtEnd) {
        Modifier
            .padding(start = smallPadding)
    } else {
        Modifier
            .padding(end = smallPadding)
    }
    val fgColor = when (parentButtonType) {
        is ButtonType.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        is ButtonType.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        else -> MaterialTheme.colorScheme.primary
    }

    GdsIcon(
        parameters = iconButtonType.iconParameters.copy(
            foreGroundColor = fgColor,
            modifier = modifier.then(
                iconButtonType.iconParameters.modifier
            )
        ),
        colors = colors
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
    @PreviewParameter(ButtonProvider::class)
    buttonParameters: ButtonParameters
) {
    GdsTheme {
        GdsButton(buttonParameters)
    }
}
