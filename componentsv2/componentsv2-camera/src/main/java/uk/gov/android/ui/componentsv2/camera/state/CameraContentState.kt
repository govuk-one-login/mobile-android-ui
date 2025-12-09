package uk.gov.android.ui.componentsv2.camera.state

import uk.gov.android.ui.componentsv2.camera.state.analyzer.ImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.state.camera.CameraHolder
import uk.gov.android.ui.componentsv2.camera.state.capture.ImageCapturer
import uk.gov.android.ui.componentsv2.camera.state.preview.ImagePreviewer
import uk.gov.android.ui.componentsv2.camera.state.surfacerequest.SurfaceRequester

/**
 * Sealed interface containing convenience interfaces combining different camera use case states.
 */
sealed interface CameraContentState {

    /**
     * Interface that combines both [States] and [Updaters].
     *
     * @sample uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
     *
     * @see States
     * @see Updaters
     */
    interface Complete :
        CameraHolder.Complete,
        ImageAnalyzer.Complete,
        ImageCapturer.Complete,
        ImagePreviewer.Complete,
        SurfaceRequester.Complete,
        States,
        Updaters

    /**
     * Property bag Interface that combines all of the StateFlow based Interfaces.
     *
     * @see CameraHolder.State
     * @see ImageAnalyzer.State
     * @see ImageCapturer.State
     * @see ImagePreviewer.State
     * @see SurfaceRequester.State
     */
    interface States :
        CameraHolder.State,
        ImageAnalyzer.State,
        ImageCapturer.State,
        ImagePreviewer.State,
        SurfaceRequester.State

    /**
     * Interface that combines all of the StateFlow updater Interfaces.
     *
     * @see CameraHolder.Updater
     * @see ImageAnalyzer.Updater
     * @see ImageCapturer.Updater
     * @see ImagePreviewer.Updater
     * @see SurfaceRequester.Updater
     */
    interface Updaters :
        CameraHolder.Updater,
        ImageAnalyzer.Updater,
        ImageCapturer.Updater,
        ImagePreviewer.Updater,
        SurfaceRequester.Updater
}
