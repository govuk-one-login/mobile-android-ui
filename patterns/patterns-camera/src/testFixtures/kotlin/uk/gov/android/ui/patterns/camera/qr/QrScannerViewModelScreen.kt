package uk.gov.android.ui.patterns.camera.qr

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.patterns.camera.R
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.CANVAS_WIDTH_MULTIPLIER
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.QrScannerOverlay
import uk.gov.android.ui.theme.m3.toMappedColors

@Composable
fun QrScannerViewModelScreen(
    viewModel: CameraContentViewModel,
    modifier: Modifier = Modifier,
    colors: QrScannerOverlay = GdsLocalColorScheme.current.qrScannerOverlay,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scanningWidthMultiplier: Float = CANVAS_WIDTH_MULTIPLIER,
    instructionContent: @Composable () -> Unit = {
        QrOverlayText(
            instructionText = stringResource(R.string.qr_scan_screen_title),
            textColor = colors.border.toMappedColors(),
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
        )
    },
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val surfaceRequest: SurfaceRequest? by
        viewModel.surfaceRequest.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val previewUseCase: Preview by viewModel.preview.collectAsStateWithLifecycle(
        lifecycleOwner = lifecycleOwner,
    )
    val analysisUseCase: ImageAnalysis? by viewModel.imageAnalysis.collectAsStateWithLifecycle(
        initialValue = null,
        lifecycleOwner = lifecycleOwner,
    )
    val imageCaptureUseCase: ImageCapture? by
        viewModel.imageCapture.collectAsStateWithLifecycle(
            initialValue = null,
            lifecycleOwner = lifecycleOwner,
        )

    QrScannerScreen(
        surfaceRequest = surfaceRequest,
        previewUseCase = previewUseCase,
        analysisUseCase = analysisUseCase,
        imageCaptureUseCase = imageCaptureUseCase,
        scanningWidthMultiplier = scanningWidthMultiplier,
        coroutineScope = coroutineScope,
        onUpdateViewModelCamera = viewModel::update,
        modifier = modifier,
        colors = colors,
        instructionContent = instructionContent,
    )
}
