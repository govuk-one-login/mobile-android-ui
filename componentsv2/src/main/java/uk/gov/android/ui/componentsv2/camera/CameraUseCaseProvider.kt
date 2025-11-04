package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import java.util.concurrent.Executor

fun interface CameraUseCaseProvider {
    fun provide(): UseCase

    companion object {

        @JvmStatic
        fun preview(
            surfaceProvider: Preview.SurfaceProvider,
        ): CameraUseCaseProvider = CameraUseCaseProvider {
            Preview.Builder().build().apply {
                this.surfaceProvider = surfaceProvider
            }
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
    }
}
