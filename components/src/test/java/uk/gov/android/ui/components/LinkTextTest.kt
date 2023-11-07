package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.content.GdsLinkText
import uk.gov.android.ui.components.content.LinkTextParameters
import uk.gov.android.ui.components.content.LinkTextProvider

@RunWith(Parameterized::class)
class LinkTextTest(
    private val parameters: Pair<LinkTextParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsLinkText(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsLinkText")
        fun values(): List<Pair<LinkTextParameters, NightMode>> {
            val result: MutableList<Pair<LinkTextParameters, NightMode>> = mutableListOf()

            LinkTextProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
