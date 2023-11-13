package uk.gov.android.ui.components.m3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ButtonsM3Test(
    private val parameters: Pair<ButtonParametersM3, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsButtonM3(buttonParameters = parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsButtonM3")
        fun values(): List<Pair<ButtonParametersM3, NightMode>> {
            val result: MutableList<Pair<ButtonParametersM3, NightMode>> = mutableListOf()

            ButtonProviderM3().values.forEach(applyNightMode(result))

            return result
        }
    }
}
