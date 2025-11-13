package uk.gov.android.ui.componentsv2.camera.state.capture

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasImageCapture
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoImageCapture
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateHelper

@RunWith(RobolectricTestRunner::class)
class MutableImageCapturerTest {

    private val state: ImageCapturer.Complete = MutableImageCapturer()
    private val helper = CameraContentStateHelper()

    @Test
    fun initialState() {
        assertThat(
            state,
            hasNoImageCapture(),
        )
    }

    @Test
    fun canUpdateImageCaptureUseCase() = runTest {
        backgroundScope.launch {
            state.imageCapture.collect {}
        }

        state.update(helper.imageCapture)

        assertThat(
            state,
            hasImageCapture(helper.imageCapture),
        )
    }
}
