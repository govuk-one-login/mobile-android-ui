package uk.gov.android.ui.testwrapper.componentsv2.progress

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicator
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicatorDefaults
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreen
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ContentPreviews

@Composable
fun GdsProgressIndicatorCustomLabelsDemo(modifier: Modifier = Modifier) = LeftAlignedScreen(
    modifier = modifier,
    body = { horizontalPadding ->
        item {
            GdsProgressIndicator(
                labels = GdsProgressIndicatorDefaults.labels(
                    short = "Custom loading message that is quite long and might spill over",
                    long = "Custom long loading message that is very long and should overflow",
                    longer = "Custom longer loading message that is just as long and might wrap",
                ),
                modifier = Modifier.padding(horizontal = horizontalPadding),
            )
        }
    },
)

@Composable
@PreviewLightDark
@ContentPreviews
internal fun GdsProgressIndicatorCustomLabelsDemoPreview() = GdsTheme {
    GdsProgressIndicatorCustomLabelsDemo()
}
