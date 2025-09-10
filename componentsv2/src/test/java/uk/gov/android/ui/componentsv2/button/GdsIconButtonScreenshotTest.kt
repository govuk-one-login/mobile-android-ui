package uk.gov.android.ui.componentsv2.button

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
class GdsIconButtonScreenshotTest(
    private val parameters: Pair<GdsIconButtonPreviewParams, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsIconButtonPreview(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonV2")
        fun values(): List<Pair<GdsIconButtonPreviewParams, NightMode>> {
            val result: MutableList<Pair<GdsIconButtonPreviewParams, NightMode>> = mutableListOf()

            GdsIconButtonPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
