package uk.gov.android.ui.componentsv2.camera.state.analyzer

import androidx.camera.core.ImageAnalysis
import kotlinx.coroutines.flow.StateFlow

/**
 * Sealed interface for holding objects designed to interact with a [StateFlow] of [ImageAnalysis]
 * objects.
 */
sealed interface ImageAnalyzer {
    /**
     * Combines functionality of the [State] and [Updater] interfaces.
     *
     * @see State
     * @see Updater
     */
    interface Complete : State, Updater

    /**
     * Interface for exposing an [ImageAnalysis] [StateFlow]. Commonly paired with the [Updater]
     * interface.
     *
     * @see Updater
     */
    interface State {
        val imageAnalysis: StateFlow<ImageAnalysis?>
    }

    /**
     * Interface for updating an [ImageAnalysis] object. Commonly paired with the [State] interface.
     *
     * @see State
     */
    fun interface Updater {
        fun update(imageAnalysis: ImageAnalysis?)
    }
}
