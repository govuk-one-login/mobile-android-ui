package uk.gov.android.ui.patterns.loadingscreen.v2

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicator
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicatorDefaults
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@RunWith(Parameterized::class)
class LoadingScreenScreenshotTest(private val parameters: Pair<String, NightMode>) :
    BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            LoadingScreen {
                GdsProgressIndicator(
                    labels = GdsProgressIndicatorDefaults.labels(
                        short = parameters.first,
                    ),
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} Loading")
        fun values(): List<Pair<String, NightMode>> {
            val result: MutableList<Pair<String, NightMode>> = mutableListOf()
            LoadingScreenPreviewParameterProvider().values.forEach(applyNightMode(result))
            return result
        }
    }
}
