package uk.gov.android.ui.testwrapper.patterns.camera.qr

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter
import uk.gov.android.ui.componentsv2.permission.PermissionLogic
import uk.gov.android.ui.componentsv2.permission.PermissionScreen
import uk.gov.android.ui.patterns.camera.CameraContentViewModel
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions
import uk.gov.android.ui.patterns.camera.qr.QrScannerScreen
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons
import uk.gov.android.ui.theme.m3.CustomColorsScheme
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.spacingDouble

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun QrScannerScreenDemo(
    modifier: Modifier = Modifier,
    colorScheme: CustomColorsScheme = GdsLocalColorScheme.current,
    context: Context = LocalContext.current,
    converter: ImageProxyConverter =
        CentrallyCroppedImageProxyConverter(
            relativeScanningWidth = ModifierExtensions.CANVAS_WIDTH_MULTIPLIER,
            relativeScanningHeight = ModifierExtensions.CANVAS_WIDTH_MULTIPLIER,
        ),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: CameraContentViewModel = viewModel<CameraContentViewModel>(),
    onNavigate: (Any) -> Unit = {},
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    viewModel.resetState()
    qrScannerDemoAnalysis(
        context = context,
        getCurrentCamera = viewModel::getCurrentCamera,
        converter = converter,
        onNavigate = onNavigate,
    ).let(viewModel::update)

    val (
        hasPreviouslyDeniedPermission,
        onUpdatePreviouslyDeniedPermission,
    ) = remember { mutableStateOf(false) }

    val permissionState =
        rememberPermissionState(Manifest.permission.CAMERA) {
            onUpdatePreviouslyDeniedPermission(!it)
        }

    val permissionLogic = qrScannerDemoPermissionLogic(
        viewModel = viewModel,
        lifecycleOwner = lifecycleOwner,
        coroutineScope = coroutineScope,
        colorScheme = colorScheme,
        context = context,
    )

    Column(
        modifier =
        modifier
            .background(colorScheme.qrScannerOverlayBackground)
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
@OptIn(ExperimentalPermissionsApi::class)
private fun qrScannerDemoPermissionLogic(
    viewModel: CameraContentViewModel,
    lifecycleOwner: LifecycleOwner,
    coroutineScope: CoroutineScope,
    colorScheme: CustomColorsScheme,
    context: Context,
): PermissionLogic = PermissionLogic(
    onGrantPermission = {
        val surfaceRequest: SurfaceRequest? by
            viewModel.surfaceRequest.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
        val previewUseCase: Preview by viewModel.previewUseCase.collectAsStateWithLifecycle(
            lifecycleOwner = lifecycleOwner,
        )
        val analysisUseCase: ImageAnalysis? by viewModel.analysisUseCase.collectAsStateWithLifecycle(
            initialValue = null,
            lifecycleOwner = lifecycleOwner,
        )
        val imageCaptureUseCase: ImageCapture? by
            viewModel.imageCaptureUseCase.collectAsStateWithLifecycle(
                initialValue = null,
                lifecycleOwner = lifecycleOwner,
            )

        QrScannerScreen(
            modifier = Modifier,
            surfaceRequest = surfaceRequest,
            previewUseCase = previewUseCase,
            analysisUseCase = analysisUseCase,
            imageCaptureUseCase = imageCaptureUseCase,
            scanningWidthMultiplier = ModifierExtensions.CANVAS_WIDTH_MULTIPLIER,
            coroutineScope = coroutineScope,
            onUpdateViewModelCamera = viewModel::update,
            colors = colorScheme.qrScannerOverlay,
        )
    },
    onPermissionPermanentlyDenied = { state ->
        CameraContentDemoButtons.PermanentCameraDenial(state, context)
    },
    onShowRationale = { _, launchPermission ->
        CameraContentDemoButtons.CameraPermissionRationaleButton(launchPermission = launchPermission)
    },
    onRequirePermission = { _, launchPermission ->
        CameraContentDemoButtons.CameraRequirePermissionButton(launchPermission = launchPermission)
    },
)

private fun qrScannerDemoCallback(
    onNavigate: (Any) -> Unit = {},
): BarcodeScanResult.Callback = BarcodeScanResult.Callback { result, toggleScanner ->
    val logTag = "QrScannerScreenDemo"
    if (Log.isLoggable(logTag, Log.INFO)) {
        Log.i(logTag, "Obtained BarcodeScanResult: $result")
    }
    when (result) {
        is BarcodeScanResult.Success -> result.firstOrNull()?.url?.url
        is BarcodeScanResult.Single -> result.barcode.url?.url
        else -> {
            toggleScanner()
            null
        }
    }?.let { url ->
        onNavigate(QrScannerResult(url))
    }
}

private fun qrScannerDemoAnalysis(
    context: Context,
    getCurrentCamera: () -> Camera?,
    onNavigate: (Any) -> Unit,
    converter: ImageProxyConverter,
) = BarcodeUseCaseProviders.barcodeAnalysis(
    context = context,
    options =
    BarcodeUseCaseProviders.provideQrScanningOptions(
        BarcodeUseCaseProviders.provideZoomOptions(getCurrentCamera),
    ),
    callback = qrScannerDemoCallback(onNavigate),
    converter = converter,
)
