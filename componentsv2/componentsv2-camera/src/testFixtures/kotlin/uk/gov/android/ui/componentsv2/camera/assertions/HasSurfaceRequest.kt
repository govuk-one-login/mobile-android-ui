package uk.gov.android.ui.componentsv2.camera.assertions

import androidx.camera.core.SurfaceRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import uk.gov.android.ui.patterns.camera.CameraContentViewModel

internal class HasSurfaceRequest(
    private val matcher: Matcher<SurfaceRequest>,
) : TypeSafeMatcher<CameraContentViewModel>() {
    override fun matchesSafely(
        item: CameraContentViewModel?,
    ): Boolean = matcher.matches(item?.surfaceRequest?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentViewModel?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.surfaceRequest?.value, mismatchDescription)
    }
}
