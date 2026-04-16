package uk.gov.android.ui.componentsv2.warning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.toMappedColors

@RunWith(Parameterized::class)
internal class GdsWarningScreenshotTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        Column(
            modifier = Modifier
                .background(color = Backgrounds.screen.toMappedColors()),
        ) {
            GdsWarningText(
                text = parameters,
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsIconV2")
        fun values(): List<Pair<String, NightMode>> {
            val result: MutableList<Pair<String, NightMode>> = mutableListOf()

            WarningPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
