package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState
import uk.gov.android.ui.componentsv2.camera.state.CompleteCameraContentState

/**
 * [ViewModel] companion to the [CameraContent] Composable UI.
 *
 * Relies on interface delegation to provide [StateFlow] properties exposing Camera [UseCase]s.
 */
class CameraContentViewModel @JvmOverloads constructor(
    state: CameraContentState.Complete = CompleteCameraContentState(),
) : ViewModel(),
    CameraContentState.Complete by state {

    init {
        update(
            preview =
            Preview.Builder().build().apply {
                this.surfaceProvider = this@CameraContentViewModel
            },
        )
    }

    /**
     * Adds the provided [useCases] to the delegated [StateFlow] properties based on the
     * implementation.
     *
     * @throws IllegalArgumentException when providing an unknown [UseCase] implementation.
     */
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

    /**
     * Adds the provided [useCases] to the delegated [StateFlow] properties based on the
     * implementation.
     *
     * @throws IllegalArgumentException when providing an unknown [UseCase] implementation.
     */
    fun addAll(vararg useCases: UseCase) = addAll(useCases.toList())

    /**
     * Updates the delegated [StateFlow] properties to their initially expected values.
     */
    fun resetState() {
        update(camera = null)
        update(
            preview =
            Preview.Builder().build().apply {
                this.surfaceProvider = this@CameraContentViewModel
            },
        )
        update(imageAnalysis = null)
        update(imageCapture = null)
        update(surfaceRequest = null)
    }

    override fun onCleared() {
        super.onCleared()
        resetState()
    }
}
