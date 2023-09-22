package uk.gov.documentchecking.pages.calculator

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.documentchecking.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class InformationScreenScreenshotTest(
    private val parameters: Pair<InformationScreenParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        InformationScreen(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun values(): List<Pair<InformationScreenParameters, NightMode>> {
            val result: MutableList<Pair<InformationScreenParameters, NightMode>> = mutableListOf()

            InformationScreenProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
