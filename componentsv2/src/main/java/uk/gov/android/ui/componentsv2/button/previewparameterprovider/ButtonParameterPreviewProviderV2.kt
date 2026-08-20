package uk.gov.android.ui.componentsv2.button.previewparameterprovider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypePreview
import uk.gov.android.ui.componentsv2.button.GdsButtonDefaults
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.theme.smallPadding

private val BUTTON_RADIUS = 12.dp
internal class ButtonParameterPreviewProviderV2 : PreviewParameterProvider<ButtonParametersV2> {
    private val parameters = listOf(
        ButtonParametersV2(
            text = "Primary button",
            buttonType = ButtonTypePreview.Primary,
            modifier = Modifier.fillMaxWidth(),
        ),
        ButtonParametersV2(
            text = "Secondary button",
            buttonType = ButtonTypePreview.Secondary,
        ),
        ButtonParametersV2(
            text = "Tertiary button",
            buttonType = ButtonTypePreview.Tertiary,
        ),
        ButtonParametersV2(
            text = "Quaternary button",
            buttonType = ButtonTypePreview.Quaternary,
        ),
        ButtonParametersV2(
            text = "Admin button",
            buttonType = ButtonTypePreview.Admin,
        ),
        ButtonParametersV2(
            text = "Error button",
            buttonType = ButtonTypePreview.Error,
        ),
        ButtonParametersV2(
            text = "Primary button (icon)",
            buttonType = ButtonTypePreview.PrimaryIcon,
        ),
        ButtonParametersV2(
            text = "Primary button (icon)",
            buttonType = ButtonTypePreview.PrimaryIconLeading,
        ),
        ButtonParametersV2(
            text = "Primary button (disabled)",
            buttonType = ButtonTypePreview.Primary,
            enabled = false,
        ),
        ButtonParametersV2(
            text = "Primary button (loading)",
            buttonType = ButtonTypePreview.Primary,
            enabled = false,
            loading = true,
            modifier = Modifier.fillMaxWidth()
        ),
        ButtonParametersV2(
            text = "Primary button (rounded)",
            buttonType = ButtonTypePreview.Primary,
            shape = GdsButtonDefaults.customRoundedShape(BUTTON_RADIUS),
            modifier = Modifier.fillMaxWidth()
        ),
        ButtonParametersV2(
            text = "Secondary button (icon)",
            buttonType = ButtonTypePreview.SecondaryIcon,
        ),
        ButtonParametersV2(
            text = "Custom button",
            buttonType = ButtonTypePreview.Custom,
        ),
        ButtonParametersV2(
            text = "Primary button (disabled, icon)",
            buttonType = ButtonTypePreview.PrimaryIcon,
            enabled = false,
        ),
        ButtonParametersV2(
            text = "Secondary button (destructive)",
            buttonType = ButtonTypePreview.ErrorSecondary,
        ),
        ButtonParametersV2(
            text = "Secondary button (full width, icon)",
            buttonType = ButtonTypePreview.SecondaryIcon,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            contentModifier = Modifier.fillMaxWidth()
                .padding(horizontal = smallPadding),
            contentPosition = Arrangement.Start,
        ),
    )

    override val values: Sequence<ButtonParametersV2> = parameters.asSequence()

    override fun getDisplayName(index: Int): String = parameters[index].toString()
}
