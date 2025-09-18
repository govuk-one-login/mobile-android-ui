package uk.gov.android.ui.componentsv2.status

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
class StatusOverlayScreenshotTest(
    parameters: Pair<Int, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        PreviewStatusOverlay()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsStatusOverlay")
        fun values(): List<Pair<Int, NightMode>> {
            val result: MutableList<Pair<Int, NightMode>> = mutableListOf()

            listOf(1).forEach(applyNightMode(result))

            return result
        }
    }
}
