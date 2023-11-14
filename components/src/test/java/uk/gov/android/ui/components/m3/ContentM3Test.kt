package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.m3.content.ContentM3Parameters
import uk.gov.android.ui.components.m3.content.ContentM3Provider
import uk.gov.android.ui.components.m3.content.GdsContentM3

@RunWith(Parameterized::class)
class ContentM3Test(
    private val parameters: Pair<ContentM3Parameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsContentM3(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsContent")
        fun values(): List<Pair<ContentM3Parameters, NightMode>> {
            val result: MutableList<Pair<ContentM3Parameters, NightMode>> = mutableListOf()

            ContentM3Provider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
