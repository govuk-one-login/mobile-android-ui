package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class GdsContentTileScreenshotTest(
    private val parameters: Pair<ContentTileParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            GdsContentTile(parameters.first) { }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsContentTileV2")
        fun values(): List<Pair<ContentTileParameters, NightMode>> {
            val result: MutableList<Pair<ContentTileParameters, NightMode>> = mutableListOf()

            ContentTilePreviewParameters().values.forEach(applyNightMode(result))

            return result
        }
    }
}
