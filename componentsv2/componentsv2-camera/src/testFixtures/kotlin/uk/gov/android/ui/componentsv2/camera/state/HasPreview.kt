package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Preview
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import uk.gov.android.ui.componentsv2.camera.state.preview.ImagePreviewer

internal class HasPreview(
    private val matcher: Matcher<Preview>,
) : TypeSafeMatcher<ImagePreviewer.State>() {
    override fun matchesSafely(
        item: ImagePreviewer.State?,
    ): Boolean = matcher.matches(item?.preview?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: ImagePreviewer.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.preview?.value, mismatchDescription)
    }
}
