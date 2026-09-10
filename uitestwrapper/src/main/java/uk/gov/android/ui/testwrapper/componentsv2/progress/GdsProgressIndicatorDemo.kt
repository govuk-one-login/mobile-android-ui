package uk.gov.android.ui.testwrapper.componentsv2.progress

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicator
import uk.gov.android.ui.patterns.loadingscreen.v2.LoadingScreen
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ContentPreviews

@Composable
fun GdsProgressIndicatorDemo(modifier: Modifier = Modifier) = LoadingScreen(
    modifier = modifier,
) {
    GdsProgressIndicator(
        modifier = Modifier.padding(),
    )
}

@Composable
@PreviewLightDark
@ContentPreviews
internal fun GdsProgressIndicatorPreview() = GdsTheme {
    GdsProgressIndicatorDemo()
}
