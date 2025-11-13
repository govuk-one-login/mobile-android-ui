package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.ImageCapture
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

internal class HasImageCapture(
    private val matcher: Matcher<ImageCapture>,
) : TypeSafeMatcher<CameraContentState.ImageCapturer.State>() {
    override fun matchesSafely(
        item: CameraContentState.ImageCapturer.State?,
    ): Boolean = matcher.matches(item?.imageCapture?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentState.ImageCapturer.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.imageCapture?.value, mismatchDescription)
    }
}
