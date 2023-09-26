package uk.gov.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.components.inputs.date.DatePickerParameters
import uk.gov.ui.components.inputs.date.DatePickerProvider
import uk.gov.ui.components.inputs.date.GdsDatePicker

@RunWith(Parameterized::class)
class DatePickerTest(
    private val parameters: Pair<DatePickerParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsDatePicker(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsRadioSelection")
        fun values(): List<Pair<DatePickerParameters, NightMode>> {
            val result: MutableList<Pair<DatePickerParameters, NightMode>> = mutableListOf()

            DatePickerProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
