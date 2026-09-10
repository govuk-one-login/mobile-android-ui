package uk.gov.android.ui.patterns.loadingscreen.v2

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@RunWith(Parameterized::class)
class LoadingScreenshotTest(nightMode: NightMode) : BaseScreenshotTest(nightMode) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            LoadingScreen()
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} Loading")
        fun values(): List<NightMode> = listOf(NightMode.NIGHT, NightMode.NOTNIGHT)
    }
}
