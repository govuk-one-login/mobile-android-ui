package uk.gov.android.ui.componentsv2.button.previewparameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypePreview
import uk.gov.android.ui.componentsv2.button.GdsButtonDefaults
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2

private val BUTTON_RADIUS = 12.dp
internal class ButtonParameterPreviewProviderV2 : PreviewParameterProvider<ButtonParametersV2> {

    override val values: Sequence<ButtonParametersV2> = sequenceOf(
        ButtonParametersV2(
            text = R.string.primary_button,
            buttonType = ButtonTypePreview.Primary,
        ),
        ButtonParametersV2(
            text = R.string.secondary_button,
            buttonType = ButtonTypePreview.Secondary,
        ),
        ButtonParametersV2(
            text = R.string.tertiary_button,
            buttonType = ButtonTypePreview.Tertiary,
        ),
        ButtonParametersV2(
            text = R.string.quaternary_button,
            buttonType = ButtonTypePreview.Quaternary,
        ),
        ButtonParametersV2(
            text = R.string.admin_button,
            buttonType = ButtonTypePreview.Admin,
        ),
        ButtonParametersV2(
            text = R.string.error_button,
            buttonType = ButtonTypePreview.Error,
        ),
        ButtonParametersV2(
            text = R.string.text_button,
            buttonType = ButtonTypePreview.Icon,
        ),
        ButtonParametersV2(
            text = R.string.text_button,
            buttonType = ButtonTypePreview.IconLeading,
        ),
        ButtonParametersV2(
            text = R.string.disabled_button,
            buttonType = ButtonTypePreview.Primary,
            enabled = false,
        ),
        ButtonParametersV2(
            text = R.string.loading_button,
            buttonType = ButtonTypePreview.Primary,
            enabled = false,
            loading = true,
        ),
        ButtonParametersV2(
            text = R.string.primary_button,
            buttonType = ButtonTypePreview.Primary,
            shape = GdsButtonDefaults.customRoundedShape(BUTTON_RADIUS),
        ),
        ButtonParametersV2(
            text = R.string.text_button,
            buttonType = ButtonTypePreview.IconSecondary,
        ),
        ButtonParametersV2(
            text = R.string.text_button,
            buttonType = ButtonTypePreview.Custom,
        ),
        ButtonParametersV2(
            text = R.string.text_button,
            buttonType = ButtonTypePreview.Icon,
            enabled = false,
        ),
    )
}
