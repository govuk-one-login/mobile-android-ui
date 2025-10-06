package uk.gov.android.ui.componentsv2.dialogue

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsDialogueScreenshotTest(
    private val parameters: Pair<DialoguePreviewParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsDialoguePreview(parameters)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsDialogue")
        fun values(): List<Pair<DialoguePreviewParameters, NightMode>> {
            val result: MutableList<Pair<DialoguePreviewParameters, NightMode>> = mutableListOf()

            DialogProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
