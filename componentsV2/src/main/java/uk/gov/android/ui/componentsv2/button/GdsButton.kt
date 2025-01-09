package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.IconParameters
import uk.gov.android.ui.componentsv2.text.AnnotatedStringParameters
import uk.gov.android.ui.componentsv2.text.GdsAnnotatedString
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.buttonContentVertical
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography

@Composable
fun GdsButton(
    parameters: ButtonParameters,
    onClick: () -> Unit,
) {
    with(parameters) {
        Button(
            colors = buttonType.buttonColour(),
            modifier = modifier.then(
                Modifier
                    .minimumInteractiveComponentSize()
                    .semantics(mergeDescendants = true) { },
            ),
            onClick = onClick,
            shape = RectangleShape,
            enabled = isEnabled,
            contentPadding = getContentPadding(),
        ) {
            Content()
        }
    }
}

data class ButtonParameters(
    val text: Int,
    val buttonType: ButtonType,
    val modifier: Modifier = Modifier,
    val contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    val contentModifier: Modifier = modifier,
    val isEnabled: Boolean = true,
)

@Composable
private fun ButtonParameters.Content() {
    Row(
        modifier = contentModifier,
        horizontalArrangement = contentPosition,
    ) {
        if (buttonType is ButtonType.ICON) {
            val iconParameters = buttonType.iconParameters
            val buttonColors = buttonType.parentButtonType.buttonColour.invoke()
            GdsAnnotatedString(
                parameters = AnnotatedStringParameters(
                    text = text,
                    fontWeight = buttonType.fontWeight,
                    icon = iconParameters.image,
                    iconContentDescription = iconParameters.contentDescription,
                    isIconTrailing = buttonType.isIconTrailing,
                    iconColor = buttonColors.contentColor,
                    iconBackgroundColor = buttonColors.containerColor,
                ),
            )
        } else {
            Text(
                text = stringResource(text),
                fontWeight = buttonType.fontWeight,
                style = Typography.labelLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun ButtonParameters.getContentPadding() =
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

class ButtonParameterPreviewProvider : PreviewParameterProvider<ButtonParameters> {
    override val values: Sequence<ButtonParameters> = sequenceOf(
        ButtonParameters(
            text = R.string.primary_button,
            buttonType = ButtonType.PRIMARY(),
        ),
        ButtonParameters(
            R.string.secondary_button,
            ButtonType.SECONDARY(),
        ),
        ButtonParameters(
            R.string.tertiary_button,
            ButtonType.TERTIARY(),
        ),
        ButtonParameters(
            R.string.quaternary_button,
            ButtonType.QUATERNARY(),
        ),
        ButtonParameters(
            R.string.admin_button,
            ButtonType.ADMIN(),
        ),
        ButtonParameters(
            R.string.error_button,
            ButtonType.ERROR(),
        ),
        ButtonParameters(
            R.string.text_button,
            ButtonType.ICON(
                ButtonType.PRIMARY(),
                IconParameters(
                    R.drawable.ic_external_site,
                    contentDescription = R.string.icon_content_desc,
                ),
            ),
        ),
    )
}

@Composable
@PreviewLightDark
fun ButtonPreview(
    @PreviewParameter(ButtonParameterPreviewProvider::class)
    parameters: ButtonParameters,
) {
    GdsTheme {
        GdsButton(parameters) { }
    }
}
