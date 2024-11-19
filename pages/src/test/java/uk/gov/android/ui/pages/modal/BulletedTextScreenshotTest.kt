package uk.gov.android.ui.pages.modal

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
class BulletedTextScreenshotTest(
    private val parameters: Pair<BulletedTextParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            BulletedText(
                parameters = parameters.first
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<BulletedTextParameters, NightMode>> =
            mutableListOf<Pair<BulletedTextParameters, NightMode>>().apply {
                listOf(bulletedTextPreviewParams).forEach(applyNightMode(this))
            }
    }
}
