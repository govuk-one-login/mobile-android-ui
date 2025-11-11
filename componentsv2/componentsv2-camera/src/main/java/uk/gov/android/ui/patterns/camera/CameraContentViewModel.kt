package uk.gov.android.ui.patterns.camera

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CameraContentViewModel() : ViewModel(), Preview.SurfaceProvider {
    private val _surfaceRequestFlow = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> =
        _surfaceRequestFlow

    private val _camera = MutableStateFlow<Camera?>(null)

    private val _previewUseCase = MutableStateFlow(
        Preview.Builder().build().apply {
            this.surfaceProvider = this@CameraContentViewModel
        },
    )
    val previewUseCase: StateFlow<Preview> = _previewUseCase

    private val _analysisUseCase = MutableStateFlow<ImageAnalysis?>(null)
    val analysisUseCase: StateFlow<ImageAnalysis?> = _analysisUseCase

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

    fun addAll(vararg useCases: UseCase) = addAll(useCases.toList())

    fun getCurrentCamera(): Camera? = _camera.value

    fun update(useCase: Preview) {
        _previewUseCase.update { useCase }
    }

    fun update(useCase: ImageAnalysis) {
        _analysisUseCase.update { useCase }
    }

    fun update(useCase: ImageCapture) {
        _imageCaptureUseCase.update { useCase }
    }

    fun update(result: Camera) {
        _camera.update { result }
    }

    fun resetState() {
        _camera.update { null }
        _previewUseCase.update {
            Preview.Builder().build().apply {
                this.surfaceProvider = this@CameraContentViewModel
            }
        }
        _analysisUseCase.update { null }
        _imageCaptureUseCase.update { null }
        _surfaceRequestFlow.update { null }
    }

    override fun onCleared() {
        super.onCleared()
        resetState()
    }

    override fun onSurfaceRequested(request: SurfaceRequest) {
        _surfaceRequestFlow.update { request }
    }
}
