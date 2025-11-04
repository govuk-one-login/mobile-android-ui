package uk.gov.android.ui.testwrapper.componentsv2.camera.qr

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.patterns.camera.qr.QrScannerScreen
import uk.gov.android.ui.testwrapper.componentsv2.camera.BarcodeScanResultLoggingCallback
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraPermissionRationaleButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraRequirePermissionButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.PermanentCameraDenial


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun QrScannerScreenDemo(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: CameraContentViewModel = viewModel<CameraContentViewModel>(),
) {
    val onPermanentCameraDenial: @Composable (permissionState: PermissionState) -> Unit = { state ->
        PermanentCameraDenial(state, context)
    }
    val onShowRationale: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit
    ) -> Unit = { _, launchPermission ->
        CameraPermissionRationaleButton(launchPermission = launchPermission)
    }
    val onRequirePermission: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit
    ) -> Unit = { _, launchPermission ->
        CameraRequirePermissionButton(launchPermission = launchPermission)
    }

    QrScannerScreen(
        barcodeAnalysisCallback = BarcodeScanResultLoggingCallback,
        viewModel = viewModel,
        onPermanentCameraDenial = onPermanentCameraDenial,
        onShowRationale = onShowRationale,
        onRequirePermission = onRequirePermission,
        modifier = modifier,
    )
}
