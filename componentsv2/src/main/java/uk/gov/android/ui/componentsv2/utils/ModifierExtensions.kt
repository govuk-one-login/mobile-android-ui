package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.buttonShadowSize
import uk.gov.android.ui.theme.cardShadow
import uk.gov.android.ui.theme.tileCornerRadius
import uk.gov.android.ui.theme.xsmallPadding

object ModifierExtensions {
    /**
     * This modifier enables a card to have shadow/ elevation provided and the shape as [RoundedCornerShape]
     * with a radius of **12.dp** (as per GDS Design requirements).
     *
     * @param shadow the elevation/. shadow of the card - it defaults to **1.dp**
     */
    fun Modifier.elevatedCardModifier(shadow: Dp?) = fillMaxWidth()
        .shadow(
            elevation = shadow ?: cardShadow,
            shape = RoundedCornerShape(tileCornerRadius),
        )

    fun Modifier.customTilePadding(apply: Boolean) = if (apply) {
        fillMaxWidth()
            .padding(top = xsmallPadding)
    } else {
        fillMaxWidth()
            .padding(vertical = xsmallPadding)
    }

    fun Modifier.customTitlePadding(apply: Boolean) = if (apply) {
        padding(end = 40.dp)
    } else {
        this
    }
}

/**
 * This modifier allows for adding bottom shadow to components.
 *
 * It allows for custom color and size of the shadow.
 *
 * @param shadowColor - [Color] for the shadow **only**
 * @param shadowHeight = defaults to 2.dp but can be overridden
 *
 */
fun Modifier.customBottomShadow(
    shadowColor: Color,
    shadowHeight: Dp = buttonShadowSize,
) = this.drawBehind {
    val shadowHeightPx = shadowHeight.toPx()
    drawLine(
        shadowColor,
        Offset(0f, size.height),
        Offset(size.width, size.height),
        shadowHeightPx,
    )
}
