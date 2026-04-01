package uk.gov.android.ui.testwrapper.componentsv2.warning

import androidx.compose.runtime.Composable
import uk.gov.android.ui.componentsv2.warning.GdsWarningText
import uk.gov.android.ui.testwrapper.DemoTemplate

@Composable
fun GdsWarningDemo() {
    DemoTemplate {
        GdsWarningText(
            text = "Instance of GdsWarningText",
        )
    }
}
