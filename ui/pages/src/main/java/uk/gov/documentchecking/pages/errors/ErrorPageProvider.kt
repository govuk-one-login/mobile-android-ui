package uk.gov.documentchecking.pages.errors

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.ui.components.ButtonParameters
import uk.gov.ui.components.ButtonType.ICON
import uk.gov.ui.components.ButtonType.PRIMARY
import uk.gov.ui.components.ButtonType.SECONDARY
import uk.gov.ui.components.HeadingSize.H1
import uk.gov.ui.components.R.drawable
import uk.gov.ui.components.R.string
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.ui.components.images.icon.IconParameters
import uk.gov.ui.components.information.InformationParameters

class ErrorPageProvider : PreviewParameterProvider<ErrorPageParameters> {
    override val values: Sequence<ErrorPageParameters> = sequenceOf(
        ErrorPageParameters(
            primaryButtonParameters = ButtonParameters(
                buttonType = PRIMARY(),
                onClick = {},
                text = string.preview__GdsButton__primary
            ),
            informationParameters = InformationParameters(
                contentParameters = ContentParameters(
                    resource = listOf(
                        GdsContentTextString(
                            text = arrayOf(string.preview__GdsContent__oneLine_0).toIntArray()
                        )
                    )
                ),
                iconParameters = IconParameters(
                    foreGroundColor = Color.Unspecified,
                    image = drawable.ic_error
                )
            )
        ),
        ErrorPageParameters(
            primaryButtonParameters = ButtonParameters(
                buttonType = PRIMARY(),
                onClick = {},
                text = string.preview__GdsButton__primary
            ),
            secondaryButtonParameters = ButtonParameters(
                buttonType = ICON(
                    buttonType = SECONDARY(),
                    iconParameters = IconParameters(
                        foreGroundColor = Color.Unspecified,
                        image = drawable.ic_external_site
                    )
                ),
                onClick = {},
                text = string.preview__GdsButton__secondary
            ),
            informationParameters = InformationParameters(
                contentParameters = ContentParameters(
                    resource = listOf(
                        GdsContentTextString(
                            text = arrayOf(string.preview__GdsContent__oneLine_0).toIntArray()
                        )
                    )
                ),
                iconParameters = IconParameters(
                    foreGroundColor = Color.Unspecified,
                    image = drawable.ic_error
                )
            )
        ),
        ErrorPageParameters(
            primaryButtonParameters = ButtonParameters(
                buttonType = PRIMARY(),
                onClick = {},
                text = string.preview__GdsButton__primary
            ),
            secondaryButtonParameters = ButtonParameters(
                buttonType = ICON(
                    buttonType = SECONDARY(),
                    iconParameters = IconParameters(
                        foreGroundColor = Color.Unspecified,
                        image = drawable.ic_external_site
                    )
                ),
                onClick = {},
                text = string.preview__GdsButton__secondary
            ),
            informationParameters = InformationParameters(
                contentParameters = ContentParameters(
                    resource = listOf(
                        GdsContentTextString(
                            subTitle = string.preview__GdsHeading__subTitle1,
                            text = arrayOf(string.preview__GdsContent__oneLine_0).toIntArray()
                        )
                    ),
                    headingSize = H1()
                ),
                iconParameters = IconParameters(
                    foreGroundColor = Color.Unspecified,
                    image = drawable.ic_error
                )
            )
        )
    )
}
