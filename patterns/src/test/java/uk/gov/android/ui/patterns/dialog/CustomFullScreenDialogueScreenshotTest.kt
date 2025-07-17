package uk.gov.android.ui.patterns.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

@RunWith(Parameterized::class)
class CustomFullScreenDialogueScreenshotTest(
    private val parameters: Pair<String, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    @OptIn(ExperimentalMaterial3Api::class)
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            FullScreenDialogue(
                topAppBar = {
                    FullScreenDialogueTopAppBar(
                        title = { Text(parameters.first) },
                        onCloseClick = { },
                    )
                },
                content = {
                    Box(
                        modifier = Modifier.padding(spacingDouble),
                    ) {
                        Text("Content")
                    }
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
