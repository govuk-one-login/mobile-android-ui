package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.theme.spacingDouble

/**
 * Holds the layout configuration for the main (scrollable) content of [LeftAlignedScreenV3] (title
 * and body).
 *
 * @param horizontalPadding horizontal padding applied to the content; defaults to [spacingDouble]
 * (optional).
 * @param verticalPadding vertical spacing between items; defaults to [spacingDouble] (optional).
 * @param modifier [Modifier] applied to the root of the main content (optional).
 */
data class MainContentConfig(
    val horizontalPadding: Dp = spacingDouble,
    val verticalPadding: Dp = spacingDouble,
    val modifier: Modifier = Modifier,
)
