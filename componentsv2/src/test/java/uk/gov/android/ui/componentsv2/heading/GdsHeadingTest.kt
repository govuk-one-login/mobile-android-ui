package uk.gov.android.ui.componentsv2.heading

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class GdsHeadingTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            GdsHeading(parameters)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<String, NightMode>> {
            val result: MutableList<Pair<String, NightMode>> = mutableListOf()

            HeadingParameterPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
