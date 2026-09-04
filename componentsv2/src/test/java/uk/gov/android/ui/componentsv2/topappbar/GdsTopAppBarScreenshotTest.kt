package uk.gov.android.ui.componentsv2.topappbar

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
class GdsTopAppBarScreenshotTest(
    private val parameters: Pair<GdsTopAppBarPreviewParams, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTopAppBarPreview(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsTopAppBar")
        fun values(): List<Pair<GdsTopAppBarPreviewParams, NightMode>> {
            val result: MutableList<Pair<GdsTopAppBarPreviewParams, NightMode>> = mutableListOf()

            GdsTopAppBarPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
