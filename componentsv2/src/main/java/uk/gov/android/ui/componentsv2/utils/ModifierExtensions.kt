package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
 * This modifier draws a bottom border clipped to the provided [Shape].
 * Useful for adding a shaped bottom stroke to components like buttons.
 *
 * Note: This currently clips a rectangle to the shape rather than following the bottom edge.
 * See https://govukverify.atlassian.net/browse/DCMAW-20843
 *
 * @param color - [Color] for the border
 * @param shape - [Shape] used to clip the border drawing
 * @param strokeWidth - [Dp] width of the border stroke
 */
internal fun Modifier.customBottomBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp,
) = this.drawWithContent {
    drawContent()
    val strokeWidthPx = strokeWidth.toPx()

    val outline = shape.createOutline(
        size = size,
        layoutDirection = layoutDirection,
        density = this,
    )

    clipPath(path = outline.toPath()) {
        drawRect(
            color = color,
            topLeft = Offset(
                x = 0f,
                y = size.height - strokeWidthPx,
            ),
            size = Size(
                width = size.width,
                height = strokeWidthPx,
            ),
        )
    }
}

private fun Outline.toPath(): Path = when (this) {
    is Outline.Rectangle -> Path().apply { addRect(rect) }
    is Outline.Rounded -> Path().apply { addRoundRect(roundRect) }
    is Outline.Generic -> path
}
