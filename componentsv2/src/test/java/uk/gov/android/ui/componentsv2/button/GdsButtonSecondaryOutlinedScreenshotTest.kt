package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.resources.NightMode
import com.android.resources.NightMode.NIGHT
import com.android.resources.NightMode.NOTNIGHT
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.buttonContentVertical
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.xsmallPadding

@RunWith(Parameterized::class)
internal class GdsButtonSecondaryOutlinedScreenshotTest(
    private val parameters: Pair<ButtonParametersV2, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(xsmallPadding),
                contentAlignment = Alignment.Center,
            ) {
                GdsButton(
                    modifier = parameters.modifier,
                    text = parameters.text,
                    buttonType = parameters.buttonType.toButtonTypeV2(),
                    icon = parameters.icon?.toButtonIcon(),
                    onClick = {},
                    contentPosition = parameters.contentPosition,
                    contentModifier = parameters.contentModifier,
                    textAlign = parameters.textAlign,
                    enabled = parameters.enabled,
                    loading = parameters.loading,
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonSecondaryOutlined")
        fun values(): List<Pair<ButtonParametersV2, NightMode>> {
            val result: MutableList<Pair<ButtonParametersV2, NightMode>> = mutableListOf()

            secondaryOutlinedParameters.forEach(applyNightMode(result))

            return result
        }

        private val secondaryOutlinedParameters = listOf(
            ButtonParametersV2(
                text = "Secondary outlined button",
                buttonType = ButtonTypePreview.SecondaryOutlined,
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (icon)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Trailing,
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (leading icon)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Leading,
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (disabled)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                enabled = false,
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (disabled, icon)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Trailing,
                enabled = false,
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (loading)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                loading = true,
                enabled = false,
            ),
        )
    }
}

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

    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = contentColor,
                shape = GdsButtonDefaults.defaultShape,
            ),
        shape = GdsButtonDefaults.defaultShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        contentPadding = PaddingValues(
            horizontal = buttonContentHorizontal,
            vertical = buttonContentVertical,
        ),
    ) {
        Text(text = "Secondary outlined (highlighted)")
    }
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

    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = contentColor,
                shape = GdsButtonDefaults.defaultShape,
            ),
        shape = GdsButtonDefaults.defaultShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        contentPadding = PaddingValues(
            horizontal = buttonContentHorizontal,
            vertical = buttonContentVertical,
        ),
    ) {
        Text(text = "Secondary outlined (focused)")
    }
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

    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = contentColor,
                shape = GdsButtonDefaults.defaultShape,
            ),
        shape = GdsButtonDefaults.defaultShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        contentPadding = PaddingValues(
            horizontal = buttonContentHorizontal,
            vertical = buttonContentVertical,
        ),
    ) {
        Text(text = "Secondary outlined (focused + highlighted)")
    }
}

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
