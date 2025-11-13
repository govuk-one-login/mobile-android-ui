package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import org.mockito.Mockito.mock
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelHelper

class CameraContentStateHelper {
    val preview = Preview.Builder().build()
    val imageAnalysis = ImageAnalysis.Builder().build()
    val imageCapture = ImageCapture.Builder().build()
    val camera = CameraContentViewModelHelper.DummyCamera
    val surfaceRequest: SurfaceRequest = mock()
}
