package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * Draws the GDS shadow for a card - **1.dp** drawn with [Modifier.shadow], matching the shadow of
 * the original cards.
 *
 * GDS cards do not use Material elevation for this, so it is not applied by default and is
 * deliberately not attached to the underlying Material card. Add it to the `modifier` passed to
 * [GdsCard]:
 *
 * ```
 * GdsCard(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .gdsCardShadow(),
 * ) { ... }
 * ```
 *
 * @param shape the shape the shadow follows - defaults to [GdsCardDefaults.shape], so pass the
 *   same shape given to [GdsCard] if it has been overridden
 * @param elevation the size of the shadow - defaults to [GdsCardDefaults.shadowElevation]
 */
fun Modifier.gdsCardShadow(
    shape: Shape = GdsCardDefaults.shape,
    elevation: Dp = GdsCardDefaults.shadowElevation,
): Modifier = shadow(elevation = elevation, shape = shape)
