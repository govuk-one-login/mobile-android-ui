package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.ImageAnalysis
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

internal class HasImageAnalysis(
    private val matcher: Matcher<ImageAnalysis>,
) : TypeSafeMatcher<CameraContentState.ImageAnalyzer.State>() {
    override fun matchesSafely(
        item: CameraContentState.ImageAnalyzer.State?,
    ): Boolean = matcher.matches(item?.imageAnalysis?.value)

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraContentState.ImageAnalyzer.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(item?.imageAnalysis?.value, mismatchDescription)
    }
}
