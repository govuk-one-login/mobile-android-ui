package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsRadioScreenshotTest(
    private val parameters: Pair<RadioSelectionPreviewData, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsSelection(
            items = parameters.items,
            selectedItem = parameters.selectedIndex,
            onItemSelected = {},
            title = parameters.title,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsSelectionV2")
        fun values(): List<Pair<RadioSelectionPreviewData, NightMode>> {
            val result: MutableList<Pair<RadioSelectionPreviewData, NightMode>> = mutableListOf()

            RadioSelectionProvider().values.forEach { previewData ->
                NightMode.entries.forEach { nightMode ->
                    result.add(Pair(previewData, nightMode))
                }
            }
            return result
        }
    }
}
