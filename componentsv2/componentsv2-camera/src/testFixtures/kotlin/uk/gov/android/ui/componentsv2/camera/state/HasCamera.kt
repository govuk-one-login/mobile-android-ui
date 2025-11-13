package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Camera
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import uk.gov.android.ui.componentsv2.camera.state.camera.CameraHolder

internal class HasCamera(
    private val matcher: Matcher<Camera>,
    private val getCamera: CameraHolder.State?.() -> Camera?,
) : TypeSafeMatcher<CameraHolder.State>() {

    override fun matchesSafely(
        item: CameraHolder.State?,
    ): Boolean = matcher.matches(getCamera(item))

    override fun describeTo(description: Description?) {
        matcher.describeTo(description)
    }

    override fun describeMismatchSafely(
        item: CameraHolder.State?,
        mismatchDescription: Description?,
    ) {
        matcher.describeMismatch(getCamera(item), mismatchDescription)
    }

    companion object {
        fun viaFlow(
            matcher: Matcher<Camera>,
        ) = HasCamera(
            matcher = matcher,
            getCamera = { this?.camera?.value },
        )

        fun viaFunction(
            matcher: Matcher<Camera>,
        ) = HasCamera(
            matcher = matcher,
            getCamera = { this?.getCurrentCamera() },
        )
    }
}
