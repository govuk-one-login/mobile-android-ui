package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.Matcher
import uk.gov.android.ui.componentsv2.camera.assertions.HasCamera
import uk.gov.android.ui.componentsv2.camera.assertions.HasImageAnalysis
import uk.gov.android.ui.componentsv2.camera.assertions.HasImageCapture
import uk.gov.android.ui.componentsv2.camera.assertions.HasPreview
import uk.gov.android.ui.componentsv2.camera.assertions.HasSurfaceRequest
import uk.gov.android.ui.patterns.camera.CameraContentViewModel

/**
 * Convenience object for holding assertions related to the [CameraContentViewModel].
 *
 * Suppresses `TooManyFunctions` as the object follows a similar architectural pattern to
 * [org.junit.Assert] and [org.hamcrest.CoreMatchers].
 */
@Suppress("TooManyFunctions")
object CameraContentViewModelAssertions {
    fun hasCamera() = hasCamera(any(Camera::class.java))
    fun hasCamera(
        expected: Camera,
    ) = hasCamera(equalTo(expected))
    fun hasCamera(
        matcher: Matcher<Camera>,
    ): Matcher<CameraContentViewModel> = HasCamera(matcher)
    fun hasNoCamera() = hasCamera(nullValue(Camera::class.java))

    fun hasPreview() = hasPreview(any(Preview::class.java))
    fun hasPreview(
        expected: Preview,
    ) = hasPreview(equalTo(expected))

    fun hasPreview(
        matcher: Matcher<Preview>,
    ): Matcher<CameraContentViewModel> = HasPreview(matcher)

    fun hasImageAnalysis() = hasImageAnalysis(any(ImageAnalysis::class.java))
    fun hasImageAnalysis(
        expected: ImageAnalysis,
    ) = hasImageAnalysis(equalTo(expected))
    fun hasImageAnalysis(
        matcher: Matcher<ImageAnalysis>,
    ): Matcher<CameraContentViewModel> = HasImageAnalysis(matcher)
    fun hasNoImageAnalysis(): Matcher<CameraContentViewModel> = hasImageAnalysis(
        nullValue(ImageAnalysis::class.java),
    )

    fun hasImageCapture() = hasImageCapture(any(ImageCapture::class.java))
    fun hasImageCapture(
        expected: ImageCapture,
    ) = hasImageCapture(equalTo(expected))
    fun hasImageCapture(
        matcher: Matcher<ImageCapture>,
    ): Matcher<CameraContentViewModel> = HasImageCapture(matcher)
    fun hasNoImageCapture(): Matcher<CameraContentViewModel> = hasImageCapture(
        nullValue(ImageCapture::class.java),
    )

    fun hasSurfaceRequest() = hasSurfaceRequest(any(SurfaceRequest::class.java))
    fun hasSurfaceRequest(
        expected: SurfaceRequest,
    ) = hasSurfaceRequest(equalTo(expected))
    fun hasSurfaceRequest(
        matcher: Matcher<SurfaceRequest>,
    ): Matcher<CameraContentViewModel> = HasSurfaceRequest(matcher)
    fun hasNoSurfaceRequest(): Matcher<CameraContentViewModel> = hasSurfaceRequest(
        nullValue(SurfaceRequest::class.java),
    )

    fun isInInitialState(): Matcher<CameraContentViewModel> = allOf(
        hasNoCamera(),
        hasNoImageAnalysis(),
        hasNoImageCapture(),
        hasNoSurfaceRequest(),
        hasPreview(),
    )
}
