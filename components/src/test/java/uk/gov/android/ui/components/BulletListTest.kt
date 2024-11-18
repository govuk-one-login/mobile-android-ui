package uk.gov.android.ui.components

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.content.BulletListParameters
import uk.gov.android.ui.components.content.BulletListProvider
import uk.gov.android.ui.components.content.GdsBulletList

@RunWith(Parameterized::class)
class BulletListTest(
    private val parameters: Pair<BulletListParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsBulletList(parameters.first)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: GdsContent")
        fun values(): List<Pair<BulletListParameters, NightMode>> {
            val result: MutableList<Pair<BulletListParameters, NightMode>> = mutableListOf()

            BulletListProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
