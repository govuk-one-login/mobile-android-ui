package uk.gov.android.ui.componentsv2.progress

import androidx.compose.runtime.Composable
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

internal class GdsProgressIndicatorCustomThemeScreenshotTest : BaseScreenshotTest() {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsProgressIndicatorCustomThemePreview()
    }
}
