package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class LeftAlignedScreenFixedScreenshotTest(
    private val parameters: Pair<LeftAlignedScreenContent, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        PreviewLeftAlignedScreenFixed(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values() = LeftAlignedScreenContentProvider().values.applyNightMode()
    }
}
