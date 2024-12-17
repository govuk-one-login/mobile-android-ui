package uk.gov.android.ui.pages.calculator

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.android.ui.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class DatePickerQuestionScreenshotTest(
    private val parameters: Pair<DatePickerQuestionParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        DatePickerQuestion(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun values(): List<Pair<DatePickerQuestionParameters, NightMode>> {
            val result: MutableList<Pair<DatePickerQuestionParameters, NightMode>> =
                mutableListOf()

            DatePickerQuestionProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
