package uk.gov.android.ui.componentsv2.camera

import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoOutput
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.isInInitialState
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelHelper.monitor
import uk.gov.android.ui.componentsv2.camera.state.CameraContentState
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasCamera
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasImageAnalysis
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasImageCapture
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasPreview
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasSurfaceRequest
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateHelper
import uk.gov.android.ui.componentsv2.camera.state.CompleteCameraContentState

@RunWith(RobolectricTestRunner::class)
class CameraContentViewModelTest {

    private val state: CameraContentState.Complete = CompleteCameraContentState()
    private val model = CameraContentViewModel(state = state)
    private val helper = CameraContentStateHelper()

    @Test
    fun initialState() = runTest {
        monitor(model)

        assertThat(
            model,
            isInInitialState(),
        )
    }

    @Test
    fun invalidUseCasesCannotBeAdded() = runTest {
        monitor(model)
        val videoCapture: VideoCapture<VideoOutput> = mock()

        val exception = assertThrows(IllegalArgumentException::class.java) {
            model.addAll(videoCapture)
        }

        assertEquals(
            "Cannot update viewmodel state with an instance of VideoCapture",
            exception.message,
        )
    }

    @Test
    fun stateCanBeReset() = runTest {
        monitor(model)

        model.addAll(
            helper.preview,
            helper.imageAnalysis,
            helper.imageCapture,
        )
        model.update(helper.camera)
        model.onSurfaceRequested(helper.surfaceRequest)

        assertThat(
            model,
            allOf(
                hasPreview(),
                hasImageAnalysis(),
                hasImageCapture(),
                hasCamera(),
                hasSurfaceRequest(),
            ),
        )

        model.resetState()

        assertThat(
            model,
            isInInitialState(),
        )
    }
}
