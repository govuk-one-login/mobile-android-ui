package uk.gov.android.ui.componentsv2.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCase
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoOutput
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.hasCamera
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.hasImageAnalysis
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.hasImageCapture
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.hasPreview
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.hasSurfaceRequest
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelAssertions.isInInitialState
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModelHelper.monitor
import uk.gov.android.ui.patterns.camera.CameraContentViewModel

@RunWith(RobolectricTestRunner::class)
class CameraContentViewModelTest {

    private val model = CameraContentViewModel()
    private val preview = Preview.Builder().build()
    private val imageAnalysis = ImageAnalysis.Builder().build()
    private val imageCapture = ImageCapture.Builder().build()
    private val camera = CameraContentViewModelHelper.DummyCamera
    private val surfaceRequest: SurfaceRequest = mock()

    @Test
    fun initialState() = runTest {
        monitor(model)

        assertThat(
            model,
            isInInitialState(),
        )
    }

    @Test
    fun canUpdatePreviewUseCase() = runTest {
        monitor(model)

        assertNotEquals(
            preview,
            model.previewUseCase.value,
        )

        verifyUseCaseFlow(
            preview,
            hasPreview(preview),
        )
    }

    @Test
    fun canUpdateImageAnalysisUseCase() = runTest {
        monitor(model)

        verifyUseCaseFlow(
            imageAnalysis,
            hasImageAnalysis(imageAnalysis),
        )
    }

    @Test
    fun canUpdateImageCaptureUseCase() = runTest {
        monitor(model)

        verifyUseCaseFlow(
            imageCapture,
            hasImageCapture(imageCapture),
        )
    }

    @Test
    fun canUpdateCurrentCamera() = runTest {
        monitor(model)

        model.update(camera)

        assertThat(
            model,
            hasCamera(camera),
        )
    }

    @Test
    fun canUpdateSurfaceRequest() = runTest {
        monitor(model)

        model.onSurfaceRequested(surfaceRequest)

        assertThat(
            model,
            hasSurfaceRequest(surfaceRequest),
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
            preview,
            imageAnalysis,
            imageCapture,
        )
        model.update(camera)
        model.onSurfaceRequested(surfaceRequest)

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

    private fun verifyUseCaseFlow(
        useCase: UseCase,
        assertion: Matcher<CameraContentViewModel>,
    ) {
        model.addAll(useCase)

        assertThat(
            model,
            assertion,
        )
    }
}
