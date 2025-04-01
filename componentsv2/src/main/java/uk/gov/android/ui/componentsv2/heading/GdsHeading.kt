package uk.gov.android.ui.componentsv2.heading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@UnstableDesignSystemAPI
@Composable
fun GdsHeading(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = Typography.displaySmall,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign = TextAlign.Start,
) {
    val heading = stringResource(R.string.heading, text)

    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = style,
        fontWeight = fontWeight,
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = heading
                heading()
            },
        textAlign = textAlign,
    )
}

internal data class HeadingParameters(
    val text: String,
    val style: TextStyle = Typography.displaySmall,
    val fontWeight: FontWeight? = null,
)

internal class HeadingParameterPreviewProvider : PreviewParameterProvider<HeadingParameters> {
    override val values: Sequence<HeadingParameters> = sequenceOf(
        HeadingParameters("Short Title"),
        HeadingParameters("Long Title - Lorem ipsum dolor sit amet, consectetur adipiscing elit"),
        HeadingParameters("Subtitle", style = Typography.bodySmall, fontWeight = FontWeight.W700),
    )
}

@OptIn(UnstableDesignSystemAPI::class)
@PreviewLightDark
@Composable
private fun PreviewTitle(
    @PreviewParameter(HeadingParameterPreviewProvider::class)
    parameters: String,
) {
    GdsTheme {
        GdsHeading(
            text = parameters,
        )
    }
}
