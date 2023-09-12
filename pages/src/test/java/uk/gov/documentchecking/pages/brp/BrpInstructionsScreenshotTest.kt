package uk.gov.documentchecking.pages.brp

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.documentchecking.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class BrpInstructionsScreenshotTest(
    private val parameters: Pair<BrpInstructionsParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        BrpInstructions(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun values(): List<Pair<BrpInstructionsParameters, NightMode>> {
            val result: MutableList<Pair<BrpInstructionsParameters, NightMode>> = mutableListOf()

            BrpInstructionsProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
