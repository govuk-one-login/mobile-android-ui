package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class GdsInformationBannerScreenshotTest(
    private val parameters: Pair<InformationBannerParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            GdsInformationBanner(parameters = parameters.first)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<InformationBannerParameters, NightMode>> {
            val result: MutableList<Pair<InformationBannerParameters, NightMode>> =
                mutableListOf()

            InformationBannerParamProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
