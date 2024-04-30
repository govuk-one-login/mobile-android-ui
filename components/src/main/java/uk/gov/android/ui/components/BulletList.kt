package uk.gov.android.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Text
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
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.m3.Heading
import uk.gov.android.ui.components.m3.HeadingSize
import uk.gov.android.ui.theme.mc_theme_dark_inverseOnSurface
import uk.gov.android.ui.theme.mc_theme_light_inverseOnSurface
import uk.gov.android.ui.theme.smallPadding

@Composable
@Suppress("LongMethod")
fun GdsBulletList(
    bulletListParameters: BulletListParameters
) {
    bulletListParameters.apply {
        val background =
            if (highlight == true) {
                if (isSystemInDarkTheme()) {
                    mc_theme_dark_inverseOnSurface
                } else {
                    mc_theme_light_inverseOnSurface
                }
            } else {
                colors.background
            }

        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .background(
                    background
                )
                .then(
                    colModifier
                ),
            horizontalAlignment = colAlignment
        ) {
            bulletListParameters.title?.let { title ->
                Heading(
                    modifier = headingModifier,
                    padding = headingPadding,
                    size = headingSize,
                    text = title,
                    textAlign = textAlign,
                    backgroundColor = background
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
                Row {
                    Text(
                        color = color ?: colors.contentColorFor(colors.background),
                        text = "\u2022",
                        textAlign = TextAlign.Center,
                        style = textStyle ?: MaterialTheme.typography.body1,
                        modifier = Modifier.width(indent)
                    )
                    Text(
                        color = color ?: colors.contentColorFor(colors.background),
                        modifier = textModifier,
                        style = textStyle ?: MaterialTheme.typography.body1,
                        text = it,
                        textAlign = textAlign
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
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
    val colAlignment: Alignment.Horizontal = Alignment.Start,
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val textAlign: TextAlign = TextAlign.Start,
    val headingSize: HeadingSize = HeadingSize.H4(),
    val headingModifier: Modifier = Modifier.fillMaxWidth(),
    val headingPadding: PaddingValues = PaddingValues(
        start = smallPadding,
        end = smallPadding
    )
) {
    override fun toString(): String = this::class.java.simpleName
}

class BulletListProvider : PreviewParameterProvider<BulletListParameters> {
    override val values: Sequence<BulletListParameters> = sequenceOf(
        BulletListParameters(
            title = R.string.preview__GdsHeading__subTitle1,
            contentText = GdsContentText.GdsContentTextString(
                text = arrayOf(R.string.preview__GdsContent__oneLine_0).toIntArray()
            )
        ),
        BulletListParameters(
            title = R.string.preview__GdsHeading__subTitle2,
            contentText = GdsContentText.GdsContentTextString(
                text = arrayOf(
                    R.string.preview__GdsContent__oneLine_0,
                    R.string.preview__GdsContent__twoLine_0
                ).toIntArray()
            )
        ),
        BulletListParameters(
            highlight = true,
            title = R.string.preview__GdsHeading__subTitle1,
            contentText = GdsContentText.GdsContentTextString(
                text = arrayOf(R.string.preview__GdsContent__oneLine_0).toIntArray()
            )
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview(
    @PreviewParameter(BulletListProvider::class)
    bulletListParameters: BulletListParameters
) {
    GdsBulletList(
        bulletListParameters
    )
}
