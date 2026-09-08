package uk.gov.android.ui.componentsv2.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ContentPreviews
import uk.gov.android.ui.theme.spacingDouble

/**
 * The progress indicator communicates background processing state to users.
 *
 * @param modifier Compose [Modifier] to apply to the component
 */
@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .padding(horizontal = spacingDouble),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(spacingDouble),
) {
    CircularProgressIndicator()
    Text(
        text = stringResource(R.string.progress_indicator_text),
    )
}

@Composable
@PreviewLightDark
@ContentPreviews
internal fun ProgressIndicatorPreview() = GdsTheme {
    Surface {
        ProgressIndicator()
    }
}
