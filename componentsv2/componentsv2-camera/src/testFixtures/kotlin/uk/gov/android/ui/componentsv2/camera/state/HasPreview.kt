package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Preview
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

internal class HasPreview(
    private val matcher: Matcher<Preview>,
) : TypeSafeMatcher<CameraContentState.Previewer.State>() {
    override fun matchesSafely(
        item: CameraContentState.Previewer.State?,
    ): Boolean = matcher.matches(item?.preview?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentState.Previewer.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.preview?.value, mismatchDescription)
    }
}
