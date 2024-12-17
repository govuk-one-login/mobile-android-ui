package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonProvider
import uk.gov.android.ui.components.buttons.GdsButton

@RunWith(Parameterized::class)
class ButtonsTest(
    private val parameters: Pair<ButtonParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsButton(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsButton")
        fun values(): List<Pair<ButtonParameters, NightMode>> {
            val result: MutableList<Pair<ButtonParameters, NightMode>> = mutableListOf()

            ButtonProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
