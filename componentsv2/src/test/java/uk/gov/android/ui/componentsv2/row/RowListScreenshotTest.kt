package uk.gov.android.ui.componentsv2.row

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class RowListScreenshotTest(
    private val parameters: Pair<ImmutableList<RowData>, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        RowList(
            rows = parameters,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}_RowList")
        fun values(): List<Pair<ImmutableList<RowData>, NightMode>> {
            val result: MutableList<Pair<ImmutableList<RowData>, NightMode>> = mutableListOf()

            RowPreviewParametersProvider().values
                .map { eachRow -> eachRow.toRowData() }
                .toImmutableList()
                .apply(applyNightMode(result))

            return result
        }
    }
}
