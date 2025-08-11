package uk.gov.android.ui.componentsv2.warning

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingSingleAndAHalf

@Composable
fun GdsWarningText(
    text: String,
    modifier: Modifier = Modifier,
) {
    val warningText = stringResource(id = R.string.warning)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) { },
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_error_filled),
            contentDescription = "$warningText\n",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.alignByBaseline(),
        )
        Spacer(Modifier.size(spacingSingleAndAHalf))
        Text(
            text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically),
        )
    }
}

internal class WarningPreviewProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "text",
        listOf(
            "lorem ipsum dolor sit amet consectetur.",
            "Purus aliquam mattis vitae enim mauris.",
            "lorem ipsum dolor sit amet consectetur.",
        ).joinToString(" "),

    )
}

@PreviewLightDark
@Composable
internal fun WarningPreview(
    @PreviewParameter(WarningPreviewProvider::class)
    text: String,
) {
    GdsTheme {
        GdsWarningText(
            text = text,
        )
    }
}
