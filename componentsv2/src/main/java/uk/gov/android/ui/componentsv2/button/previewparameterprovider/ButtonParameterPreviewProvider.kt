package uk.gov.android.ui.componentsv2.button.previewparameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypePreview
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParameters

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
            R.string.text_button,
            ButtonTypePreview.IconLeading,
        ),
        ButtonParameters(
            text = R.string.disabled_button,
            buttonType = ButtonTypePreview.Primary,
            enabled = false,
        ),
    )
}
