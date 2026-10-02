package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
 * Screenshot tests for SecondaryOutlined button focused state.
 *
 * Uses FocusInteraction to put the button into a real focused state rather than
 * manually overriding colors to simulate the appearance.
 *
 * Note: Highlighted (pressed) states cannot be tested with Paparazzi as PressInteraction
 * triggers ripple animations which require a real frame clock.
 */
@RunWith(Parameterized::class)
internal class GdsButtonSecondaryOutlinedInteractionScreenshotTest(
    private val nightMode: NightMode,
) : BaseScreenshotTest(nightMode) {

    override val generateComposeLayout: @Composable () -> Unit = {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(xsmallPadding),
                contentAlignment = Alignment.Center,
            ) {
                FocusedButton()
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}SecondaryOutlinedInteraction")
        fun values(): List<NightMode> = listOf(NOTNIGHT, NIGHT)
    }
}

/**
 * Keyboard/VoiceOver focused state:
 * Uses FocusInteraction to simulate actual focused state.
 */
@Composable
private fun FocusedButton() {
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(Unit) {
        interactionSource.emit(FocusInteraction.Focus())
    }
    GdsButton(
        text = "Secondary outlined (focused)",
        buttonType = ButtonTypeV2.SecondaryOutlined(),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        interactionSource = interactionSource,
    )
}
