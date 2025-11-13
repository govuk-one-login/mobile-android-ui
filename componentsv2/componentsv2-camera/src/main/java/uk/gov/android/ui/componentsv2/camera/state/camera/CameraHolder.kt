package uk.gov.android.ui.componentsv2.camera.state.camera

import androidx.camera.core.Camera
import kotlinx.coroutines.flow.StateFlow

/**
 * Sealed interface for holding objects designed to interact with a [StateFlow] of [Camera]
 * objects.
 */
sealed interface CameraHolder {
    /**
     * Combines functionality of the [State] and [Updater] interfaces.
     *
     * @see State
     * @see Updater
     */
    interface Complete : State, Updater

    /**
     * Interface for exposing an [Camera] [StateFlow]. Commonly paired with the [Updater]
     * interface.
     *
     * @see Updater
     */
    interface State {
        val camera: StateFlow<Camera?>

        /**
         * Function that returns the latest [Camera] value within the [camera] property.
         *
         * Use this when collecting the [camera] causes recomposition / testing issues.
         */
        fun getCurrentCamera(): Camera? = camera.value
    }

    /**
     * Interface for updating an [Camera] object. Commonly paired with the [State] interface.
     *
     * @see State
     */
    fun interface Updater {
        fun update(camera: Camera?)
    }
}
