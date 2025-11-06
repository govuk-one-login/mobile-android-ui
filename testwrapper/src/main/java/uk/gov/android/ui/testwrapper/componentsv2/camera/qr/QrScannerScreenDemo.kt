package uk.gov.android.ui.testwrapper.componentsv2.camera.qr

import android.content.Context
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.CANVAS_WIDTH_MULTIPLIER
import uk.gov.android.ui.patterns.camera.qr.QrScannerScreen
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraPermissionRationaleButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraRequirePermissionButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.PermanentCameraDenial

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun QrScannerScreenDemo(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    converter: ImageProxyConverter = CentrallyCroppedImageProxyConverter(
        relativeScanningWidth = CANVAS_WIDTH_MULTIPLIER,
        relativeScanningHeight = CANVAS_WIDTH_MULTIPLIER,
    ),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: CameraContentViewModel = viewModel<CameraContentViewModel>(),
    onNavigate: (Any) -> Unit = {},
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
        barcodeAnalysisCallback = { result, toggleScanner ->
            Log.i(
                "QrScannerScreenDemo",
                "Obtained BarcodeScanResult: $result",
            )
            when (result) {
                is BarcodeScanResult.Success -> result.firstOrNull()?.url?.url
                is BarcodeScanResult.Single -> result.barcode.url?.url
                else -> {
                    toggleScanner()
                    null
                }
            }?.let { url ->
                coroutineScope.cancel(
                    CancellationException(
                        "Navigating away from CameraContent",
                    )
                )
                onNavigate(QrScannerResult(url))
            }
        },
        coroutineScope = coroutineScope,
        converter = converter,
        viewModel = viewModel,
        onPermanentCameraDenial = onPermanentCameraDenial,
        onShowRationale = onShowRationale,
        onRequirePermission = onRequirePermission,
        modifier = modifier,
    )
}
