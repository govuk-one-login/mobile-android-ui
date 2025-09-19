package uk.gov.android.ui.componentsv2.list

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsBulletedListV2ScreenshotTest(
    private val parameters: Pair<ListWrapper, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsBulletedListPreview(parameters)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<ListWrapper, NightMode>> {
            val result: MutableList<Pair<ListWrapper, NightMode>> = mutableListOf()

            val list = BulletedListProvider().values.toMutableList()
            // Remove item containing Spannable content which paparazzi cannot render
            list.removeAt(BulletedListProvider().values.count() - 1)
            list.forEach(applyNightMode(result))

            return result
        }
    }
}
