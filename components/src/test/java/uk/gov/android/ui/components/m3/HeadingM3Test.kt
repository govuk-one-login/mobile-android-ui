package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class HeadingM3Test(
    private val parameters: Pair<Heading, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first.generate()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsHeadingM3")
        fun values(): List<Pair<Heading, NightMode>> {
            val result: MutableList<Pair<Heading, NightMode>> = mutableListOf()
            HeadingsProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
