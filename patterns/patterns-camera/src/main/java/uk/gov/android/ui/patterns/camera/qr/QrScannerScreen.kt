package uk.gov.android.ui.patterns.camera.qr

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.qrScannerOverlay
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.QrScannerOverlay
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.smallPadding

@Composable
fun QrScannerScreen(
    instructionText: String,
    surfaceRequest: SurfaceRequest?,
    previewUseCase: Preview,
    analysisUseCase: ImageAnalysis?,
    imageCaptureUseCase: ImageCapture?,
    scanningWidthMultiplier: Float,
    coroutineScope: CoroutineScope,
    onUpdateViewModelCamera: (Camera) -> Unit,
    modifier: Modifier = Modifier,
    colors: QrScannerOverlay = GdsLocalColorScheme.current.qrScannerOverlay,
    backgroundTint: Color = colors.background.toMappedColors(),
    borderColor: Color = colors.border.toMappedColors(),
) {
    Box(modifier = modifier) {
        QrOverlayText(
            instructionText = instructionText,
            textColor = borderColor,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
        )

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
                    overlayTint = backgroundTint,
                    qrBorderColor = borderColor,
                ),
            coroutineScope = coroutineScope,
            onViewModelUpdate = onUpdateViewModelCamera,
        )
    }
}

@Composable
fun QrOverlayText(
    instructionText: String,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        Text(
            text = instructionText,
            style = MaterialTheme.typography.headlineMedium,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(all = smallPadding),
        )
    }
}
