package uk.gov.android.ui.componentsv2.list

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsBulletedListScreenshotTest(
    private val parameters: Pair<ListWrapper, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsBulletedList(
            bulletListItems = parameters.items,
            title = parameters.title?.let {
                ListTitle(it.text, it.titleType)
            },
        )
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
