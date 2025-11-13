package uk.gov.android.ui.patterns.camera.qr

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ModifierExtensions {

    const val CANVAS_WIDTH_MULTIPLIER = .6f
    const val CANVAS_HEIGHT_MULTIPLIER = 0.3f
    const val BORDER_OFFSET = 7f
    const val QR_BORDER_OVERLAY_STROKE_WIDTH = 5

    /**
     * Draws a cornered square in the centre of the Composable UI:
     *
     * ```
     * ⌜ ⌝
     * ⌞ ⌟
     * ```
     */
    @Suppress("LongParameterList")
    fun ContentDrawScope.drawQrOverlayBorder(
        width: Float,
        borderLength: Float,
        color: Color,
        canvasHeightMultiplier: Float = CANVAS_HEIGHT_MULTIPLIER,
        borderOffset: Float = BORDER_OFFSET,
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val leftX = (canvasWidth - width) / 2
        val rightX = leftX + width
        val topY = canvasHeight * canvasHeightMultiplier
        val bottomY = topY + width
        val topLeft = Offset(leftX, topY)
        val topLeftOffset = Offset(leftX - borderOffset, topY)
        val topRight = Offset(rightX, topY)
        val topRightOffset = Offset(rightX + borderOffset, topY)
        val bottomLeft = Offset(leftX, bottomY)
        val bottomLeftOffset = Offset(leftX - borderOffset, bottomY)
        val bottomRight = Offset(rightX, bottomY)
        val bottomRightOffset = Offset(rightX, bottomY + borderOffset)
        val topLeftPlusX = Offset(leftX + borderLength, topY)
        val topLeftPlusY = Offset(leftX, topY + borderLength)
        val topRightMinusX = Offset(rightX - borderLength, topY)
        val topRightPlusY = Offset(rightX, topY + borderLength)
        val bottomRightMinusY = Offset(rightX, bottomY - borderLength)
        val bottomRightMinusX = Offset(rightX - borderLength, bottomY)
        val bottomLeftPlusX = Offset(leftX + borderLength, bottomY)
        val bottomLeftMinusY = Offset(leftX, bottomY - borderLength)

        drawBorderLine(topLeftOffset, topLeftPlusX, color)
        drawBorderLine(topRightMinusX, topRightOffset, color)
        drawBorderLine(topRight, topRightPlusY, color)
        drawBorderLine(bottomRightMinusY, bottomRightOffset, color)
        drawBorderLine(bottomRight, bottomRightMinusX, color)
        drawBorderLine(bottomLeftPlusX, bottomLeftOffset, color)
        drawBorderLine(bottomLeft, bottomLeftMinusY, color)
        drawBorderLine(topLeftPlusY, topLeft, color)
    }

    private fun ContentDrawScope.drawBorderLine(
        start: Offset,
        end: Offset,
        color: Color,
        strokeWidth: Dp = QR_BORDER_OVERLAY_STROKE_WIDTH.dp,
    ) {
        drawLine(
            start = start,
            end = end,
            strokeWidth = strokeWidth.toPx(),
            color = color,
        )
    }

    /**
     * Overlays the Composable UI with a tint and draws a cornered square in the centre of the
     * Composable.
     *
     * @see drawQrOverlayBorder
     */
    fun Modifier.qrScannerOverlay(
        overlayTint: Color,
        qrBorderColor: Color,
        canvasWidthMultiplier: Float = CANVAS_WIDTH_MULTIPLIER,
        canvasHeightMultiplier: Float = CANVAS_HEIGHT_MULTIPLIER,
    ) = this.then(
        Modifier
            .drawWithContent {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val width = canvasWidth * canvasWidthMultiplier
                val borderLength = width * canvasHeightMultiplier
                val rectangleSize = Size(width, width)
                val rectangleOffset = Offset(
                    (canvasWidth - width) / 2,
                    canvasHeight * canvasHeightMultiplier,
                )

                drawContent()
                drawRect(overlayTint)

                // Draws the rectangle in the middle
                drawRect(
                    topLeft = rectangleOffset,
                    size = rectangleSize,
                    color = Color.Transparent,
                    blendMode = BlendMode.SrcIn,
                )

                drawQrOverlayBorder(
                    width = width,
                    borderLength = borderLength,
                    color = qrBorderColor,
                )
            },
    )
}
