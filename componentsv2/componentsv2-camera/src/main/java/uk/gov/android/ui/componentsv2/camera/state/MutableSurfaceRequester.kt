package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.SurfaceRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MutableSurfaceRequester(
    private val state: MutableStateFlow<SurfaceRequest?> = MutableStateFlow(null),
) : CameraContentState.SurfaceRequester.Complete {
    override val surfaceRequest: StateFlow<SurfaceRequest?> = state
    override fun update(surfaceRequest: SurfaceRequest?) {
        state.update { surfaceRequest }
    }
}
