package uk.gov.android.ui.componentsv2.camera.state.surfacerequest

import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import kotlinx.coroutines.flow.StateFlow

/**
 * Sealed interface for holding objects designed to interact with a [StateFlow] of [SurfaceRequest]
 * objects.
 */
sealed interface SurfaceRequester {
    /**
     * Combines functionality of the [State] and [Updater] interfaces.
     *
     * @see State
     * @see Updater
     */
    interface Complete : State, Updater

    /**
     * Interface for exposing a [SurfaceRequest] [StateFlow]. Commonly paired with the [Updater]
     * interface.
     *
     * @see Updater
     */
    interface State {
        val surfaceRequest: StateFlow<SurfaceRequest?>
    }

    /**
     * Interface for updating a [SurfaceRequest] object. Commonly paired with the [State] interface.
     *
     * @see State
     */
    fun interface Updater : Preview.SurfaceProvider {
        override fun onSurfaceRequested(request: SurfaceRequest) {
            update(request)
        }
        fun update(surfaceRequest: SurfaceRequest?)
    }
}
