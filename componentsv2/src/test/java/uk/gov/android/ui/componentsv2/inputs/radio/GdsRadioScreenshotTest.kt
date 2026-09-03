package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.inputs.radio.previewparameterprovider.GdsRadioOptionItemProvider
import uk.gov.android.ui.componentsv2.inputs.radio.previewparameterprovider.GdsRadiosPreviewDataProvider
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadioOptionItemPreviewData
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadiosPreviewData
import kotlin.sequences.forEach

@RunWith(Parameterized::class)
class GdsRadioScreenshotTest(
    private val parameters: ScreenshotTestData,
) : BaseScreenshotTest(parameters.nightMode) {

    override val generateComposeLayout: @Composable () -> Unit = {
        when (val data = parameters.previewData) {
            is GdsRadiosPreviewData -> GdsRadiosPreview(data)
            is GdsRadioOptionItemPreviewData -> GdsRadioOptionItemPreview(data)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsRadios")
        fun values(): List<ScreenshotTestData> {
            val result: MutableList<ScreenshotTestData> = mutableListOf()

            GdsRadiosPreviewDataProvider().values.forEach { previewData ->
                NightMode.entries.forEach { nightMode ->
                    result.add(ScreenshotTestData(previewData, nightMode))
                }
            }

            GdsRadioOptionItemProvider().values.forEach { previewData ->
                NightMode.entries.forEach { nightMode ->
                    result.add(ScreenshotTestData(previewData, nightMode))
                }
            }
            return result
        }
    }

    data class ScreenshotTestData(
        val previewData: Any,
        val nightMode: NightMode,
    )
}
