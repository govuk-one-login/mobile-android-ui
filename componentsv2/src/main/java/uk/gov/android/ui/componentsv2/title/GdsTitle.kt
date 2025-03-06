package uk.gov.android.ui.componentsv2.title

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
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble

@Composable
fun GdsTitle(
    title: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    val heading = stringResource(R.string.heading)

    Text(
        text = title,
        color = MaterialTheme.colorScheme.onBackground,
        style = Typography.displaySmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacingDouble)
            .semantics { contentDescription = "$title $heading" },
        textAlign = textAlign,
    )
}

@Preview
@Composable
private fun PreviewTitle() {
    GdsTheme {
        GdsTitle(
            title = "Title",
        )
    }
}
