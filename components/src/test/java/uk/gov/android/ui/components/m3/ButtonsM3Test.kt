package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonProvider
import uk.gov.android.ui.components.m3.buttons.GdsButton

@RunWith(Parameterized::class)
class ButtonsM3Test(
    private val parameters: Pair<ButtonParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsButton(buttonParameters = parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsButtonM3")
        fun values(): List<Pair<ButtonParameters, NightMode>> {
            val result: MutableList<Pair<ButtonParameters, NightMode>> = mutableListOf()

            ButtonProvider().values.map { it.first() }.forEach(applyNightMode(result))

            return result
        }
    }
}
