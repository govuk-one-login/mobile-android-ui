package uk.gov.android.ui.patterns.camera.qr

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.smallPadding

object ModifierExtensions {

    const val CANVAS_WIDTH_MULTIPLIER = .6f
    const val CANVAS_HEIGHT_MULTIPLIER = .3f
    const val BORDER_OFFSET = 7f
    const val QR_BORDER_OVERLAY_STROKE_WIDTH = 5
    const val CENTER_OFFSET_DIVIDER = 2

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

    /**
     * Draws a cornered square in the centre of the Composable UI:
     *
     * ```
     * ⌜ ⌝
     * ⌞ ⌟
     * ```
     */
    @Suppress("LongParameterList")
    fun ContentDrawScope.drawQrOverlayBorderCentered(
        width: Float,
        borderLength: Float,
        color: Color,
        borderHeight: Float,
        borderOffset: Float = BORDER_OFFSET,
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val leftX = (canvasWidth - width) / 2
        val rightX = leftX + width
        val topY = (canvasHeight - borderHeight) / 2f
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

    /**
     * Overlays the Composable UI with a tint and draws a cornered square in the centre of the
     * Composable which takes the width of the entire screen and adds a 16.dp padding vertically.
     *
     * @see drawQrOverlayBorderCentered
     */
    fun Modifier.qrScannerOverlay(
        overlayTint: Color,
        qrBorderColor: Color,
        density: Density,
        canvasHeightMultiplier: Float = CANVAS_HEIGHT_MULTIPLIER,
    ) = this.then(
        Modifier
            .drawWithContent {
                // Calculate vertical padding to Px (Float)
                val padding = with(density) { smallPadding.toPx() }
                // Calculate total padding if added vertically
                val totalVerticalPadding = padding * CENTER_OFFSET_DIVIDER
                val canvasWidth = size.width
                val canvasHeight = size.height
                // Calculate the width of the canvas/ focus square taking away the padding that will be applied vertically
                val width = canvasWidth - totalVerticalPadding
                // Calculate the border height
                val borderLength = width * canvasHeightMultiplier
                // Size of the rectangle (requires to be a square
                val rectangleSize = Size(width, width)
                // Calculate offset, which should be width of the entire screen/ canvas minus the padding (16.dp)
                val rectangleOffset = Offset(
                    width - (width - padding),
                    (canvasHeight - width) / CENTER_OFFSET_DIVIDER,
                )

                drawContent()
                drawRect(overlayTint)

                // Draws the focused (non-overlay) rectangle in the middle
                drawRect(
                    topLeft = rectangleOffset,
                    size = rectangleSize,
                    color = Color.Transparent,
                    blendMode = BlendMode.SrcIn,
                )

                // Draws the border around the focus rectangle
                drawQrOverlayBorderCentered(
                    width = canvasWidth - totalVerticalPadding,
                    borderLength = borderLength,
                    borderHeight = width,
                    color = qrBorderColor,
                )
            },
    )
}
