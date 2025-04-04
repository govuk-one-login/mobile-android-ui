package uk.gov.android.ui.componentsv2.heading

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@RunWith(Parameterized::class)
internal class GdsHeadingScreenshotTest(
    private val parameters: Pair<HeadingParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    @OptIn(UnstableDesignSystemAPI::class)
    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            GdsHeading(
                text = parameters.text,
                style = parameters.style,
                textFontWeight = parameters.fontWeight,
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<HeadingParameters, NightMode>> {
            val result: MutableList<Pair<HeadingParameters, NightMode>> = mutableListOf()

            HeadingParameterPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
