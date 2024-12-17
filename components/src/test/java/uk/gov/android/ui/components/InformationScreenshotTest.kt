package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.information.InformationParameters
import uk.gov.android.ui.components.information.InformationPreview
import uk.gov.android.ui.components.information.InformationProvider

@RunWith(Parameterized::class)
class InformationScreenshotTest(
    private val parameters: Pair<InformationParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        InformationPreview(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} Information")
        fun values(): List<Pair<InformationParameters, NightMode>> {
            val result: MutableList<Pair<InformationParameters, NightMode>> = mutableListOf()
            InformationProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
