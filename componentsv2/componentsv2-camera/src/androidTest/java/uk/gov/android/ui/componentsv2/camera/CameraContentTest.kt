package uk.gov.android.ui.componentsv2.camera

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProviders.preview
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.barcodeAnalysis
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter
import uk.gov.android.ui.patterns.camera.CameraContentViewModel

class CameraContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
    )

    private val model = CameraContentViewModel()

    @Test
    fun configuresFullSurfaceAnalysis() = runTest {
        performJourney(
            converter = ImageProxyConverter.simple(),
        )
    }

    @Test
    fun configuresCroppedSurfaceAnalysis() = runTest {
        performJourney(
            converter = CentrallyCroppedImageProxyConverter(),
        )
    }

    private fun performJourney(
        converter: ImageProxyConverter,
    ) = runTest {
        val state = StateRestorationTester(composeTestRule)
        model.update(preview(model))
        model.update(
            barcodeAnalysis(
                context = ApplicationProvider.getApplicationContext(),
                converter = converter,
            ),
        )

        state.setContent {
            CameraContentWithViewModel(
                viewModel = model,
                modifier = Modifier.fillMaxSize(),
            )
        }

        state.emulateSavedInstanceStateRestore()
    }
}
