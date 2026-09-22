package uk.gov.android.ui.componentsv2.progress

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsProgressIndicatorScreenshotTest(
    config: Config,
    private val waitLength: ProgressWaitLength,
) : BaseScreenshotTest(config) {

    override val generateComposeLayout: @Composable () -> Unit = {
        Surface {
            val state = remember(waitLength) {
                GdsProgressIndicatorState(initialWaitedFor = waitLength)
            }
            GdsProgressIndicator(state = state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsProgressIndicator_{1}_{0}")
        fun values(): List<Array<Any>> = allConfigs.map { combination ->
            arrayOf(combination, ProgressWaitLength.Short)
        } + listOf(
            arrayOf(defaultConfig, ProgressWaitLength.Long),
            arrayOf(defaultConfig, ProgressWaitLength.Longer),
            arrayOf(welshConfig, ProgressWaitLength.Long),
            arrayOf(welshConfig, ProgressWaitLength.Longer),
        )
    }
}
