package uk.gov.android.ui.patterns.camera.qr

import android.Manifest
import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.patterns.camera.R

@OptIn(ExperimentalPermissionsApi::class)
class QrScannerScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
    )

    private val resources = ApplicationProvider.getApplicationContext<Context>().resources

    @Test
    fun configuresCameraUseCases() = runTest {
        val state = StateRestorationTester(composeTestRule)

        state.setContent {
            val model = CameraContentViewModel()
            QrScannerViewModelScreen(viewModel = model)
        }

        composeTestRule
            .onNodeWithText(
                resources.getString(R.string.qr_scan_screen_title),
            ).assertExists()
            .assertIsDisplayed()
    }
}
