package uk.gov.android.ui.componentsV2.images

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsV2.BaseScreenshotTest

@RunWith(Parameterized::class)
class GdsIconScreenshotTest(
    private val parameters: Pair<IconParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsIcon(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsIconV2")
        fun values(): List<Pair<IconParameters, NightMode>> {
            val result: MutableList<Pair<IconParameters, NightMode>> = mutableListOf()

            IconPreviewParameters().values.forEach(applyNightMode(result))

            return result
        }
    }
}
