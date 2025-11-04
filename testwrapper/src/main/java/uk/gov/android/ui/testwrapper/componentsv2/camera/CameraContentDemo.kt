package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProvider
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProvider.Companion.preview
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.barcodeAnalysis
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.provideQrScanningOptions
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.provideZoomOptions
import uk.gov.android.ui.componentsv2.permission.PermissionLogic
import uk.gov.android.ui.componentsv2.permission.PermissionScreen
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.CANVAS_WIDTH_MULTIPLIER
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.qrScannerOverlay
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraPermissionRationaleButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraRequirePermissionButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.PermanentCameraDenial
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.spacingDouble


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CameraContentDemo(
    modifier: Modifier = Modifier,
) {
    val colorScheme = GdsLocalColorScheme.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val viewModel = viewModel<CameraContentViewModel>()
    val (
        hasPreviouslyDeniedPermission,
        onUpdatePreviouslyDeniedPermission,
    ) = remember { mutableStateOf(false) }

    val permissionState = rememberPermissionState(Manifest.permission.CAMERA) {
        onUpdatePreviouslyDeniedPermission(!it)
    }

    viewModel.removeUseCases()

    listOf(
        preview(viewModel::update),
        barcodeAnalysis(
            context = context,
            options = provideQrScanningOptions(
                provideZoomOptions(viewModel::getCurrentCamera)
            ),
            relativeScanningWidth = CANVAS_WIDTH_MULTIPLIER,
            callback = BarcodeScanResultLoggingCallback,
        )
    ).map(CameraUseCaseProvider::provide)
        .let(viewModel::addAll)

    val permissionLogic = PermissionLogic(
        onGrantPermission = {
            CameraContent(
                coroutineScope = coroutineScope,
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("cameraViewfinder")
                    .qrScannerOverlay(
                        canvasWidthMultiplier = CANVAS_WIDTH_MULTIPLIER,
                        overlayTint = colorScheme.qrScannerOverlayBackground,
                        qrBorderColor = colorScheme.qrScannerOverlayBorder,
                    )
            )
        },
        onPermissionPermanentlyDenied = {
            PermanentCameraDenial(permissionState, context)
        },
        onShowRationale = { launchPermission ->
            CameraPermissionRationaleButton(launchPermission = launchPermission)
        },
        onRequirePermission = { launchPermission ->
            CameraRequirePermissionButton(launchPermission = launchPermission)
        }
    )

    Column(
        modifier = modifier
            .background(colorScheme.dialogBackground)
            .padding(spacingDouble)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PermissionScreen(
            permissionState = permissionState,
            hasPreviouslyDeniedPermission = hasPreviouslyDeniedPermission,
            logic = permissionLogic,
        )
    }
}
