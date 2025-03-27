package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class ErrorScreenScreenshotTest(
    private val parameters: Pair<ErrorScreenContent, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            ErrorScreen(
                title = parameters.title,
                icon = parameters.icon,
                body = parameters.body,
                buttons = parameters.buttons,
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<ErrorScreenContent, NightMode>> {
            val result: MutableList<Pair<ErrorScreenContent, NightMode>> = mutableListOf()

            ErrorScreenContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
