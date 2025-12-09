package uk.gov.android.ui.componentsv2.camera.state.surfacerequest

import androidx.camera.core.SurfaceRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * [SurfaceRequester.Complete] implementation that defers to the internal [state].
 */
class MutableSurfaceRequester(
    private val state: MutableStateFlow<SurfaceRequest?> = MutableStateFlow(null),
) : SurfaceRequester.Complete {
    override val surfaceRequest: StateFlow<SurfaceRequest?> = state
    override fun update(surfaceRequest: SurfaceRequest?) {
        state.update { surfaceRequest }
    }
}
