package uk.gov.android.ui.componentsv2.camera.assertions

import androidx.camera.core.Preview
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import uk.gov.android.ui.patterns.camera.CameraContentViewModel

internal class HasPreview(
    private val matcher: Matcher<Preview>,
) : TypeSafeMatcher<CameraContentViewModel>() {
    override fun matchesSafely(
        item: CameraContentViewModel?,
    ): Boolean = matcher.matches(item?.previewUseCase?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentViewModel?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.previewUseCase?.value, mismatchDescription)
    }
}
