package uk.gov.android.ui.componentsv2.camera.qr

import android.content.Context
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProviders
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter.Companion.IMAGE_WIDTH_CROP_MULTIPLIER

/**
 * Wrapper object that holds functions generating different [androidx.camera.core.UseCase]
 * implementations.
 */
object BarcodeUseCaseProviders {

    /**
     * Creates an instance of [ImageAnalysis] to find [Barcode]s via the Android-powered device's
     * camera.
     *
     * @param backpressureStrategy The [ImageAnalysis] strategy to utilise. This changes camera
     * frame queueing behaviour. Defaults to [ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST].
     * @param callback The [BarcodeScanResult.Callback] implementation that receives
     * [BarcodeScanResult] sub-classes. Defaults to an empty code block, meaning that nothing occurs
     * when processing camera output.
     * @param context The Android [Context] to fetch an [java.util.concurrent.Executor] from.
     * @param converter The [ImageProxyConverter] transforms provided [ImageProxy] inputs into
     * instances of [InputImage]. Defaults to using an instance of
     * [CentrallyCroppedImageProxyConverter].
     * @param options The [BarcodeScannerOptions] that initialises a
     * [com.google.mlkit.vision.barcode.BarcodeScanner]. Defaults to using
     * [provideQrScanningOptions] with the result of [provideZoomOptions].
     *
     * @return An [ImageAnalysis] use case with an instance of [BarcodeImageAnalyzer] defined via
     * [ImageAnalysis.setAnalyzer].
     *
     * @see BarcodeImageAnalyzer
     * @see CentrallyCroppedImageProxyConverter
     * @see provideQrScanningOptions
     * @see provideZoomOptions
     */
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

    /**
     * @param camera The function to provide an instance of a [Camera]. Defaults to returning
     * `null`, meaning that zoom ratios aren't applied.
     *
     * @return An instance of [ZoomSuggestionOptions] that update the
     * [androidx.camera.core.CameraControl.setZoomRatio] to suggested values.
     */
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

    /**
     * @return An instance of [BarcodeScannerOptions] tailored to [Barcode.FORMAT_QR_CODE].
     */
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
