package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.Matcher

/**
 * Convenience object for holding assertions related to the [CameraContentState].
 *
 * Suppresses `TooManyFunctions` as the object follows a similar architectural pattern to
 * [org.junit.Assert] and [org.hamcrest.CoreMatchers].
 */
@Suppress("TooManyFunctions")
object CameraContentStateAssertions {
    fun hasCamera() = hasCamera(any(Camera::class.java))
    fun hasCamera(
        expected: Camera,
    ) = hasCamera(equalTo(expected))
    fun hasCamera(
        matcher: Matcher<Camera>,
    ): Matcher<in CameraContentState.CameraHolder.State> = HasCamera.viaFunction(matcher)
    fun hasNoCamera() = hasCamera(nullValue(Camera::class.java))

    fun hasCurrentCamera() = hasCurrentCamera(any(Camera::class.java))
    fun hasCurrentCamera(
        expected: Camera,
    ) = hasCurrentCamera(equalTo(expected))
    fun hasCurrentCamera(
        matcher: Matcher<Camera>,
    ): Matcher<in CameraContentState.CameraHolder.State> = HasCamera.viaFlow(matcher)
    fun hasNullCurrentCamera() = hasCurrentCamera(nullValue(Camera::class.java))

    fun hasPreview() = hasPreview(any(Preview::class.java))
    fun hasPreview(
        expected: Preview,
    ) = hasPreview(equalTo(expected))

    fun hasPreview(
        matcher: Matcher<Preview>,
    ): Matcher<in CameraContentState.Previewer.State> = HasPreview(matcher)

    fun hasImageAnalysis() = hasImageAnalysis(any(ImageAnalysis::class.java))
    fun hasImageAnalysis(
        expected: ImageAnalysis,
    ) = hasImageAnalysis(equalTo(expected))
    fun hasImageAnalysis(
        matcher: Matcher<ImageAnalysis>,
    ): Matcher<in CameraContentState.ImageAnalyzer.State> = HasImageAnalysis(matcher)
    fun hasNoImageAnalysis() = hasImageAnalysis(
        nullValue(ImageAnalysis::class.java),
    )

    fun hasImageCapture() = hasImageCapture(any(ImageCapture::class.java))
    fun hasImageCapture(
        expected: ImageCapture,
    ) = hasImageCapture(equalTo(expected))
    fun hasImageCapture(
        matcher: Matcher<ImageCapture>,
    ): Matcher<in CameraContentState.ImageCapturer.State> = HasImageCapture(matcher)
    fun hasNoImageCapture() = hasImageCapture(
        nullValue(ImageCapture::class.java),
    )

    fun hasSurfaceRequest() = hasSurfaceRequest(any(SurfaceRequest::class.java))
    fun hasSurfaceRequest(
        expected: SurfaceRequest,
    ) = hasSurfaceRequest(equalTo(expected))
    fun hasSurfaceRequest(
        matcher: Matcher<SurfaceRequest>,
    ): Matcher<in CameraContentState.SurfaceRequester.State> = HasSurfaceRequest(matcher)
    fun hasNoSurfaceRequest() = hasSurfaceRequest(
        nullValue(SurfaceRequest::class.java),
    )
}
