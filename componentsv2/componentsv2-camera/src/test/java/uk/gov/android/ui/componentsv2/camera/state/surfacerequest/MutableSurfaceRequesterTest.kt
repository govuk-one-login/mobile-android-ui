package uk.gov.android.ui.componentsv2.camera.state.surfacerequest

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoSurfaceRequest
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasSurfaceRequest
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateHelper

@RunWith(RobolectricTestRunner::class)
class MutableSurfaceRequesterTest {

    private val state: SurfaceRequester.Complete = MutableSurfaceRequester()
    private val helper = CameraContentStateHelper()

    @Test
    fun initialState() {
        assertThat(
            state,
            hasNoSurfaceRequest(),
        )
    }

    @Test
    fun canUpdateSurfaceRequest() = runTest {
        backgroundScope.launch {
            state.surfaceRequest.collect {}
        }

        state.onSurfaceRequested(helper.surfaceRequest)

        assertThat(
            state,
            hasSurfaceRequest(helper.surfaceRequest),
        )
    }
}
