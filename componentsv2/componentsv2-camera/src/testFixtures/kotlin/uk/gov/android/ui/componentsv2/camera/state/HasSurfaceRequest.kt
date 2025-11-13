package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.SurfaceRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

internal class HasSurfaceRequest(
    private val matcher: Matcher<SurfaceRequest>,
) : TypeSafeMatcher<CameraContentState.SurfaceRequester.State>() {
    override fun matchesSafely(
        item: CameraContentState.SurfaceRequester.State?,
    ): Boolean = matcher.matches(item?.surfaceRequest?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentState.SurfaceRequester.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.surfaceRequest?.value, mismatchDescription)
    }
}
