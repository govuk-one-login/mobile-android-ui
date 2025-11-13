package uk.gov.android.ui.componentsv2.camera.state.camera

import androidx.camera.core.Camera
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * [CameraHolder.Complete] implementation that defers to the internal [state].
 */
class MutableCameraHolder(
    private val state: MutableStateFlow<Camera?> = MutableStateFlow(null),
) : CameraHolder.Complete {
    /**
     * [kotlinx.coroutines.flow.StateFlow] for the internal [state] property.
     *
     * Be mindful when collecting the [camera] property, due to UI recomposition.
     */
    override val camera: StateFlow<Camera?> = state
    override fun update(camera: Camera?) {
        state.update { camera }
    }
}
