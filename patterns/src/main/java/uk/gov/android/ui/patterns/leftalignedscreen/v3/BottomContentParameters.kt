package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText

/**
 * Holds the bottom content of [LeftAlignedScreenV3] (supporting text and buttons).
 *
 * @param supportingText the supporting text slot, shown above the buttons (optional).
 * @param primaryButton the primary action slot (optional).
 * @param secondaryButton the secondary action slot (optional).
 */
data class BottomContentParameters(
    val supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    val primaryButton: (@Composable () -> Unit)? = null,
    val secondaryButton: (@Composable () -> Unit)? = null,
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
