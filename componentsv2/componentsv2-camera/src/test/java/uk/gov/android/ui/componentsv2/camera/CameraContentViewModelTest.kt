package uk.gov.android.ui.componentsv2.camera

import androidx.core.content.ContextCompat
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// DCMAW-16272: Update tests
@RunWith(RobolectricTestRunner::class)
class CameraContentViewModelTest {

    private val model = CameraContentViewModel(
        executor = ContextCompat.getMainExecutor(
            InstrumentationRegistry.getInstrumentation().targetContext,
        ),
    )
}
