package uk.gov.android.ui.componentsv2.camera

import android.content.Context
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCase
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import java.util.concurrent.Executor

@Suppress("UNCHECKED_CAST")
fun cameraContentViewModelFactory(
    context: Context,
) = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CameraContentViewModel(
            executor = ContextCompat.getMainExecutor(context),
        ) as T
    }
}

class CameraContentViewModel(
    private val executor: Executor,
) : ViewModel(), Preview.SurfaceProvider {
    private val _surfaceRequestFlow = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequestFlow: StateFlow<SurfaceRequest?> =
        _surfaceRequestFlow

    private val _camera = MutableStateFlow<Camera?>(null)

    private val _previewUseCase = MutableStateFlow<Preview>(
        Preview.Builder().build().apply {
            this.surfaceProvider = this@CameraContentViewModel
        },
    )
    val previewUseCase: StateFlow<Preview> = _previewUseCase

    private val _imageAnalyzer = MutableStateFlow<ImageAnalysis.Analyzer?>(null)
    val imageAnalyzer: StateFlow<ImageAnalysis.Analyzer?> = _imageAnalyzer

    private val _analysisUseCase = MutableStateFlow<ImageAnalysis?>(null)
    val analysisUseCase = combine(
        _analysisUseCase,
        imageAnalyzer,
    ) { analysis, analyzer ->
        analyzer?.let { useCase ->
            analysis?.setAnalyzer(executor, useCase)
        }

        analysis
    }

    private val _imageCaptureUseCase = MutableStateFlow<ImageCapture?>(null)
    val imageCaptureUseCase: StateFlow<ImageCapture?> = _imageCaptureUseCase

    fun addAll(useCases: List<UseCase>) = useCases.forEach { useCase ->
        when (useCase) {
            is ImageAnalysis -> update(useCase)
            is ImageCapture -> update(useCase)
            is Preview -> update(useCase)
            else -> {
                throw IllegalArgumentException(
                    "Cannot update viewmodel state with an instance of " +
                        useCase::class.java.simpleName,
                )
            }
        }
    }

    fun getCurrentCamera(): Camera? = _camera.value

    fun update(builder: Preview) {
        _previewUseCase.update { builder }
    }

    fun update(builder: ImageAnalysis) {
        _analysisUseCase.update { builder }
    }

    fun update(builder: ImageCapture) {
        _imageCaptureUseCase.update { builder }
    }

    fun update(analyzer: ImageAnalysis.Analyzer) {
        _imageAnalyzer.update { analyzer }
    }

    fun update(request: SurfaceRequest) {
        _surfaceRequestFlow.update { request }
    }

    fun update(result: Camera) {
        _camera.update { result }
    }

    fun removeUseCases() {
        _camera.update { null }
        _previewUseCase.update {
            Preview.Builder().build().apply {
                this.surfaceProvider = this@CameraContentViewModel
            }
        }
        _analysisUseCase.update { null }
        _imageCaptureUseCase.update { null }
    }

    override fun onSurfaceRequested(request: SurfaceRequest) {
        _surfaceRequestFlow.update { request }
    }
}
