package uk.gov.android.ui.componentsv2.camera.state.analyzer

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasImageAnalysis
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasNoImageAnalysis
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateHelper

@RunWith(RobolectricTestRunner::class)
class MutableImageAnalyzerTest {

    private val state: ImageAnalyzer.Complete = MutableImageAnalyzer()
    private val helper = CameraContentStateHelper()

    @Test
    fun initialState() {
        assertThat(
            state,
            hasNoImageAnalysis(),
        )
    }

    @Test
    fun canUpdateImageAnalysisUseCase() = runTest {
        backgroundScope.launch {
            state.imageAnalysis.collect {}
        }

        state.update(helper.imageAnalysis)

        assertThat(
            state,
            hasImageAnalysis(helper.imageAnalysis),
        )
    }
}
