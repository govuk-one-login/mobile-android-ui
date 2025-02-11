package uk.gov.android.ui.components.m3

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.theme.smallPadding

@Deprecated("Use componentsv2.bulletedlist.GdsBulletedList instead")
@Composable
@Suppress("LongMethod")
fun GdsBulletList(
    bulletListParameters: BulletListParameters,
) {
    bulletListParameters.apply {
        val background =
            if (highlight == true) {
                MaterialTheme.colorScheme.inverseOnSurface
            } else {
                MaterialTheme.colorScheme.background
            }
        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .background(
                    color = background,
                )
                .then(
                    colModifier,
                ),
            horizontalAlignment = colAlignment,
        ) {
            bulletListParameters.title?.let { title ->
                Heading(
                    modifier = headingModifier,
                    padding = headingPadding,
                    size = headingSize,
                    text = title,
                    textAlign = textAlign,
                    backgroundColor = background,
                ).generate()
            }

            when (contentText) {
                is GdsContentText.GdsContentTextString ->
                    contentText.text.map {
                        stringResource(id = it)
                    }.toTypedArray()

                is GdsContentText.GdsContentTextArray ->
                    stringArrayResource(id = contentText.text)
            }.forEach {
                Row(
                    verticalAlignment = verticalAlignment,
                ) {
                    Text(
                        color = color
                            ?: MaterialTheme.colorScheme.contentColorFor(
                                MaterialTheme.colorScheme.background,
                            ),
                        text = "\u2022",
                        textAlign = TextAlign.Center,
                        style = textStyle ?: MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(indent),
                    )
                    Text(
                        color = color
                            ?: MaterialTheme.colorScheme.contentColorFor(
                                MaterialTheme.colorScheme.background,
                            ),
                        modifier = textModifier,
                        style = textStyle ?: MaterialTheme.typography.bodyLarge,
                        text = it,
                        textAlign = textAlign,
                    )
                }
            }
        }
    }
}

data class BulletListParameters(
    val title: Int? = null,
    val contentText: GdsContentText,
    val color: Color? = null,
    val highlight: Boolean? = null,
    val textStyle: TextStyle? = null,
    val indent: Dp = 50.dp,
    val colModifier: Modifier = Modifier.padding(),
    val colAlignment: Alignment.Horizontal = Alignment.Start,
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val textAlign: TextAlign = TextAlign.Start,
    val headingSize: HeadingSize = HeadingSize.HeadlineSmall(),
    val headingModifier: Modifier = Modifier.fillMaxWidth(),
    val headingPadding: PaddingValues = PaddingValues(
        start = smallPadding,
        end = smallPadding,
    ),
    val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
) {
    override fun toString(): String = this::class.java.simpleName
}

class BulletListProvider : PreviewParameterProvider<BulletListParameters> {
    override val values: Sequence<BulletListParameters> = sequenceOf(
        BulletListParameters(
            title = R.string.preview__GdsHeading__subTitle1,
            contentText = GdsContentText.GdsContentTextString(
                text = arrayOf(R.string.preview__GdsContent__oneLine_0).toIntArray(),
            ),
        ),
        BulletListParameters(
            title = R.string.preview__GdsHeading__subTitle2,
            contentText = GdsContentText.GdsContentTextString(
                text = arrayOf(
                    R.string.preview__GdsContent__oneLine_0,
                    R.string.preview__GdsContent__twoLine_0,
                ).toIntArray(),
            ),
        ),
        BulletListParameters(
            highlight = true,
            title = R.string.preview__GdsHeading__subTitle1,
            contentText = GdsContentText.GdsContentTextString(
                text = arrayOf(R.string.preview__GdsContent__oneLine_0).toIntArray(),
            ),
        ),
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(BulletListProvider::class)
    bulletListParameters: BulletListParameters,
) {
    GdsBulletList(
        bulletListParameters,
    )
}
