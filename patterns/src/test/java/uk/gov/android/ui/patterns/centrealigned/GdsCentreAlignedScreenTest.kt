package uk.gov.android.ui.patterns.centrealigned

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsCentreAlignedScreenTest(
    private val parameters: Pair<CentreAlignedContent, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit =
        {
            val parameters = parameters.first
            GdsCentreAlignedScreen(
                title = parameters.title,
                image = parameters.image,
                body = parameters.body,
                supportingText = parameters.supportingText,
                primaryButtonText = parameters.primaryButtonText,
                secondaryButtonText = parameters.secondaryButtonText,
            )
        }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<CentreAlignedContent, NightMode>> {
            val result: MutableList<Pair<CentreAlignedContent, NightMode>> = mutableListOf()

            ContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
