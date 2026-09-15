package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.button.GdsButtonSizeScreenshotTest.Companion.buttonHeight
import uk.gov.android.ui.componentsv2.button.GdsButtonSizeScreenshotTest.Companion.shadowHeight
import uk.gov.android.ui.componentsv2.button.previewparameterprovider.ButtonParameterPreviewProviderV2

internal class GdsButtonSizeScreenshotTest : BaseScreenshotTest() {

    companion object {
        val buttonHeight = 48.dp
        val shadowHeight = 2.dp

        val totalButtonHeight = buttonHeight + shadowHeight
    }

    override val generateComposeLayout: @Composable () -> Unit = {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White),
        ) {
            val buttons = ButtonParameterPreviewProviderV2().values
            Row {
                Text("Button size test. Buttons should align with coloured boxes.")
            }
            Row {
                MeasuringColumn(
                    numBoxes = buttons.count(),
                    boxHeight = totalButtonHeight,
                )

                Column {
                    buttons.forEach { parameters ->
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
        }
    }
}

/**
 * Column of alternating coloured boxes that should align with the height of the buttons
 */
@Composable
fun MeasuringColumn(numBoxes: Int, boxHeight: Dp, modifier: Modifier = Modifier) =
    Column(modifier) {
        (1..numBoxes).forEach { i ->
            MeasuringBox(i, boxHeight)
        }
    }

@Composable
fun MeasuringBox(index: Int, boxHeight: Dp, modifier: Modifier = Modifier) = Box(
    modifier = modifier
        .size(boxHeight)
        .background(if (index % 2 == 0) Color.Cyan else Color.Magenta),
)
