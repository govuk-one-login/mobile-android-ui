package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class GdsCentreAlignedScreenTest(
    private val parameters: Pair<CentreAlignedScreenContent, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit =
        {
            val parameters = parameters.first
            GdsTheme {
                GdsCentreAlignedScreen(
                    title = parameters.title,
                    image = parameters.image,
                    body = parameters.body,
                    supportingText = parameters.supportingText,
                    primaryButton = parameters.primaryButton,
                    secondaryButton = parameters.secondaryButton,
                )
            }
        }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<CentreAlignedScreenContent, NightMode>> {
            val result: MutableList<Pair<CentreAlignedScreenContent, NightMode>> = mutableListOf()

            CentreAlignedScreenContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
