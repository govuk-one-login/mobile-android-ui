package uk.gov.android.ui.pages.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.pages.LandingPage
import uk.gov.android.ui.pages.LandingPageParameters
import uk.gov.android.ui.pages.LandingPageProvider
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class LandingPageScreenshotTest(
    private val parameters: Pair<LandingPageParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            LandingPage(
                landingPageParameters = parameters.first
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<LandingPageParameters, NightMode>> {
            val result: MutableList<Pair<LandingPageParameters, NightMode>> =
                mutableListOf()

            LandingPageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
