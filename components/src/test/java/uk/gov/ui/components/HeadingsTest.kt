package uk.gov.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class HeadingsTest(
    private val parameters: Pair<HeadingParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsHeading(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsHeading")
        fun values(): List<Pair<HeadingParameters, NightMode>> {
            val result: MutableList<Pair<HeadingParameters, NightMode>> = mutableListOf()
            HeadingsProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
