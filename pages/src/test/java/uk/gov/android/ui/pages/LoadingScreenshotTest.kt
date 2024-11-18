package uk.gov.android.ui.pages

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class LoadingScreenshotTest(
    private val parameters: Pair<LoadingScreenParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            LoadingScreen(parameters.first)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<LoadingScreenParameters, NightMode>> {
            val result: MutableList<Pair<LoadingScreenParameters, NightMode>> =
                mutableListOf()

            LoadingScreenParameterProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
