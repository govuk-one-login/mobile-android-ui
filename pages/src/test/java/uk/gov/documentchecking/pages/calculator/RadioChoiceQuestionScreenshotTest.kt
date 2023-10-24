package uk.gov.documentchecking.pages.calculator

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.documentchecking.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class RadioChoiceQuestionScreenshotTest(
    private val parameters: Pair<RadioChoiceQuestionParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        RadioChoiceQuestion(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun values(): List<Pair<RadioChoiceQuestionParameters, NightMode>> {
            val result: MutableList<Pair<RadioChoiceQuestionParameters, NightMode>> =
                mutableListOf()

            RadioChoiceQuestionProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
