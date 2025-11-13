package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Camera
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MutableCameraHolder(
    private val state: MutableStateFlow<Camera?> = MutableStateFlow(null),
) : CameraContentState.CameraHolder.Complete {
    /**
     * [StateFlow] for the internal [state] property.
     *
     * Be mindful when collecting the [camera] property, due to UI recomposition.
     */
    override val camera: StateFlow<Camera?> = state
    override fun update(camera: Camera?) {
        state.update { camera }
    }
}
