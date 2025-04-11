package uk.gov.android.ui.componentsv2.numberedlist

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListItem
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle

@RunWith(Parameterized::class)
internal class GdsNumberedListScreenshotTest(
    private val parameters: Pair<BulletedListItem, NightMode>,
) : BaseScreenshotTest(parameters.second) {
    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsNumberedList(
            numberedListItems = parameters.items,
            title = parameters.title?.let {
                BulletedListTitle(it.text, it.titleType)
            },
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<BulletedListItem, NightMode>> {
            val result: MutableList<Pair<BulletedListItem, NightMode>> = mutableListOf()

            NumberedListProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
