package uk.gov.android.ui.testwrapper.componentsv2.heading

import androidx.compose.runtime.Composable
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.testwrapper.DemoTemplate

@Composable
fun GdsHeadingDemo() {
    DemoTemplate {
        GdsHeading(
            text = "GdsHeading with body style",
            style = GdsHeadingStyle.Body,
        )
    }
}
