package uk.gov.android.ui.pages.modal

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class ModalDialogScreenshotTest(
    private val parameters: Pair<ModalDialogParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            ModalDialog(parameters.first)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<ModalDialogParameters, NightMode>> =
            mutableListOf<Pair<ModalDialogParameters, NightMode>>().apply {
                listOf(modalDialogPreviewParams).forEach(applyNightMode(this))
            }
    }
}
