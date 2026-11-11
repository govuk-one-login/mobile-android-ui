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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.patterns.camera.R
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.Borders
import uk.gov.android.ui.theme.m3.Text
import uk.gov.android.ui.theme.m3.toMappedColors

@Composable
fun QrScannerViewModelScreen(
    viewModel: CameraContentViewModel,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    backgroundTint: Color = Backgrounds.qrScanner.toMappedColors(),
    borderColor: Color = Borders.qrScanner.toMappedColors(),
    textColor: Color = Text.qrScanner.toMappedColors(),
    backgroundTextColor: Color = Backgrounds.qrScannerPrompt.toMappedColors(),
    instructionContent: @Composable () -> Unit = {
        QrOverlayText(
            instructionText = stringResource(R.string.qr_scan_screen_title),
            instructionTextContentDesc = stringResource(R.string.qr_scan_screen_title_content_desc),
            textColor = textColor,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
            textBackground = backgroundTextColor,
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
        modifier = modifier,
        surfaceRequest = surfaceRequest,
        previewUseCase = previewUseCase,
        analysisUseCase = analysisUseCase,
        imageCaptureUseCase = imageCaptureUseCase,
        coroutineScope = coroutineScope,
        onUpdateViewModelCamera = viewModel::update,
        backgroundTint = backgroundTint,
        borderColor = borderColor,
        backgroundTextColor = backgroundTextColor,
        instructionContent = instructionContent,
    )
}
