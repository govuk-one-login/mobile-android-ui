package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.images.icon.GdsIconProvider
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.images.icon.GdsIconM3Preview

@RunWith(Parameterized::class)
class IconM3ScreenshotTest(
    parameters: Pair<IconParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsIconM3Preview(parameters = parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsIconM3")
        fun values(): List<Pair<IconParameters, NightMode>> {
            val result: MutableList<Pair<IconParameters, NightMode>> = mutableListOf()
            GdsIconProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
