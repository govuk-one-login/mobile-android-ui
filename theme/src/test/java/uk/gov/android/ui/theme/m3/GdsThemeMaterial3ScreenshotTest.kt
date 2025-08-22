package uk.gov.android.ui.theme.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.theme.utils.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsThemeMaterial3ScreenshotTest(
    private val parameters: Pair<Unit, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        ThemeV2Material3Preview()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<Unit, NightMode>> {
            val result: MutableList<Pair<Unit, NightMode>> = mutableListOf()

            listOf(Unit).forEach(applyNightMode(result))

            return result
        }
    }
}
