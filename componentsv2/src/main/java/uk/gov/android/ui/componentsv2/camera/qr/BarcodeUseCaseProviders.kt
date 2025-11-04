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
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProvider
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter.Companion.RELATIVE_SCANNING_WIDTH_FULL

object BarcodeUseCaseProviders {

    @OptIn(ExperimentalGetImage::class)
    @JvmStatic
    fun barcodeAnalysis(
        context: Context,
        relativeScanningWidth: Float = RELATIVE_SCANNING_WIDTH_FULL,
        options: BarcodeScannerOptions = provideQrScanningOptions(provideZoomOptions()),
        callback: BarcodeScanResult.Callback = BarcodeScanResult.Callback {},
    ): CameraUseCaseProvider {
        val analyzer = BarcodeImageAnalyzer(
            options = options,
            callback = callback,
            converter = CentrallyCroppedImageProxyConverter(
                relativeScanningWidth = relativeScanningWidth,
            ),
        )

        return CameraUseCaseProvider.imageAnalysis(
            analyzer = analyzer,
            backpressureStrategy = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST,
            executor = ContextCompat.getMainExecutor(context),
            outputImageFormat = ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888,
        )
    }

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
