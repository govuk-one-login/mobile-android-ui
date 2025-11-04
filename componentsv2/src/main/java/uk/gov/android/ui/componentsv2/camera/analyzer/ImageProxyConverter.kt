package uk.gov.android.ui.componentsv2.camera.analyzer

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage

fun interface ImageProxyConverter {
    fun convert(proxy: ImageProxy): InputImage?

    companion object {
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
