package uk.gov.android.ui.componentsv2.text

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.supportingtext.SupportingTextPreviewProvider
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class GdsAnnotatedStringScreenshotTest(private val parameters: Pair<GdsAnnotatedStringPreview, NightMode>) :
    BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            GdsAnnotatedStringPreview(parameters)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsAnnotatedString")
        fun values(): List<Pair<GdsAnnotatedStringPreview, NightMode>> {
            val result: MutableList<Pair<GdsAnnotatedStringPreview, NightMode>> = mutableListOf()

            GdsAnnotatedStringPreviewDataProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
