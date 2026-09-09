package uk.gov.android.ui.componentsv2.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ContentPreviews
import uk.gov.android.ui.theme.spacingDouble

/**
 * A progress indicator with a label that changes over time to communicate ongoing background
 * processing.
 *
 * @param modifier Compose [Modifier] to apply to the component
 * @param labels the labels shown for each phase
 * @param state the state of the indicator determining the label that is shown
 */
@Composable
fun GdsProgressIndicator(
    modifier: Modifier = Modifier,
    labels: ProgressLabels = GdsProgressIndicatorDefaults.labels(),
    state: GdsProgressIndicatorState = rememberProgressIndicatorState(),
) {
    LaunchedEffect(state) {
        state.runAnimation()
    }

    Column(
        modifier = modifier.padding(horizontal = spacingDouble),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingDouble),
    ) {
        CircularProgressIndicator()
        Text(text = labels.get(state.waitedFor))
    }
}

@Composable
@PreviewLightDark
@ContentPreviews
internal fun GdsProgressIndicatorPreview() = GdsTheme {
    Surface {
        val state = remember {
            GdsProgressIndicatorState(
                initialWaitedFor = ProgressWaitLength.Short,
            )
        }
        GdsProgressIndicator(state = state)
    }
}
