package uk.gov.android.ui.testwrapper.componentsv2.camera.qr

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCase
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProvider
import uk.gov.android.ui.componentsv2.camera.CameraUseCaseProvider.Companion.preview
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.cameraContentViewModelFactory
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.barcodeAnalysis
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.provideQrScanningOptions
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeUseCaseProviders.provideZoomOptions
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter
import uk.gov.android.ui.componentsv2.permission.PermissionLogic
import uk.gov.android.ui.componentsv2.permission.PermissionScreen
import uk.gov.android.ui.patterns.camera.R
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.CANVAS_WIDTH_MULTIPLIER
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.qrScannerOverlay
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraPermissionRationaleButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.CameraRequirePermissionButton
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemoButtons.PermanentCameraDenial
import uk.gov.android.ui.theme.m3.CustomColorsScheme
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun QrScannerScreenDemo(
    modifier: Modifier = Modifier,
    colorScheme: CustomColorsScheme = GdsLocalColorScheme.current,
    context: Context = LocalContext.current,
    converter: ImageProxyConverter =
        CentrallyCroppedImageProxyConverter(
            relativeScanningWidth = CANVAS_WIDTH_MULTIPLIER,
            relativeScanningHeight = CANVAS_WIDTH_MULTIPLIER,
        ),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: CameraContentViewModel = cameraContentViewModelFactory(context).create(
        CameraContentViewModel::class.java,
    ),
    onNavigate: (Any) -> Unit = {},
) {
    val (
        hasPreviouslyDeniedPermission,
        onUpdatePreviouslyDeniedPermission,
    ) = remember { mutableStateOf(false) }

    val permissionState =
        rememberPermissionState(Manifest.permission.CAMERA) {
            onUpdatePreviouslyDeniedPermission(!it)
        }

    viewModel.removeUseCases()

    generateCameraUseCases(viewModel, context, converter, onNavigate).let(viewModel::addAll)

    val permissionLogic = generatePermissionLogic(
        coroutineScope = coroutineScope,
        colorScheme = colorScheme,
        viewModel = viewModel,
        context = context,
        instructionText = stringResource(R.string.qr_scan_screen_title),
        scanningWidthMultiplier = CANVAS_WIDTH_MULTIPLIER,
        onUpdateViewModelCamera = viewModel::update,
    )

    Column(
        modifier =
        modifier
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

@OptIn(ExperimentalPermissionsApi::class)
private fun generatePermissionLogic(
    coroutineScope: CoroutineScope,
    colorScheme: CustomColorsScheme,
    viewModel: CameraContentViewModel,
    context: Context,
    instructionText: String,
    onUpdateViewModelCamera: (Camera) -> Unit,
    modifier: Modifier = Modifier,
    scanningWidthMultiplier: Float = CANVAS_WIDTH_MULTIPLIER,
): PermissionLogic = PermissionLogic(
    onGrantPermission = {
        val surfaceRequest: SurfaceRequest? by
            viewModel.surfaceRequestFlow.collectAsStateWithLifecycle()
        val previewUseCase: Preview by viewModel.previewUseCase.collectAsStateWithLifecycle()
        val analysisUseCase: ImageAnalysis? by viewModel.analysisUseCase.collectAsStateWithLifecycle(
            initialValue = null,
        )
        val imageCaptureUseCase: ImageCapture? by
            viewModel.imageCaptureUseCase.collectAsStateWithLifecycle(
                initialValue = null,
            )

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
    },
    onPermissionPermanentlyDenied = { state ->
        PermanentCameraDenial(state, context)
    },
    onShowRationale = { _, launchPermission ->
        CameraPermissionRationaleButton(launchPermission = launchPermission)
    },
    onRequirePermission = { _, launchPermission ->
        CameraRequirePermissionButton(launchPermission = launchPermission)
    },
)

private fun generateCameraUseCases(
    viewModel: CameraContentViewModel,
    context: Context,
    converter: ImageProxyConverter,
    onNavigate: (Any) -> Unit = {},
): List<UseCase> = listOf(
    preview(viewModel::update),
    barcodeAnalysis(
        context = context,
        options =
        provideQrScanningOptions(
            provideZoomOptions(viewModel::getCurrentCamera),
        ),
        callback = { result, toggleScanner ->
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
        },
        converter = converter,
    ),
).map(CameraUseCaseProvider::provide)
