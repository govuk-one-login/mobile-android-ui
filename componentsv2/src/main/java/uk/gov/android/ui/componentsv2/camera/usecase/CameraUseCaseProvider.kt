package uk.gov.android.ui.componentsv2.camera.usecase

import android.content.Context
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.ui.unit.IntSize
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions
import com.google.mlkit.vision.barcode.common.Barcode
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeScanResult
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.CentrallyCroppedImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.CentrallyCroppedImageProxyConverter.Companion.RELATIVE_SCANNING_WIDTH_FULL
import java.util.concurrent.Executor

fun interface CameraUseCaseProvider {
    fun provide(): UseCase

    companion object {
        const val MAX_ZOOM_RATIO = 10f

        @JvmStatic
        fun preview(
            surfaceProvider: Preview.SurfaceProvider,
        ): CameraUseCaseProvider = CameraUseCaseProvider {
            Preview.Builder().build().apply {
                this.surfaceProvider = surfaceProvider
            }
        }

        @OptIn(ExperimentalGetImage::class)
        @JvmStatic
        fun barcodeAnalysis(
            context: Context,
            windowContainer: IntSize,
            relativeScanningWidth: Float = RELATIVE_SCANNING_WIDTH_FULL,
            options: BarcodeScannerOptions = provideQrScanningOptions(provideZoomOptions()),
            onBarcodeResult: (BarcodeScanResult) -> Unit = {},
        ): CameraUseCaseProvider {
            val analyzer = BarcodeImageAnalyzer(
                options = options,
                onResult = onBarcodeResult,
                converter = CentrallyCroppedImageProxyConverter(
                    relativeScanningWidth = relativeScanningWidth,
                ),
            )

            return imageAnalysis(
                analyzer = analyzer,
                backpressureStrategy = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST,
                executor = ContextCompat.getMainExecutor(context),
                outputImageFormat = ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888,
            )
        }

        @JvmStatic
        fun imageAnalysis(
            analyzer: ImageAnalysis.Analyzer,
            backpressureStrategy: Int = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST,
            executor: Executor,
            outputImageFormat: Int = ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888,
        ): CameraUseCaseProvider = CameraUseCaseProvider {
            val provideImageAnalysis: ImageAnalysis =
                ImageAnalysis
                    .Builder()
                    .setOutputImageFormat(outputImageFormat)
                    .setBackpressureStrategy(backpressureStrategy)
                    .build()

            provideImageAnalysis.setAnalyzer(executor, analyzer)

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
    }
}
