package uk.gov.android.ui.componentsv2.bulletedlist

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
class BulletedListScreenshotTest(
    private val parameters: Pair<BulletedListItem, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit =
        {
            GdsBulletedList(parameters.first)
        }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<BulletedListItem, NightMode>> {
            val result: MutableList<Pair<BulletedListItem, NightMode>> = mutableListOf()

            BulletedListProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
