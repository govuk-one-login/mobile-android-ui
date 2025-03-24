package uk.gov.android.ui.componentsv2.warning

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsWarningScreenshotTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsWarningText(
            text = parameters,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsIconV2")
        fun values(): List<Pair<String, NightMode>> {
            val result: MutableList<Pair<String, NightMode>> = mutableListOf()

            WarningPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
