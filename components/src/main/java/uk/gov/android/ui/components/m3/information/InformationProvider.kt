package uk.gov.android.ui.components.m3.information

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R.drawable
import uk.gov.android.ui.components.R.string
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.HeadingSize
import uk.gov.android.ui.components.m3.content.ContentParameters

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
                headingSize = HeadingSize.H1()
            ),
            iconParameters = IconParameters(
                foreGroundColor = Color.Unspecified,
                image = drawable.ic_error
            )
        )
    )
}
