package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.lifecycle.ViewModel
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState.CameraHolder
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState.ImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState.ImageCapturer
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState.Previewer
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState.SurfaceRequester
import uk.gov.android.ui.componentsv2.camera.state.MutableCameraHolder
import uk.gov.android.ui.componentsv2.camera.state.MutableImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.state.MutableImageCapturer
import uk.gov.android.ui.componentsv2.camera.state.MutablePreviewer
import uk.gov.android.ui.componentsv2.camera.state.MutableSurfaceRequester

class CameraContentViewModel @JvmOverloads constructor(
    cameraHolder: CameraHolder.Complete = MutableCameraHolder(),
    imageAnalyzer: ImageAnalyzer.Complete = MutableImageAnalyzer(),
    imageCapturer: ImageCapturer.Complete = MutableImageCapturer(),
    previewer: Previewer.Complete = MutablePreviewer(),
    surfaceRequester: SurfaceRequester.Complete = MutableSurfaceRequester(),
) : ViewModel(),
    CameraHolder.Complete by cameraHolder,
    ImageAnalyzer.Complete by imageAnalyzer,
    ImageCapturer.Complete by imageCapturer,
    Previewer.Complete by previewer,
    SurfaceRequester.Complete by surfaceRequester,
    CameraContentState.Complete {

    init {
        update(
            preview =
            Preview.Builder().build().apply {
                this.surfaceProvider = this@CameraContentViewModel
            },
        )
    }

    fun addAll(useCases: List<UseCase>) = useCases.forEach { useCase ->
        when (useCase) {
            is ImageAnalysis -> update(useCase)
            is ImageCapture -> update(useCase)
            is Preview -> update(useCase)
            else -> {
                throw IllegalArgumentException(
                    "Cannot update viewmodel state with an instance of " +
                        useCase::class.java.simpleName,
                )
            }
        }
    }

    fun addAll(vararg useCases: UseCase) = addAll(useCases.toList())

    fun resetState() {
        update(camera = null)
        update(
            preview =
            Preview.Builder().build().apply {
                this.surfaceProvider = this@CameraContentViewModel
            },
        )
        update(imageAnalysis = null)
        update(imageCapture = null)
        update(surfaceRequest = null)
    }

    override fun onCleared() {
        super.onCleared()
        resetState()
    }
}
