package uk.gov.android.ui.componentsv2.camera.state.preview

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateAssertions.hasPreview
import uk.gov.android.ui.componentsv2.camera.state.CameraContentStateHelper

@RunWith(RobolectricTestRunner::class)
class MutableImagePreviewerTest {

    private val state: ImagePreviewer.Complete = MutableImagePreviewer()
    private val helper = CameraContentStateHelper()

    @Test
    fun initialState() {
        assertThat(
            state,
            hasPreview(),
        )
    }

    @Test
    fun canUpdatePreviewUseCase() = runTest {
        backgroundScope.launch {
            state.preview.collect {}
        }
        assertNotEquals(
            helper.preview,
            state.preview.value,
        )

        state.update(helper.preview)

        assertThat(
            state,
            hasPreview(helper.preview),
        )
    }
}
