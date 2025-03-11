package uk.gov.android.ui.componentsv2.heading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble

@Deprecated("This should be replaced with the new Heading component")
@Composable
fun GdsHeading(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    val heading = stringResource(R.string.heading)

    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = Typography.displaySmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacingDouble)
            .semantics { contentDescription = "$text $heading" },
        textAlign = textAlign,
    )
}

internal class HeadingParameterPreviewProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "Short Title",
        "Long Title - Lorem ipsum dolor sit amet, consectetur adipiscing elit",
    )
}

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
