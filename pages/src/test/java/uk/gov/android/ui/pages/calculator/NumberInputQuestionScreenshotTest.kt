package uk.gov.android.ui.pages.calculator

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.android.ui.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class NumberInputQuestionScreenshotTest(
    private val parameters: Pair<NumberInputQuestionParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        NumberInputQuestion(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun values(): List<Pair<NumberInputQuestionParameters, NightMode>> {
            val result: MutableList<Pair<NumberInputQuestionParameters, NightMode>> =
                mutableListOf()

            NumberInputQuestionProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
