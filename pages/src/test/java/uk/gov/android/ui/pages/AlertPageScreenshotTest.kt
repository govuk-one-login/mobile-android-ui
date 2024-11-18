package uk.gov.android.ui.pages

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class AlertPageScreenshotTest(
    private val parameters: Pair<AlertPageParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            AlertPage(
                alertPageParameters = parameters.first,
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<AlertPageParameters, NightMode>> {
            val result: MutableList<Pair<AlertPageParameters, NightMode>> =
                mutableListOf()

            AlertPageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
