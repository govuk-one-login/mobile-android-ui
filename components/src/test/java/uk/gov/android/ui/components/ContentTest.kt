package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.content.ContentProvider
import uk.gov.android.ui.components.content.GdsContent

@RunWith(Parameterized::class)
class ContentTest(
    private val parameters: Pair<ContentParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsContent(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsContent")
        fun values(): List<Pair<ContentParameters, NightMode>> {
            val result: MutableList<Pair<ContentParameters, NightMode>> = mutableListOf()

            ContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
