package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.theme.spacingDouble

/**
 * Holds the layout configuration for the bottom content of [LeftAlignedScreenV3] (supporting text
 * and buttons).
 *
 * @param backgroundColor background colour used when the content is a sticky footer (optional).
 * @param horizontalPadding horizontal padding applied to the content; defaults to [spacingDouble]
 * (optional).
 * @param verticalPadding vertical padding applied to the content; defaults to [spacingDouble]
 * (optional).
 * @param modifier [Modifier] applied to the root of the bottom content (optional).
 */
data class BottomContentConfig(
    val backgroundColor: Color = Color.Unspecified,
    val horizontalPadding: Dp = spacingDouble,
    val verticalPadding: Dp = spacingDouble,
    val modifier: Modifier = Modifier,
)
