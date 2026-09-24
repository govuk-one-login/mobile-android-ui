package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.xsmallPadding

/**
 * Screenshot tests for SecondaryOutlined button interaction states (focused, pressed).
 */
@RunWith(Parameterized::class)
internal class GdsButtonSecondaryOutlinedInteractionScreenshotTest(
    private val parameters: Pair<InteractionState, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val state = parameters.first
        Surface(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(xsmallPadding),
                contentAlignment = Alignment.Center,
            ) {
                when (state) {
                    InteractionState.DefaultHighlighted -> DefaultHighlightedButton()
                    InteractionState.Focused -> FocusedButton()
                    InteractionState.FocusedHighlighted -> FocusedHighlightedButton()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}SecondaryOutlinedInteraction")
        fun values(): List<Pair<InteractionState, NightMode>> {
            val result = mutableListOf<Pair<InteractionState, NightMode>>()
            InteractionState.entries.forEach { state ->
                result.add(state to NOTNIGHT)
                result.add(state to NIGHT)
            }
            return result
        }
    }
}

internal enum class InteractionState {
    DefaultHighlighted,
    Focused,
    FocusedHighlighted,
}

/**
 * Default highlighted state (pressed/tapped):
 * - Border and text: secondaryTextAndSymbolButtonHighlighted
 * - Background: secondaryOutlinedBackground (White/Black1)
 */
@Composable
private fun DefaultHighlightedButton() {
    val contentColor = GdsLocalColorScheme.current.secondaryTextAndSymbolButtonHighlighted
    val backgroundColor = GdsLocalColorScheme.current.secondaryOutlinedBackground

    GdsButton(
        text = "Secondary outlined (highlighted)",
        buttonType = ButtonTypeV2.SecondaryOutlined(
            borderColor = contentColor,
            contentColor = contentColor,
            containerColor = backgroundColor,
        ),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
    )
}

/**
 * Keyboard/VoiceOver focused state:
 * - Border and text: focusStateContent
 * - Background: focusState
 */
@Composable
private fun FocusedButton() {
    val contentColor = GdsLocalColorScheme.current.focusStateContent
    val backgroundColor = GdsLocalColorScheme.current.focusState

    GdsButton(
        text = "Secondary outlined (focused)",
        buttonType = ButtonTypeV2.SecondaryOutlined(
            borderColor = contentColor,
            contentColor = contentColor,
            containerColor = backgroundColor,
        ),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
    )
}

/**
 * Keyboard/VoiceOver focused and highlighted state:
 * - Border and text: focusStateContent
 * - Background: focusButtonHighlighted
 */
@Composable
private fun FocusedHighlightedButton() {
    val contentColor = GdsLocalColorScheme.current.focusStateContent
    val backgroundColor = GdsLocalColorScheme.current.focusButtonHighlighted

    GdsButton(
        text = "Secondary outlined (focused + highlighted)",
        buttonType = ButtonTypeV2.SecondaryOutlined(
            borderColor = contentColor,
            contentColor = contentColor,
            containerColor = backgroundColor,
        ),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
    )
}
