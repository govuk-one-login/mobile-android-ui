package uk.gov.android.ui.componentsv2.date

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme
import java.time.LocalDate

@RunWith(Parameterized::class)
internal class FormatDateScreenshotTest(
    locale: String,
) : BaseScreenshotTest(locale = locale) {

    private val dates = (1..12).map { LocalDate.of(2026, it, it) }

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsTheme {
            Surface {
                Column {
                    dates.forEach { date ->
                        Text(text = date.formatFullDate())
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}DateFormatting")
        fun locales(): List<String> = listOf(
            "en", // English
            "cy", // Welsh
        )
    }
}
