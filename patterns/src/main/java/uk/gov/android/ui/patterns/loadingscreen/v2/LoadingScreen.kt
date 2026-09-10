package uk.gov.android.ui.patterns.loadingscreen.v2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicator
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicatorDefaults
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ContentPreviews
import uk.gov.android.ui.theme.spacingDouble

/**
 * The Loading Screen is a container for [GdsProgressIndicator] that fills the whole
 * screen and positions the progress indicator in the centre.
 *
 * It is just for convenience and not an official Design System pattern.
 *
 * @param modifier
 * @param progressIndicator the [GdsProgressIndicator] to display
 *
 * @sample LoadingScreenDefaultSample
 * @sample LoadingScreenCustomSample
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    progressIndicator: @Composable () -> Unit = ::GdsProgressIndicator,
) = Surface(
    modifier = modifier.fillMaxSize(),
) {
    Box(
        modifier = Modifier.padding(spacingDouble),
        contentAlignment = Alignment.Center,
    ) {
        progressIndicator()
    }
}

@Composable
internal fun LoadingScreenDefaultSample() {
    LoadingScreen()
}

@Composable
internal fun LoadingScreenCustomSample() {
    LoadingScreen {
        GdsProgressIndicator(
            labels = GdsProgressIndicatorDefaults.labels(
                short = "Custom message",
                long = "Custom message, loading for a long time",
                longer = "Custom message, loading for a very long time",
            ),
        )
    }
}

@Composable
@PreviewLightDark
@ContentPreviews
internal fun LoadingScreenPreview() = GdsTheme {
    LoadingScreenDefaultSample()
}
