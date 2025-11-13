package uk.gov.android.ui.componentsv2.camera.state.preview

import androidx.camera.core.Preview
import kotlinx.coroutines.flow.StateFlow

/**
 * Sealed interface for holding objects designed to interact with a [StateFlow] of [Preview]
 * objects.
 */
sealed interface ImagePreviewer {
    /**
     * Combines functionality of the [State] and [Updater] interfaces.
     *
     * @see State
     * @see Updater
     */
    interface Complete : State, Updater

    /**
     * Interface for exposing a [Preview] [StateFlow]. Commonly paired with the [Updater]
     * interface.
     *
     * @see Updater
     */
    interface State {
        val preview: StateFlow<Preview>
    }

    /**
     * Interface for updating a [Preview] object. Commonly paired with the [State] interface.
     *
     * @see State
     */
    fun interface Updater {
        fun update(preview: Preview)
    }
}
