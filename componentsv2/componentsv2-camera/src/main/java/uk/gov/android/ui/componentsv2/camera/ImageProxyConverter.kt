package uk.gov.android.ui.componentsv2.camera

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage

/**
 * Functional interface for converting a provided camera [ImageProxy] into an [InputImage] for
 * MLKit image analyzers.
 *
 * @sample ImageProxyConverter.simple
 */
fun interface ImageProxyConverter {
    fun convert(proxy: ImageProxy): InputImage?

    companion object {
        /**
         * Basic conversion with no additional handling.
         */
        @OptIn(ExperimentalGetImage::class)
        @JvmStatic
        fun simple(): ImageProxyConverter = ImageProxyConverter { proxy ->
            proxy.image?.let { image ->
                InputImage.fromMediaImage(
                    image,
                    proxy.imageInfo.rotationDegrees,
                )
            }
        }
    }
}
