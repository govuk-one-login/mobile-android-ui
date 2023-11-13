package uk.gov.android.ui.pages.instructions

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.pages.BaseScreenshotTestM3

@RunWith(Parameterized::class)
class InstructionsScreenshotM2Test(
    private val parameters: Pair<InstructionsParametersM2, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first.generate()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<InstructionsParametersM2, NightMode>> {
            val result: MutableList<Pair<InstructionsParametersM2, NightMode>> =
                mutableListOf()

            InstructionsM2Provider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
