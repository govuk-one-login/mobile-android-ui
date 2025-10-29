package uk.gov.android.ui.componentsv2.camera.usecase

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions
import com.google.mlkit.vision.barcode.common.Barcode
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeScanResult
import java.util.concurrent.Executors

fun interface CameraUseCaseProvider {
    fun provide(): UseCase

    companion object {

        @JvmStatic
        fun providePreviewUseCase(
            surfaceProvider: Preview.SurfaceProvider,
        ): CameraUseCaseProvider = CameraUseCaseProvider {
            Preview.Builder().build().apply {
                this.surfaceProvider = surfaceProvider
            }
        }

        @JvmStatic
        fun provideBarcodeAnalysis(
            options: BarcodeScannerOptions = provideQrScanningOptions(provideZoomOptions()),
            onBarcodeResult: (BarcodeScanResult) -> Unit = {},
        ): CameraUseCaseProvider = CameraUseCaseProvider {
            val provideImageAnalysis: ImageAnalysis =
                ImageAnalysis
                    .Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

            provideImageAnalysis.setAnalyzer(
                Executors.newSingleThreadExecutor(),
                BarcodeImageAnalyzer(
                    options = options,
                    onResult = onBarcodeResult,
                ),
            )

            provideImageAnalysis
        }

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

        const val MAX_ZOOM_RATIO = 10f
    }
}
