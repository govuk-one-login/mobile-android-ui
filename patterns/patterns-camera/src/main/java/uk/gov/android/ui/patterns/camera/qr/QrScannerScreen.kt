package uk.gov.android.ui.patterns.camera.qr

import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CoroutineScope
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.componentsv2.camera.state.camera.CameraHolder
import uk.gov.android.ui.componentsv2.permission.PermissionLogic
import uk.gov.android.ui.patterns.camera.R
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.qrScannerOverlay
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.Borders
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.QrScannerOverlayDefaults
import uk.gov.android.ui.theme.m3.Text
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding

/**
 * UI for capturing a QR code from the User's camera.
 *
 * Usually called within a [PermissionLogic.onGrantPermission] block that checks for
 * [android.Manifest.permission.CAMERA].
 *
 * Be mindful of recomposition issues - nesting this composable may create infinite
 * recompositions.
 *
 * @param instructionContent The composable that's drawn alongside the QR overlay. Defaults to
 * [QrOverlayText].
 * @param previewUseCase The [Preview] camera use case that renders camera output to the
 * Android-powered device. This usually comes from an instance of [CameraContentViewModel].
 * @param analysisUseCase The [ImageAnalysis] camera use case that handles camera output.
 * This usually comes from an instance of [CameraContentViewModel]. Is nullable, due to the nature
 * of creating [ImageAnalysis] instances requiring [Camera] objects for zoom preferences, which are
 * only available after binding use cases to a [androidx.camera.lifecycle.ProcessCameraProvider].
 * Defaults to null, meaning that no analysis occurs.
 * @param imageCaptureUseCase The [ImageCapture] camera use case that stores camera output. This
 * usually comes from an instance of [CameraContentViewModel]. Defaults to null, meaning that no
 * image capture occurs.
 * @param onUpdateViewModelCamera The function to call when there's a new instance of [Camera].
 */
@Deprecated(
    message = "To be replaced by variant without the colors parameter - will aim to be removed on 19th of May",
    level = DeprecationLevel.WARNING,
)
@Composable
fun QrScannerScreen(
    surfaceRequest: SurfaceRequest?,
    previewUseCase: Preview,
    scanningWidthMultiplier: Float,
    coroutineScope: CoroutineScope,
    onUpdateViewModelCamera: CameraHolder.Updater,
    modifier: Modifier = Modifier,
    analysisUseCase: ImageAnalysis? = null,
    imageCaptureUseCase: ImageCapture? = null,
    colors: QrScannerOverlayDefaults = GdsLocalColorScheme.current.qrScannerOverlay,
    backgroundTint: Color = colors.background.toMappedColors(),
    borderColor: Color = colors.border.toMappedColors(),
    instructionContent: @Composable () -> Unit = {
        QrOverlayText(
            instructionText = stringResource(R.string.qr_scan_screen_title),
            instructionTextContentDesc = stringResource(R.string.qr_scan_screen_title_content_desc),
            textColor = borderColor,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
        )
    },
) {
    Box(modifier = modifier) {
        instructionContent()

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
            cameraUpdater = onUpdateViewModelCamera,
        )
    }
}

/**
 * UI for capturing a QR code from the User's camera.
 *
 * Usually called within a [PermissionLogic.onGrantPermission] block that checks for
 * [android.Manifest.permission.CAMERA].
 *
 * Be mindful of recomposition issues - nesting this composable may create infinite
 * recompositions.
 *
 * @param instructionContent The composable that's drawn alongside the QR overlay. Defaults to
 * [QrOverlayText].
 * @param previewUseCase The [Preview] camera use case that renders camera output to the
 * Android-powered device. This usually comes from an instance of [CameraContentViewModel].
 * @param analysisUseCase The [ImageAnalysis] camera use case that handles camera output.
 * This usually comes from an instance of [CameraContentViewModel]. Is nullable, due to the nature
 * of creating [ImageAnalysis] instances requiring [Camera] objects for zoom preferences, which are
 * only available after binding use cases to a [androidx.camera.lifecycle.ProcessCameraProvider].
 * Defaults to null, meaning that no analysis occurs.
 * @param imageCaptureUseCase The [ImageCapture] camera use case that stores camera output. This
 * usually comes from an instance of [CameraContentViewModel]. Defaults to null, meaning that no
 * image capture occurs.
 * @param onUpdateViewModelCamera The function to call when there's a new instance of [Camera].
 */
@JvmName("QrScannerV2")
@Composable
fun QrScannerScreen(
    surfaceRequest: SurfaceRequest?,
    previewUseCase: Preview,
    coroutineScope: CoroutineScope,
    onUpdateViewModelCamera: CameraHolder.Updater,
    modifier: Modifier = Modifier,
    instructionsText: String = stringResource(R.string.position_qr_in_frame_below),
    instructionTextContentDesc: String = stringResource(
        R.string.position_qr_in_frame_below_content_desc,
    ),
    analysisUseCase: ImageAnalysis? = null,
    imageCaptureUseCase: ImageCapture? = null,
    backgroundTint: Color = Backgrounds.qrScanner.toMappedColors(),
    borderColor: Color = Borders.qrScanner.toMappedColors(),
    backgroundTextColor: Color = Backgrounds.qrScannerPrompt.toMappedColors(),
    instructionContent: @Composable () -> Unit = {
        QrOverlayText(
            instructionText = instructionsText,
            instructionTextContentDesc = instructionTextContentDesc,
            textColor = Text.qrScanner.toMappedColors(),
            textBackground = backgroundTextColor,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
        )
    },
) {
    val density = LocalDensity.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        instructionContent()

        CameraContent(
            surfaceRequest = surfaceRequest,
            previewUseCase = previewUseCase,
            analysisUseCase = analysisUseCase,
            imageCaptureUseCase = imageCaptureUseCase,
            modifier = Modifier
                .fillMaxSize()
                .testTag("cameraViewfinder")
                .qrScannerOverlay(
                    overlayTint = backgroundTint,
                    qrBorderColor = borderColor,
                    density = density,
                ),
            coroutineScope = coroutineScope,
            cameraUpdater = onUpdateViewModelCamera,
        )
    }
}

@Composable
fun QrOverlayText(
    instructionText: String,
    instructionTextContentDesc: String,
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
            modifier = Modifier
                .padding(all = smallPadding)
                .semantics {
                    this.contentDescription = instructionTextContentDesc
                },
        )
    }
}

@Composable
fun QrOverlayText(
    instructionText: String,
    instructionTextContentDesc: String,
    textColor: Color,
    textBackground: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        Text(
            text = instructionText,
            style = MaterialTheme.typography.headlineSmall,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(textBackground)
                .padding(horizontal = smallPadding, vertical = mediumPadding)
                .semantics {
                    this.contentDescription = instructionTextContentDesc
                },
        )
    }
}
