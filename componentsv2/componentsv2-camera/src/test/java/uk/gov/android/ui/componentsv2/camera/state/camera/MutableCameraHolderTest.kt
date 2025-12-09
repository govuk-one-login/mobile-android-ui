package uk.gov.android.ui.componentsv2.camera.state.camera

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasCamera
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasCurrentCamera
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoCamera
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateHelper

@RunWith(RobolectricTestRunner::class)
class MutableCameraHolderTest {

    private val state: CameraHolder.Complete = MutableCameraHolder()
    private val helper = CameraContentStateHelper()

    @Test
    fun initialState() {
        assertThat(
            state,
            hasNoCamera(),
        )
    }

    @Test
    fun canUpdateCurrentCamera() = runTest {
        backgroundScope.launch {
            state.camera.collect {}
        }

        state.update(helper.camera)

        assertThat(
            state,
            hasCamera(helper.camera),
        )
    }

    @Test
    fun cameraVerificationOccursViaPublicStateFlowAccessor() = runTest {
        backgroundScope.launch {
            state.camera.collect {}
        }

        state.update(helper.camera)

        assertThat(
            state,
            hasCurrentCamera(helper.camera),
        )
    }
}
