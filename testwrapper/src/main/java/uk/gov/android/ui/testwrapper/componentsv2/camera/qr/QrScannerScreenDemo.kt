package uk.gov.android.ui.testwrapper.componentsv2.camera.qr

import android.content.Context
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
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
    val lifecycleOwner = LocalLifecycleOwner.current
    val logTag = "QrScannerScreenDemo"
    val onPermanentCameraDenial: @Composable (permissionState: PermissionState) -> Unit = { state ->
        PermanentCameraDenial(state, context)
    }
    val onShowRationale: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit,
    ) -> Unit = { _, launchPermission ->
        CameraPermissionRationaleButton(launchPermission = launchPermission)
    }
    val onRequirePermission: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit,
    ) -> Unit = { _, launchPermission ->
        CameraRequirePermissionButton(launchPermission = launchPermission)
    }

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

    LaunchedEffect(lifecycleOwner) {
        listOf(
            preview(viewModel),
            barcodeAnalysis(
                backpressureStrategy = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST,
                context = context,
                options = provideQrScanningOptions(
                    provideZoomOptions(viewModel::getCurrentCamera),
                ),
                converter = converter,
                callback = { result, toggleScanner ->
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
                        coroutineScope.cancel(
                            CancellationException(
                                "Navigating away from CameraContent",
                            ),
                        )
                        onNavigate(QrScannerResult(url))
                    }
                },
            ),
        ).map(CameraUseCaseProvider::provide)
            .let(viewModel::addAll)
    }

    QrScannerScreen(
        surfaceRequest = surfaceRequest,
        previewUseCase = previewUseCase,
        analysisUseCase = analysisUseCase,
        imageCaptureUseCase = imageCaptureUseCase,
        onUpdateViewModelCamera = viewModel::update,
        coroutineScope = coroutineScope,
        onPermanentCameraDenial = onPermanentCameraDenial,
        onShowRationale = onShowRationale,
        onRequirePermission = onRequirePermission,
        modifier = modifier,
    )
}
