package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

/**
 * Companion object for holding functions that generate various camera
 * [UseCase] objects.
 */
object CameraUseCaseProviders {

    /**
     * Creates an [ImageAnalysis] object that's passed to a
     * [ProcessCameraProvider.bindToLifecycle] call.
     *
     * @param analyzer The behaviour to perform on each camera frame.
     * @param backpressureStrategy The [ImageAnalysis] strategy to utilise. This changes camera
     * frame queueing behaviour. Defaults to [ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST].
     * @param executor The [Executor] that the provided [analyzer] runs within.
     * @param outputImageFormat The [ImageAnalysis] output that the [ImageAnalysis.Analyzer]'s
     * [androidx.camera.core.ImageProxy] should have. Defaults to
     * [ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888].
     *
     * @see ContextCompat.getMainExecutor
     * @see ImageAnalysis.Builder.setBackpressureStrategy
     * @see ImageAnalysis.Builder.setOutputImageFormat
     * @see ImageAnalysis.setAnalyzer
     * @see ProcessCameraProvider.bindToLifecycle
     *
     * @return An [ImageAnalysis] object that defers to the provided [analyzer] parameter.
     */
    @JvmStatic
    fun imageAnalysis(
        analyzer: ImageAnalysis.Analyzer,
        backpressureStrategy: Int = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST,
        executor: Executor,
        outputImageFormat: Int = ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888,
    ): ImageAnalysis = ImageAnalysis
        .Builder()
        .setOutputImageFormat(outputImageFormat)
        .setBackpressureStrategy(backpressureStrategy)
        .build()
        .apply {
            setAnalyzer(executor, analyzer)
        }
}
