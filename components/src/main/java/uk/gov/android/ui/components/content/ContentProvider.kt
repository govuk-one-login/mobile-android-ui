package uk.gov.android.ui.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R.array
import uk.gov.android.ui.components.R.string
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextArray
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.android.ui.theme.spacingSingle

class ContentProvider : PreviewParameterProvider<ContentParameters> {
    override val values: Sequence<ContentParameters> = sequenceOf(
        ContentParameters(
            resource = listOf(
                GdsContentTextString(
                    text = arrayOf(
                        string.preview__GdsContent__oneLine_0
                    ).toIntArray()
                )
            )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextString(
                    subTitle = string.preview__GdsHeading__subTitle1,
                    text = arrayOf(
                        string.preview__GdsContent__oneLine_0
                    ).toIntArray()
                )
            ),
            textAlign = TextAlign.Center
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextString(
                    text = arrayOf(
                        string.preview__GdsContent__twoLine_0,
                        string.preview__GdsContent__twoLine_1
                    ).toIntArray()
                )
            )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextString(
                    subTitle = string.preview__GdsHeading__subTitle1,
                    text = arrayOf(
                        string.preview__GdsContent__oneSection_0,
                        string.preview__GdsContent__twoLine_1
                    ).toIntArray()
                ),
                GdsContentTextString(
                    text = arrayOf(
                        string.preview__GdsContent__twoSection_0,
                        string.preview__GdsContent__twoLine_0
                    ).toIntArray()
                )
            )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextArray(
                    text = array.preview__GdsContent__array_oneLine
                )
            )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextArray(
                    text = array.preview__GdsContent__array_twoLine
                )
            )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextArray(
                    subTitle = string.preview__GdsHeading__subTitle1,
                    text = array.preview__GdsContent__array_twoSection_0
                ),
                GdsContentTextArray(
                    subTitle = string.preview__GdsHeading__subTitle2,
                    text = array.preview__GdsContent__array_twoSection_1
                )
            )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextArray(
                    text = array.preview__GdsContent__array_oneLine
                )
            ),
            color = Color.Black,
            modifier = Modifier
                .background(
                    color = Color.Red
                )
                .padding(
                    all = spacingSingle
                ),
            textModifier = Modifier
                .background(
                    color = Color.Cyan
                )
                .padding(
                    all = spacingSingle
                )
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextString(
                    text = arrayOf(
                        string.preview__GdsContent__oneLine_0
                    ).toIntArray()
                )
            ),
            color = Color.Red
        ),
        ContentParameters(
            resource = listOf(
                GdsContentTextString(
                    subTitle = string.preview__GdsHeading__subTitle1,
                    subTitle2 = string.preview__GdsHeading__subTitle2,
                    text = arrayOf(
                        string.preview__GdsContent__oneLine_0
                    ).toIntArray()
                )
            ),
            textAlign = TextAlign.Center
        )
    )
}
