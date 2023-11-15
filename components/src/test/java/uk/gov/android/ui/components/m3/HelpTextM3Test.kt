package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class HelpTextM3Test(
    private val parameters: Pair<HelpText, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first.generate
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsHelpText")
        fun values(): List<Pair<HelpText, NightMode>> {
            val result: MutableList<Pair<HelpText, NightMode>> = mutableListOf()
            HelpTextProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
