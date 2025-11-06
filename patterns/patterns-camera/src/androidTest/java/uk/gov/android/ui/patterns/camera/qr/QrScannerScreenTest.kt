package uk.gov.android.ui.patterns.camera.qr

import android.Manifest
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
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
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult
import uk.gov.android.ui.patterns.camera.R

@OptIn(ExperimentalPermissionsApi::class)
class QrScannerScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
    )

    private val model = CameraContentViewModel()
    private val resources = ApplicationProvider.getApplicationContext<Context>().resources
    private var barcodeScanResult: BarcodeScanResult = BarcodeScanResult.EmptyScan

    @Test
    fun configuresCameraUseCases() = runTest {
        val state = StateRestorationTester(composeTestRule)

        state.setContent {
            QrScannerScreen(
                barcodeAnalysisCallback = { result, _ ->
                    barcodeScanResult = result
                },
                converter = ImageProxyConverter.Companion.simple(),
                viewModel = model,
                modifier = Modifier.Companion.fillMaxSize(),
            )
        }

        composeTestRule
            .onNodeWithText(
                resources.getString(R.string.qr_scan_screen_title),
            ).assertExists()
            .assertIsDisplayed()
    }
}
