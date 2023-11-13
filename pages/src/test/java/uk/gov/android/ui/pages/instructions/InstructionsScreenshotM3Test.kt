package uk.gov.android.ui.pages.instructions

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.pages.BaseScreenshotTestM3

@RunWith(Parameterized::class)
class InstructionsScreenshotM3Test(
    private val parameters: Pair<InstructionsM3, NightMode>
) : BaseScreenshotTestM3(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first.generate()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<InstructionsM3, NightMode>> {
            val result: MutableList<Pair<InstructionsM3, NightMode>> =
                mutableListOf()

            InstructionsM3Provider().values.forEach(BaseScreenshotTest.applyNightMode(result))

            return result
        }
    }
}
