package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class LeftAlignedScreenV2ScreenshotTest(
    private val parameters: Pair<LeftAlignedScreenContentV2, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            LeftAlignedScreenFromContentParamsV2(parameters)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<LeftAlignedScreenContentV2, NightMode>> {
            val result: MutableList<Pair<LeftAlignedScreenContentV2, NightMode>> = mutableListOf()

            LeftAlignedScreenContentProviderV2().values.forEach(applyNightMode(result))

            return result
        }
    }
}
