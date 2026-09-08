package uk.gov.android.ui.componentsv2.progress

import androidx.compose.runtime.Composable
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class ProgressIndicatorScreenshotTest(config: Config) :
    BaseScreenshotTest(config.nightMode, config.locale, config.fontScale) {

    override val generateComposeLayout: @Composable () -> Unit = {
        ProgressIndicatorPreview()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}ProgressIndicator_{0}")
        fun values(): List<Config> = allCombinations.toList()
    }
}
