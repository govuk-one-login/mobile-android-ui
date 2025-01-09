package uk.gov.android.ui.componentsv2.button

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
class GdsButtonScreenshotTest(
    private val parameters: Pair<ButtonParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsButton(parameters.first) { }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonV2")
        fun values(): List<Pair<ButtonParameters, NightMode>> {
            val result: MutableList<Pair<ButtonParameters, NightMode>> = mutableListOf()

            ButtonParameterPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
