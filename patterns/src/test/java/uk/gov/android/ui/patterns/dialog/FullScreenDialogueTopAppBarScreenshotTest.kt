package uk.gov.android.ui.patterns.dialog

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@OptIn(ExperimentalMaterial3Api::class)
@RunWith(Parameterized::class)
class FullScreenDialogueTopAppBarScreenshotTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            FullScreenDialogueTopAppBar(
                onCloseClick = {},
                title = {
                    val text = parameters.first
                    Text(text = text)
                },
            )
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
