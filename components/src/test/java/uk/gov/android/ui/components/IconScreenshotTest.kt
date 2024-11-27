package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.images.icon.GdsIconPreview
import uk.gov.android.ui.components.images.icon.GdsIconProvider
import uk.gov.android.ui.components.images.icon.IconParameters

@RunWith(Parameterized::class)
class IconScreenshotTest(
    parameters: Pair<IconParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsIconPreview(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsIcon")
        fun values(): List<Pair<IconParameters, NightMode>> {
            val result: MutableList<Pair<IconParameters, NightMode>> = mutableListOf()
            GdsIconProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
