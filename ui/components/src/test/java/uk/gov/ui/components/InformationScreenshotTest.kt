package uk.gov.ui.components

import androidx.compose.runtime.Composable
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.components.information.InformationParameters
import uk.gov.ui.components.information.InformationPreview
import uk.gov.ui.components.information.InformationProvider

@RunWith(Parameterized::class)
class InformationScreenshotTest(
    private val parameters: InformationParameters
) : BaseScreenshotTest() {

    override val generateComposeLayout: @Composable () -> Unit = {
        InformationPreview(parameters)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = InformationProvider().values.toList()
    }
}
