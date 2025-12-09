package uk.gov.android.ui.componentsv2.camera

import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoCamera
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoImageAnalysis
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoImageCapture
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoSurfaceRequest
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasPreview

/**
 * Convenience object for holding assertions related to the [CameraContentViewModel].
 */
object CameraContentViewModelAssertions {
    fun isInInitialState(): Matcher<CameraContentViewModel> = allOf(
        hasNoCamera(),
        hasNoImageAnalysis(),
        hasNoImageCapture(),
        hasNoSurfaceRequest(),
        hasPreview(),
    )
}
