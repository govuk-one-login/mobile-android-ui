package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.Camera
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCase
import androidx.camera.core.UseCaseGroup
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CameraContentViewModel : ViewModel() {
    private val _surfaceRequestFlow = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequestFlow: StateFlow<SurfaceRequest?> =
        _surfaceRequestFlow

    private val _camera = MutableStateFlow<Camera?>(null)

    private val _useCases = MutableStateFlow(UseCaseGroup.Builder())

    val useCasesBuilder: StateFlow<UseCaseGroup.Builder> = _useCases

    fun getCurrentCamera(): Camera? = _camera.value

    fun update(request: SurfaceRequest) {
        _surfaceRequestFlow.update { request }
    }

    fun update(result: Camera) {
        _camera.update { result }
    }

    fun add(useCase: UseCase) = _useCases.update { builder ->
        builder.addUseCase(useCase)
    }

    fun addAll(useCases: List<UseCase>) = useCases.forEach(::add)

    fun addAll(vararg useCases: UseCase) = addAll(useCases.toList())

    fun removeUseCases() {
        _useCases.update {
            UseCaseGroup.Builder()
        }
        _camera.update { null }
    }
}
