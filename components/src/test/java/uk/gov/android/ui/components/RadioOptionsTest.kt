package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.inputs.radio.GdsRadioSelection
import uk.gov.android.ui.components.inputs.radio.RadioSelectionParameters
import uk.gov.android.ui.components.inputs.radio.RadioSelectionProvider

@RunWith(Parameterized::class)
class RadioOptionsTest(
    private val parameters: Pair<RadioSelectionParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsRadioSelection(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsRadioSelection")
        fun values(): List<Pair<RadioSelectionParameters, NightMode>> {
            val result: MutableList<Pair<RadioSelectionParameters, NightMode>> = mutableListOf()

            RadioSelectionProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
