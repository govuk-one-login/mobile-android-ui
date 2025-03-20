package uk.gov.android.ui.patterns.dialog

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class CustomFullScreenDialogScreenshotTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    @OptIn(ExperimentalMaterial3Api::class)
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            FullScreenDialog(
                onDismissRequest = { },
                topAppBar = {
                    TopAppBar(
                        title = { Text(parameters.first) },
                    )
                },
                content = { Text("Some other content") },
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
