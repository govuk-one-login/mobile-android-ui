package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsCardScreenshotTest(private val parameters: Pair<CardPreview, NightMode>) :
    BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        when (parameters.first) {
            CardPreview.Default -> GdsCardPreview()
            CardPreview.Border -> GdsCardWithBorderPreview()
            CardPreview.Shadow -> LargeShadowCard()
        }
    }

    /**
     * The card's real shadow is only 1.dp, which is barely visible in a snapshot. This uses a
     * much larger elevation purely so the shadow is clearly visible - it is not a design value.
     */
    @Composable
    private fun LargeShadowCard() {
        // A fixed light backdrop is used in light and dark mode as a shadow is invisible on the
        // dark snapshot background
        Box(modifier = Modifier.background(Color.White)) {
            GdsCard(
                // Padding must exceed the shadow's reach so it isn't clipped when the layout shrinks
                modifier = Modifier.padding(SHADOW_PADDING),
                elevation = CardDefaults.cardElevation(defaultElevation = LARGE_ELEVATION),
            ) {
                Text(
                    text = "A card with a large shadow.",
                    modifier = Modifier.padding(CONTENT_PADDING),
                )
            }
        }
    }

    internal enum class CardPreview {
        Default,
        Border,
        Shadow,
    }

    companion object {
        private val LARGE_ELEVATION = 40.dp
        private val SHADOW_PADDING = 80.dp
        private val CONTENT_PADDING = 32.dp

        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsCard")
        fun values(): List<Pair<CardPreview, NightMode>> {
            val result: MutableList<Pair<CardPreview, NightMode>> = mutableListOf()

            CardPreview.entries.forEach(applyNightMode(result))

            return result
        }
    }
}
