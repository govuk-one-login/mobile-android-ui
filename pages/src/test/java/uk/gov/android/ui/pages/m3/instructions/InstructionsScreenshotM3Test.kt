package uk.gov.android.ui.pages.m3.instructions

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.pages.m3.BaseScreenshotTestM3

@RunWith(Parameterized::class)
class InstructionsScreenshotM3Test(
    private val parameters: Pair<Instructions, NightMode>,
) : BaseScreenshotTestM3(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first.generate()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<Instructions, NightMode>> {
            val result: MutableList<Pair<Instructions, NightMode>> =
                mutableListOf()

            InstructionsProvider().values.forEach(BaseScreenshotTest.applyNightMode(result))

            return result
        }
    }
}
