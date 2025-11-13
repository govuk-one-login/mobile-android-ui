package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.SurfaceRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import uk.gov.android.ui.componentsv2.camera.state.surfacerequest.SurfaceRequester

internal class HasSurfaceRequest(
    private val matcher: Matcher<SurfaceRequest>,
) : TypeSafeMatcher<SurfaceRequester.State>() {
    override fun matchesSafely(
        item: SurfaceRequester.State?,
    ): Boolean = matcher.matches(item?.surfaceRequest?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: SurfaceRequester.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.surfaceRequest?.value, mismatchDescription)
    }
}
