package uk.gov.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.components.inputs.number.GdsNumberInput
import uk.gov.ui.components.inputs.number.NumberInputParameters
import uk.gov.ui.components.inputs.number.NumberInputProvider

@RunWith(Parameterized::class)
class NumberInputTest(
    private val parameters: Pair<NumberInputParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsNumberInput(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsNumberInput")
        fun values(): List<Pair<NumberInputParameters, NightMode>> {
            val result: MutableList<Pair<NumberInputParameters, NightMode>> = mutableListOf()

            NumberInputProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
