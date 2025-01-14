package uk.gov.android.ui.pages.dialog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class FullScreenDialogScreenshotTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            FullScreenDialog(title = parameters.first) {
                Text("Content")
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<String, NightMode>> =
            mutableListOf<Pair<String, NightMode>>().apply {
                listOf("Title").forEach(applyNightMode(this))
            }
    }
}
