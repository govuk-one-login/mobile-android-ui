package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsRadioScreenshotTest(
    private val parameters: Pair<GdsRadiosPreviewData, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsRadiosPreview(parameters)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsRadios")
        fun values(): List<Pair<GdsRadiosPreviewData, NightMode>> {
            val result: MutableList<Pair<GdsRadiosPreviewData, NightMode>> = mutableListOf()

            GdsRadiosProvider().values.forEach { previewData ->
                NightMode.entries.forEach { nightMode ->
                    result.add(Pair(previewData, nightMode))
                }
            }
            return result
        }
    }
}
