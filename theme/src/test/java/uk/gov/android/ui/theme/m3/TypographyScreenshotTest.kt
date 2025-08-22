package uk.gov.android.ui.theme.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.theme.utils.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class TypographyScreenshotTest(
    private val parameters: Pair<Int, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        TypographyPreview()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<Int, NightMode>> {
            val result: MutableList<Pair<Int, NightMode>> = mutableListOf()

            listOf(1).forEach(applyNightMode(result))

            return result
        }
    }
}
