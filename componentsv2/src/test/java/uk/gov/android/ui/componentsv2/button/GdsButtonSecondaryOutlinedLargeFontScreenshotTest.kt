package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.android.resources.NightMode
import com.android.resources.NightMode.NIGHT
import com.android.resources.NightMode.NOTNIGHT
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.theme.xsmallPadding

/**
 * Screenshot tests for SecondaryOutlined button with large font sizes.
 * Provides evidence for accessibility compliance with scaled text.
 */
@RunWith(Parameterized::class)
internal class GdsButtonSecondaryOutlinedLargeFontScreenshotTest(
    private val parameters: Triple<String, Float, NightMode>,
) : BaseScreenshotTest(
    nightMode = parameters.third,
    fontScale = parameters.second,
) {

    override val generateComposeLayout: @Composable () -> Unit = {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(xsmallPadding),
                contentAlignment = Alignment.Center,
            ) {
                GdsButton(
                    text = "Secondary outlined button",
                    buttonType = ButtonTypeV2.SecondaryOutlined(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }

    companion object {
        private const val FONT_SCALE_LARGE = 1.5f
        private const val FONT_SCALE_LARGEST = 2.0f

        @JvmStatic
        @Parameterized.Parameters(name = "{index}SecondaryOutlinedLargeFont")
        fun values(): List<Triple<String, Float, NightMode>> = listOf(
            Triple("large_font", FONT_SCALE_LARGE, NOTNIGHT),
            Triple("large_font", FONT_SCALE_LARGE, NIGHT),
            Triple("largest_font", FONT_SCALE_LARGEST, NOTNIGHT),
            Triple("largest_font", FONT_SCALE_LARGEST, NIGHT),
        )
    }
}
