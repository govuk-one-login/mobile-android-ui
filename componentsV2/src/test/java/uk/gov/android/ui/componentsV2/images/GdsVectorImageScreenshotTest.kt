package uk.gov.android.ui.componentsV2.images

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsV2.BaseScreenshotTest

@RunWith(Parameterized::class)
class GdsVectorImageScreenshotTest(
    private val parameters: Pair<VectorImageParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsVectorImage(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsVectorImageV2")
        fun values(): List<Pair<VectorImageParameters, NightMode>> {
            val result: MutableList<Pair<VectorImageParameters, NightMode>> = mutableListOf()

            VectorImageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
