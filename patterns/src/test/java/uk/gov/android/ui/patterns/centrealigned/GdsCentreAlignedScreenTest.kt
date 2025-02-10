package uk.gov.android.ui.patterns.centrealigned

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.BaseScreenshotTest

@RunWith(Parameterized::class)
class GdsCentreAlignedScreenTest(
    private val parameters: Pair<Content, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit =
        {
            GdsCentreAlignedScreen(parameters.first)
        }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<Content, NightMode>> {
            val result: MutableList<Pair<Content, NightMode>> = mutableListOf()

            ContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
