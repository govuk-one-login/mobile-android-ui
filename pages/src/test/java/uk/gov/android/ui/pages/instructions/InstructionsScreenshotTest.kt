package uk.gov.android.ui.pages.instructions

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class InstructionsScreenshotTest(
    private val parameters: Pair<InstructionsParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first.generate()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<InstructionsParameters, NightMode>> {
            val result: MutableList<Pair<InstructionsParameters, NightMode>> =
                mutableListOf()

            InstructionsProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
