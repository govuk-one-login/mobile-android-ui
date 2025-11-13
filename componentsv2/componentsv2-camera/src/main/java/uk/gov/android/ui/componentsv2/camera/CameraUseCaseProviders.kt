package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import java.util.concurrent.Executor

object CameraUseCaseProviders {
    @JvmStatic
    fun preview(
        surfaceProvider: Preview.SurfaceProvider,
    ): Preview = Preview.Builder().build().apply {
        this.surfaceProvider = surfaceProvider
    }

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
