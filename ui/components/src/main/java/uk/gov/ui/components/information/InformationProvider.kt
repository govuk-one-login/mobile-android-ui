package uk.gov.ui.components.information

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.ui.components.HeadingSize.H1
import uk.gov.ui.components.R.drawable
import uk.gov.ui.components.R.string
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.ui.components.images.icon.IconParameters

class InformationProvider : PreviewParameterProvider<InformationParameters> {
    override val values: Sequence<InformationParameters> = sequenceOf(
        InformationParameters(
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
        ),
        InformationParameters(
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
}
