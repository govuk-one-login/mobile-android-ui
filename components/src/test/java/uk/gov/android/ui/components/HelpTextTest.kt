package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class HelpTextTest(
    private val parameters: Pair<HelpTextParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsHelpText(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsHelpText")
        fun values(): List<Pair<HelpTextParameters, NightMode>> {
            val result: MutableList<Pair<HelpTextParameters, NightMode>> = mutableListOf()
            HelpTextProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
