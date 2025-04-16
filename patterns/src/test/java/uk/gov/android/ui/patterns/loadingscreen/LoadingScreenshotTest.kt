package uk.gov.android.ui.patterns.loadingscreen

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class LoadingScreenshotTest(
    private val parameters: Pair<String, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            LoadingScreen(text = parameters.first)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} Loading")
        fun values(): List<Pair<String, NightMode>> {
            val result: MutableList<Pair<String, NightMode>> = mutableListOf()
            LoadingScreenContentProvider().values.forEach(applyNightMode(result))
            return result
        }
    }
}