package uk.gov.android.ui.components.buttons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.images.icon.IconParameters

class ButtonProvider : PreviewParameterProvider<ButtonParameters> {
    override val values: Sequence<ButtonParameters> = sequenceOf(
        ButtonParameters(
            buttonType = ButtonType.PRIMARY(),
            onClick = {},
            text = R.string.preview__GdsButton__primary
        ),
        ButtonParameters(
            buttonType = ButtonType.PRIMARY(),
            onClick = {},
            text = R.string.preview__GdsButton__primary,
            enabled = false
        ),
        ButtonParameters(
            buttonType = ButtonType.SECONDARY(),
            onClick = {},
            text = R.string.preview__GdsButton__secondary
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.PRIMARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__primary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.SECONDARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__secondary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.PRIMARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    imagePositionAtEnd = false
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__primary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.SECONDARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    imagePositionAtEnd = false
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__secondary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.TERTIARY(),
            onClick = {},
            text = R.string.preview__GdsButton__tertiary
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.TERTIARY(),
                iconParameters = IconParameters(
                    image = R.drawable.ic_external_site,
                    description = R.string.externalSite,
                    backGroundColor = Color.Transparent
                )
            ),
            onClick = {},
            text = R.string.preview__GdsButton__tertiary_icon
        ),
        ButtonParameters(
            buttonType = ButtonType.ICON(
                buttonType = ButtonType.TERTIARY(),
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
