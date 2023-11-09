package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class VectorImageTest(
    private val parameters: Pair<VectorImageParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsVectorImage(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsVector")
        fun values(): List<Pair<VectorImageParameters, NightMode>> {
            val result: MutableList<Pair<VectorImageParameters, NightMode>> = mutableListOf()
            VectorImageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
