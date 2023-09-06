package uk.gov.ui.components

import androidx.compose.runtime.Composable
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.components.images.icon.GdsIconPreview
import uk.gov.ui.components.images.icon.GdsIconProvider
import uk.gov.ui.components.images.icon.IconParameters

@RunWith(Parameterized::class)
class IconScreenshotTest(
    parameters: IconParameters
) : BaseScreenshotTest() {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsIconPreview(parameters)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = GdsIconProvider().values.toList()
    }
}
