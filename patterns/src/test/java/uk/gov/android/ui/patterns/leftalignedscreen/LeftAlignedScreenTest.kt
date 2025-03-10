package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class LeftAlignedScreenTest(
    private val parameters: Pair<LeftAlignedScreenContent, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            LeftAlignedScreenFromContentParams(parameters)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<LeftAlignedScreenContent, NightMode>> {
            val result: MutableList<Pair<LeftAlignedScreenContent, NightMode>> = mutableListOf()

            LeftAlignedScreenContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
