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
 * Holds the bottom content of [LeftAlignedScreenV3] (supporting text and buttons) along with its
 * layout configuration.
 *
 * @param supportingText the supporting text slot, shown above the buttons (optional).
 * @param primaryButton the primary action slot (optional).
 * @param secondaryButton the secondary action slot (optional).
 * @param backgroundColor background colour used when the content is a sticky footer (optional).
 * @param horizontalPadding horizontal padding applied to the content; defaults to [spacingDouble]
 * (optional).
 * @param verticalPadding vertical padding applied to the content; defaults to [spacingDouble]
 * (optional).
 * @param modifier [Modifier] applied to the root of the bottom content (optional).
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
        internal fun empty() = BottomContentParameters()

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
