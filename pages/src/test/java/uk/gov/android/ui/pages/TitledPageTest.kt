package uk.gov.android.ui.pages

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class TitledPageTest(
    private val parameters: Pair<TitledPageParameters, NightMode>
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        parameters.first
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): List<Pair<TitledPageParameters, NightMode>> {
            val result: MutableList<Pair<TitledPageParameters, NightMode>> =
                mutableListOf()

            TitledPageParametersProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
