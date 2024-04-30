package uk.gov.android.ui.components.content

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextArray
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.android.ui.theme.GdsTheme

@Composable
fun GdsContent(
    contentParameters: ContentParameters,
    colors: Colors = MaterialTheme.colors
) {
    Column(
        modifier = Modifier
            .background(
                color = colors.background
            )
            .then(
                contentParameters.modifier
            )

    ) {
        contentParameters.apply {
            this.resource.forEach { contentText ->
                Column(
                    modifier = internalColumnModifier
                ) {
                    contentText.subTitle?.let { subTitle ->
                        val headingParameters = HeadingParameters(
                            modifier = headingModifier,
                            padding = headingPadding,
                            size = headingSize,
                            text = subTitle,
                            textAlign = textAlign
                        )
                        GdsHeading(headingParameters)
                    }
                    contentText.subTitle2?.let { subTitle2 ->
                        val headingParameters = HeadingParameters(
                            modifier = headingModifier,
                            padding = headingPadding,
                            size = subHeadingSize,
                            text = subTitle2,
                            textVar = contentText.subTitle2Var,
                            textAlign = textAlign
                        )
                        GdsHeading(headingParameters)
                    }
                    when (contentText) {
                        is GdsContentTextString ->
                            contentText.text.map {
                                stringResource(id = it)
                            }.toTypedArray()

                        is GdsContentTextArray ->
                            stringArrayResource(id = contentText.text)
                    }.forEach {
                        Text(
                            color = color ?: colors.contentColorFor(colors.background),
                            modifier = textModifier.padding(textPadding),
                            style = textStyle ?: MaterialTheme.typography.body1,
                            text = it,
                            textAlign = textAlign
                        )
                    }
                }
            }
        }
    }
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
private fun Preview(
    @PreviewParameter(ContentProvider::class)
    contentParameters: ContentParameters
) {
    GdsTheme {
        GdsContent(
            contentParameters
        )
    }
}
