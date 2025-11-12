package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import kotlinx.coroutines.flow.StateFlow

sealed interface CameraContentState {
    sealed interface CameraHolder {
        interface Complete : State, Updater
        interface State {
            val camera: StateFlow<Camera?>

            /**
             * Function that returns the latest [Camera] value within the [camera] property.
             *
             * Use this when collecting the [camera] causes recomposition / testing issues.
             */
            fun getCurrentCamera(): Camera? = camera.value
        }
        fun interface Updater {
            fun update(camera: Camera?)
        }
    }

    interface Complete : States, Updaters

    sealed interface ImageAnalyzer {
        interface Complete : State, Updater

        interface State {
            val imageAnalysis: StateFlow<ImageAnalysis?>
        }

        fun interface Updater {
            fun update(imageAnalysis: ImageAnalysis?)
        }
    }

    sealed interface ImageCapturer {
        interface Complete : State, Updater

        interface State {
            val imageCapture: StateFlow<ImageCapture?>
        }

        fun interface Updater {
            fun update(imageCapture: ImageCapture?)
        }
    }

    sealed interface Previewer {
        interface Complete : State, Updater

        interface State {
            val preview: StateFlow<Preview>
        }

        fun interface Updater {
            fun update(preview: Preview)
        }
    }

    interface States :
        CameraHolder.State,
        ImageAnalyzer.State,
        ImageCapturer.State,
        Previewer.State,
        SurfaceRequester.State

    sealed interface SurfaceRequester {
        interface Complete : State, Updater

        interface State {
            val surfaceRequest: StateFlow<SurfaceRequest?>
        }

        fun interface Updater : Preview.SurfaceProvider {
            override fun onSurfaceRequested(request: SurfaceRequest) {
                update(request)
            }
            fun update(surfaceRequest: SurfaceRequest?)
        }
    }

    interface Updaters :
        CameraHolder.Updater,
        ImageAnalyzer.Updater,
        ImageCapturer.Updater,
        Previewer.Updater,
        SurfaceRequester.Updater
}
