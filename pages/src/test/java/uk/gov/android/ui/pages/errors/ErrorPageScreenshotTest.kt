package uk.gov.android.ui.pages.errors

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import uk.gov.android.ui.pages.BaseScreenshotTest

@RunWith(Parameterized::class)
class ErrorPageScreenshotTest(
    private val parameters: Pair<ErrorPageParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        ErrorPage(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun values(): List<Pair<ErrorPageParameters, NightMode>> {
            val result: MutableList<Pair<ErrorPageParameters, NightMode>> = mutableListOf()

            ErrorPageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
