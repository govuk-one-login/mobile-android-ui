package uk.gov.android.ui.componentsv2.camera.state.capture

import androidx.camera.core.ImageCapture
import kotlinx.coroutines.flow.StateFlow

/**
 * Sealed interface for holding objects designed to interact with a [StateFlow] of [ImageCapture]
 * objects.
 */
sealed interface ImageCapturer {
    /**
     * Combines functionality of the [State] and [Updater] interfaces.
     *
     * @see State
     * @see Updater
     */
    interface Complete : State, Updater

    /**
     * Interface for exposing an [ImageCapture] [StateFlow]. Commonly paired with the [Updater]
     * interface.
     *
     * @see Updater
     */
    interface State {
        val imageCapture: StateFlow<ImageCapture?>
    }

    /**
     * Interface for updating an [ImageCapture] object. Commonly paired with the [State] interface.
     *
     * @see State
     */
    fun interface Updater {
        fun update(imageCapture: ImageCapture?)
    }
}
