package uk.gov.android.ui.componentsv2.camera.state

import kotlinx.coroutines.flow.MutableStateFlow
import uk.gov.android.ui.componentsv2.camera.state.analyzer.ImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.state.analyzer.MutableImageAnalyzer
import uk.gov.android.ui.componentsv2.camera.state.camera.CameraHolder
import uk.gov.android.ui.componentsv2.camera.state.camera.MutableCameraHolder
import uk.gov.android.ui.componentsv2.camera.state.capture.ImageCapturer
import uk.gov.android.ui.componentsv2.camera.state.capture.MutableImageCapturer
import uk.gov.android.ui.componentsv2.camera.state.preview.ImagePreviewer
import uk.gov.android.ui.componentsv2.camera.state.preview.MutableImagePreviewer
import uk.gov.android.ui.componentsv2.camera.state.surfacerequest.MutableSurfaceRequester
import uk.gov.android.ui.componentsv2.camera.state.surfacerequest.SurfaceRequester

/**
 * [CameraContentState.Complete] implementation that relies upon interface delegation.
 *
 * By default, all constructor parameters are implementations backed by [MutableStateFlow] objects.
 */
class CompleteCameraContentState(
    cameraHolder: CameraHolder.Complete = MutableCameraHolder(),
    imageAnalyzer: ImageAnalyzer.Complete = MutableImageAnalyzer(),
    imageCapturer: ImageCapturer.Complete = MutableImageCapturer(),
    imagePreviewer: ImagePreviewer.Complete = MutableImagePreviewer(),
    surfaceRequester: SurfaceRequester.Complete = MutableSurfaceRequester(),
) : CameraHolder.Complete by cameraHolder,
    ImageAnalyzer.Complete by imageAnalyzer,
    ImageCapturer.Complete by imageCapturer,
    ImagePreviewer.Complete by imagePreviewer,
    SurfaceRequester.Complete by surfaceRequester,
    CameraContentState.Complete
