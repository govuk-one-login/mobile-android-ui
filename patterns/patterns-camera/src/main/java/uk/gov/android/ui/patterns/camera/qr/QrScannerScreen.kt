package uk.gov.android.ui.patterns.camera.qr

import android.Manifest
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.componentsv2.permission.PermissionLogic
import uk.gov.android.ui.componentsv2.permission.PermissionScreen
import uk.gov.android.ui.patterns.camera.R
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.CANVAS_WIDTH_MULTIPLIER
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.qrScannerOverlay
import uk.gov.android.ui.theme.m3.CustomColorsScheme
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun QrScannerScreen(
    surfaceRequest: SurfaceRequest?,
    previewUseCase: Preview,
    onUpdateViewModelCamera: (Camera) -> Unit,
    modifier: Modifier = Modifier,
    analysisUseCase: ImageAnalysis? = null,
    imageCaptureUseCase: ImageCapture? = null,
    scanningWidthMultiplier: Float = CANVAS_WIDTH_MULTIPLIER,
    instructionText: String = stringResource(R.string.qr_scan_screen_title),
    onPermanentCameraDenial: @Composable ((PermissionState) -> Unit) = { _ -> },
    onShowRationale: @Composable ((PermissionState, () -> Unit) -> Unit) = { _, _ -> },
    onRequirePermission: @Composable ((PermissionState, () -> Unit) -> Unit) = { _, _ -> },
    colorScheme: CustomColorsScheme = GdsLocalColorScheme.current,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val (
        hasPreviouslyDeniedPermission,
        onUpdatePreviouslyDeniedPermission,
    ) = remember { mutableStateOf(false) }

    val permissionState = rememberPermissionState(Manifest.permission.CAMERA) {
        onUpdatePreviouslyDeniedPermission(!it)
    }

    val permissionLogic = PermissionLogic(
        onGrantPermission = {
            QrScannerPermissionGranted(
                instructionText = instructionText,
                previewUseCase = previewUseCase,
                analysisUseCase = analysisUseCase,
                imageCaptureUseCase = imageCaptureUseCase,
                colorScheme = colorScheme,
                coroutineScope = coroutineScope,
                scanningWidthMultiplier = scanningWidthMultiplier,
                surfaceRequest = surfaceRequest,
                onUpdateViewModelCamera = onUpdateViewModelCamera,
            )
        },
        onPermissionPermanentlyDenied = onPermanentCameraDenial,
        onShowRationale = onShowRationale,
        onRequirePermission = onRequirePermission,
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

@Composable
private fun QrScannerPermissionGranted(
    instructionText: String,
    colorScheme: CustomColorsScheme,
    previewUseCase: Preview,
    coroutineScope: CoroutineScope,
    scanningWidthMultiplier: Float,
    surfaceRequest: SurfaceRequest?,
    onUpdateViewModelCamera: (Camera) -> Unit,
    modifier: Modifier = Modifier,
    imageCaptureUseCase: ImageCapture? = null,
    analysisUseCase: ImageAnalysis? = null,
) {
    Box(modifier = modifier) {
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .zIndex(2f),
            contentAlignment = Alignment.TopCenter,
        ) {
            Text(
                text = instructionText,
                style = MaterialTheme.typography.headlineMedium,
                color = colorScheme.qrScannerOverlayBorder,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(all = smallPadding),
            )
        }

        CameraContent(
            surfaceRequest = surfaceRequest,
            previewUseCase = previewUseCase,
            analysisUseCase = analysisUseCase,
            imageCaptureUseCase = imageCaptureUseCase,
            modifier = Modifier
                .fillMaxSize()
                .testTag("cameraViewfinder")
                .qrScannerOverlay(
                    canvasWidthMultiplier = scanningWidthMultiplier,
                    overlayTint = colorScheme.qrScannerOverlayBackground,
                    qrBorderColor = colorScheme.qrScannerOverlayBorder,
                ),
            coroutineScope = coroutineScope,
            onViewModelUpdate = onUpdateViewModelCamera,
        )
    }
}
