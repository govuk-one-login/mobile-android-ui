package uk.gov.android.ui.componentsv2.supportingtext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import uk.gov.android.ui.theme.m3.GdsThemeV2
import uk.gov.android.ui.theme.m3.Typography

@Composable
fun GdsSupportingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = Typography.labelMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .fillMaxWidth(),
        textAlign = textAlign,
    )
}

internal class SupportingTextPreviewProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "Short supporting text",
        "Long supporting text - ${LoremIpsum(20).values.first()}",
    )
}

@PreviewLightDark
@Composable
private fun PreviewSupportingText(
    @PreviewParameter(SupportingTextPreviewProvider::class)
    parameters: String,
) {
    GdsThemeV2 {
        GdsSupportingText(parameters)
    }
}
