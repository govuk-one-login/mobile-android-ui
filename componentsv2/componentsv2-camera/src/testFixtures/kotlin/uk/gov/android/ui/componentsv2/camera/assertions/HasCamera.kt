package uk.gov.android.ui.componentsv2.camera.assertions

import androidx.camera.core.Camera
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import uk.gov.android.ui.patterns.camera.CameraContentViewModel

internal class HasCamera(
    private val matcher: Matcher<Camera>,
) : TypeSafeMatcher<CameraContentViewModel>() {
    override fun matchesSafely(
        item: CameraContentViewModel?,
    ): Boolean = matcher.matches(item?.getCurrentCamera())

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentViewModel?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.getCurrentCamera(), mismatchDescription)
    }
}
