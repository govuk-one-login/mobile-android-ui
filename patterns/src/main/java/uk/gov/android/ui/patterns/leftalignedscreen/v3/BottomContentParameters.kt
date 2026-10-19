package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.theme.spacingDouble

/**
 *  A data class containing the composables to be arranged and configured,
 *  as well as other configurable parameters.
 *
 * @param supportingText [androidx.compose.runtime.Composable] first composable in column (optional).
 * @param primaryButton [androidx.compose.runtime.Composable] second composable in column (optional).
 * @param secondaryButton [androidx.compose.runtime.Composable] third composable in column (optional).
 * @param backgroundColor [androidx.compose.ui.graphics.Color] background colour when sticky (optional).
 * @param horizontalPadding [androidx.compose.ui.unit.Dp] defaults to spacingDouble (optional).
 * @param verticalPadding [androidx.compose.ui.unit.Dp] defaults to spacingDouble (optional).
 * @param modifier A [Modifier] to be applied to the root composable of the BottomContent (optional).
 */
data class BottomContentParameters(
    val supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    val primaryButton: (@Composable () -> Unit)? = null,
    val secondaryButton: (@Composable () -> Unit)? = null,
    val backgroundColor: Color = Color.Unspecified,
    val horizontalPadding: Dp = spacingDouble,
    val verticalPadding: Dp = spacingDouble,
    val modifier: Modifier = Modifier,
) {
    companion object {
        @Composable
        fun generateDefault(
            supportingText: String?,
            primaryButton: LeftAlignedScreenButton?,
            secondaryButton: LeftAlignedScreenButton?,
        ): BottomContentParameters = BottomContentParameters(
            supportingText = supportingText?.let { text ->
                { horizontalPadding ->
                    GdsSupportingText(
                        text = text,
                        modifier = Modifier.padding(horizontal = horizontalPadding),
                    )
                }
            },
            primaryButton = primaryButton?.let { button ->
                {
                    GdsButton(
                        text = button.text,
                        onClick = button.onClick,
                        buttonType = ButtonTypeV2.Primary(),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = button.enabled,
                    )
                }
            },
            secondaryButton = secondaryButton?.let { button ->
                {
                    GdsButton(
                        text = button.text,
                        onClick = button.onClick,
                        buttonType = ButtonTypeV2.Secondary(),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = button.enabled,
                    )
                }
            },
        )
    }
}
