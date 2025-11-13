package uk.gov.android.ui.componentsv2.camera.qr

import android.graphics.ImageFormat
import android.graphics.Rect
import android.media.Image
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.ui.geometry.Size
import com.google.mlkit.vision.common.InputImage
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import kotlin.experimental.inv
import kotlin.math.ceil
import kotlin.math.floor

/**
 * [ImageProxyConverter] implementation that crops the input from a provided [ImageProxy].
 *
 * Internally converts [ImageFormat.YUV_420_888] into [ImageFormat.NV21] via byte array
 * manipulation.
 *
 * Image cropping is based on the centre point of the input image, going outwards.
 *
 * @param expectedFormat The expected [ImageFormat] of the provided [ImageProxy]. If the format
 * doesn't match what's contained within the [ImageProxy], this implementation returns null when
 * calling [ImageProxyConverter.convert]. Defaults to [ImageFormat.YUV_420_888].
 * @param relativeScanningWidth The percentage of the input image's width that should be used for
 * the output image's size. Defaults to [IMAGE_WIDTH_CROP_MULTIPLIER].
 * @param relativeScanningHeight The percentage of the input image's height that should be used for
 * the output image's size. Defaults to [IMAGE_WIDTH_CROP_MULTIPLIER].
 */
class CentrallyCroppedImageProxyConverter(
    private val expectedFormat: Int = ImageFormat.YUV_420_888,
    private val relativeScanningWidth: Float = IMAGE_WIDTH_CROP_MULTIPLIER,
    private val relativeScanningHeight: Float = IMAGE_WIDTH_CROP_MULTIPLIER,
) : ImageProxyConverter {
    @OptIn(ExperimentalGetImage::class)
    override fun convert(proxy: ImageProxy): InputImage? = proxy.image?.let { image ->
        if (proxy.format == expectedFormat) {
            val (size, imageArray) = image.toCentralScanningArea(
                relativeScanningWidth = relativeScanningWidth,
                relativeScanningHeight = relativeScanningHeight,
            )

            InputImage.fromByteArray(
                imageArray,
                size.width.toInt(),
                size.height.toInt(),
                proxy.imageInfo.rotationDegrees,
                ImageFormat.NV21,
            )
        } else {
            null
        }
    }

    /**
     * @return A [Pair] containing the cropped image's [Size], as well as cropped output as an
     * NV-21 style byte array.
     */
    fun Image.toCentralScanningArea(
        relativeScanningWidth: Float,
        relativeScanningHeight: Float,
    ): Pair<Size, ByteArray> {
        val centerX = width / 2
        val centerY = height / 2
        val scanningAreaWidth = width * relativeScanningWidth
        val scanningAreaHeight = height * relativeScanningHeight

        val scanningAreaStartWidth = centerX - (scanningAreaWidth / 2)
        val scanningAreaStartHeight = centerY - (scanningAreaHeight / 2)
        val croppedArea = Rect(
            scanningAreaStartWidth.toInt(),
            scanningAreaStartHeight.toInt(),
            (scanningAreaStartWidth + scanningAreaWidth).toInt(),
            (scanningAreaStartHeight + scanningAreaHeight).toInt(),
        )

        val nv21 = yuv420888ToNv21(this)
        val croppedNv21 = cropNV21(nv21, width, croppedArea)

        return Size(
            scanningAreaWidth,
            scanningAreaHeight,
        ) to croppedNv21
    }

    /**
     * Converts the provided [image] into an NV-21 style byte array.
     */
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
        const val IMAGE_WIDTH_CROP_MULTIPLIER = .6f
        const val SINGLE_PIXEL_STRIDE = 1
        const val YUV_BYTE_ARRAY_MULTIPLIER = 2
        const val YUV_SIZING_SPLIT = 4
    }
}
