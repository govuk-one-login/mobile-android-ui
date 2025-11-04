package uk.gov.android.ui.componentsv2.camera.qr

import android.graphics.ImageFormat
import android.graphics.Rect
import android.media.Image
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import kotlin.experimental.inv
import kotlin.math.ceil
import kotlin.math.floor

/**
 * [ImageProxyConverter] implementation that crops the input from a provided [ImageProxy].
 *
 * This is based on the centre point of the input image.
 *
 * @param relativeScanningWidth The percentage of the input image's width that should be used for
 * the output image's size.
 */
class CentrallyCroppedImageProxyConverter(
    private val relativeScanningWidth: Float = RELATIVE_SCANNING_WIDTH_FULL,
) : ImageProxyConverter {
    @OptIn(ExperimentalGetImage::class)
    override fun convert(proxy: ImageProxy): InputImage? = proxy.image?.let { image ->
        val (size, imageArray) = image.toCentralScanningArea(
            relativeScanningWidth = relativeScanningWidth,
        )

        InputImage.fromByteArray(
            imageArray,
            size,
            size,
            proxy.imageInfo.rotationDegrees,
            ImageFormat.NV21,
        )
    }

    fun Image.toCentralScanningArea(
        relativeScanningWidth: Float,
    ): Pair<Int, ByteArray> {
        val centerX = width / 2
        val centerY = height / 2
        val scanningAreaWidth = width * relativeScanningWidth

        val scanningAreaStartWidth = centerX - (scanningAreaWidth / 2)
        val scanningAreaStartHeight = centerY - (scanningAreaWidth / 2)
        val croppedArea = Rect(
            scanningAreaStartWidth.toInt(),
            scanningAreaStartHeight.toInt(),
            (scanningAreaStartWidth + scanningAreaWidth).toInt(),
            (scanningAreaStartHeight + scanningAreaWidth).toInt(),
        )

        val nv21 = yuv420888ToNv21(this)
        val croppedNv21 = cropNV21(nv21, width, croppedArea)

        return scanningAreaWidth.toInt() to croppedNv21
    }

    private fun yuv420888ToNv21(image: Image): ByteArray {
        val width = image.width
        val height = image.height
        val ySize = width * height
        val uvSize = width * height / YUV_SIZING_SPLIT

        val nv21 = ByteArray(ySize + uvSize * YUV_BYTE_ARRAY_MULTIPLIER)

        val yBuffer = image.planes[0].buffer // Y
        val uBuffer = image.planes[1].buffer // U
        val vBuffer = image.planes[2].buffer // V

        var rowStride = image.planes[0].rowStride
        assert(image.planes[0].pixelStride == SINGLE_PIXEL_STRIDE)

        var pos = 0

        if (rowStride == width) { // likely
            yBuffer[nv21, 0, ySize]
            pos += ySize
        } else {
            var yBufferPos = -rowStride.toLong() // not an actual position
            while (pos < ySize) {
                yBufferPos += rowStride.toLong()
                yBuffer.position(yBufferPos.toInt())
                yBuffer[nv21, pos, width]
                pos += width
            }
        }

        rowStride = image.planes[2].rowStride
        val pixelStride = image.planes[2].pixelStride

        assert(rowStride == image.planes[1].rowStride)
        assert(pixelStride == image.planes[1].pixelStride)

        if (pixelStride == 2 && rowStride == width && uBuffer[0] == vBuffer[1]) {
            // maybe V an U planes overlap as per NV21, which means vBuffer[1] is alias of uBuffer[0]
            val savePixel = vBuffer[1]
            vBuffer.put(1, savePixel.inv())
            if (uBuffer[0] == savePixel.inv()) {
                vBuffer.put(1, savePixel)
                vBuffer.position(0)
                uBuffer.position(0)
                vBuffer[nv21, ySize, 1]
                uBuffer[nv21, ySize + 1, uBuffer.remaining()]

                return nv21 // shortcut
            }

            // unfortunately, the check failed. We must save U and V pixel by pixel
            vBuffer.put(1, savePixel)
        }

        // other optimizations could check if (pixelStride == 1) or (pixelStride == 2),
        // but performance gain would be less significant
        for (row in 0..<height / 2) {
            for (col in 0..<width / 2) {
                val vuPos = col * pixelStride + row * rowStride
                nv21[pos++] = vBuffer[vuPos]
                nv21[pos++] = uBuffer[vuPos]
            }
        }

        return nv21
    }

    private fun cropNV21(img: ByteArray, imgWidth: Int, cropRect: Rect): ByteArray {
        // 1.5 mean 1.0 for Y and 0.25 each for U and V
        val croppedImgSize = floor(
            cropRect.width() * cropRect.height() * NV21_CROP_RECT_MULTIPLIER,
        ).toInt()
        val croppedImg = ByteArray(croppedImgSize)

        // Start points of UV plane
        val imgYPlaneSize = ceil(img.size / NV21_CROP_RECT_MULTIPLIER).toInt()
        val croppedImgYPlaneSize = cropRect.width() * cropRect.height()

        // Y plane copy
        for (w in 0..<cropRect.height()) {
            val imgPos = (cropRect.top + w) * imgWidth + cropRect.left
            val croppedImgPos = w * cropRect.width()
            System.arraycopy(img, imgPos, croppedImg, croppedImgPos, cropRect.width())
        }

        // UV plane copy
        // U and V are reduced by 2 * 2, so each row is the same size as Y
        // and is half U and half V data, and there are Y_rows/2 of UV_rows
        for (w in 0..<floor(cropRect.height() / 2.0).toInt()) {
            val imgPos = imgYPlaneSize + (cropRect.top / 2 + w) * imgWidth + cropRect.left
            val croppedImgPos = croppedImgYPlaneSize + (w * cropRect.width())
            System.arraycopy(img, imgPos, croppedImg, croppedImgPos, cropRect.width())
        }

        return croppedImg
    }

    companion object {
        const val NV21_CROP_RECT_MULTIPLIER = 1.5
        const val RELATIVE_SCANNING_WIDTH_FULL = 1f
        const val SINGLE_PIXEL_STRIDE = 1
        const val YUV_BYTE_ARRAY_MULTIPLIER = 2
        const val YUV_SIZING_SPLIT = 4
    }
}
