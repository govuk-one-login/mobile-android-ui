package uk.gov.android.ui.componentsv2.camera.qr

import android.content.Context
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions
import com.google.mlkit.vision.barcode.common.Barcode
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProviders
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter.Companion.IMAGE_WIDTH_CROP_MULTIPLIER

object BarcodeUseCaseProviders {

    @OptIn(ExperimentalGetImage::class)
    @JvmStatic
    fun barcodeAnalysis(
        context: Context,
        backpressureStrategy: Int = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST,
        converter: ImageProxyConverter = CentrallyCroppedImageProxyConverter(
            relativeScanningWidth = IMAGE_WIDTH_CROP_MULTIPLIER,
            relativeScanningHeight = IMAGE_WIDTH_CROP_MULTIPLIER,
        ),
        options: BarcodeScannerOptions = provideQrScanningOptions(provideZoomOptions()),
        callback: BarcodeScanResult.Callback = BarcodeScanResult.Callback { _, _ -> },
    ): ImageAnalysis = CameraUseCaseProviders.imageAnalysis(
        analyzer = BarcodeImageAnalyzer(
            options = options,
            callback = callback,
            converter = converter,
        ),
        backpressureStrategy = backpressureStrategy,
        executor = ContextCompat.getMainExecutor(context),
        outputImageFormat = ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888,
    )

    const val MAX_ZOOM_RATIO = 10f

    @JvmStatic
    fun provideZoomOptions(
        camera: () -> Camera? = { null },
    ): ZoomSuggestionOptions =
        ZoomSuggestionOptions
            .Builder { zoomRatio ->
                camera()?.cameraControl?.setZoomRatio(zoomRatio)
                true
            }.setMaxSupportedZoomRatio(camera()?.provideMaxZoomRatio() ?: MAX_ZOOM_RATIO)
            .build()

    @JvmStatic
    fun provideQrScanningOptions(
        provideZoomOptions: ZoomSuggestionOptions,
    ): BarcodeScannerOptions = BarcodeScannerOptions
        .Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .setZoomSuggestionOptions(provideZoomOptions)
        .build()

    fun Camera.provideMaxZoomRatio(): Float = cameraInfo
        .zoomState
        .value
        ?.maxZoomRatio ?: MAX_ZOOM_RATIO
}
