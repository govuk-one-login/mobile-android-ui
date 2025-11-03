package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.Manifest
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeScanResult
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.provideBarcodeAnalysis
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.providePreviewUseCase
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.provideQrScanningOptions
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.provideZoomOptions
import uk.gov.android.ui.componentsv2.permission.PermissionScreen
import uk.gov.android.ui.testwrapper.R
import uk.gov.android.ui.testwrapper.componentsv2.camera.CANVAS_HEIGHT_MULTIPLIER
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.spacingDouble


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CameraContentDemo(
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val (
        hasPreviouslyDeniedPermission,
        onUpdatePreviouslyDeniedPermission,
    ) = remember { mutableStateOf(false) }

    val permissionState = rememberPermissionState(Manifest.permission.CAMERA) {
        onUpdatePreviouslyDeniedPermission(!it)
    }

    val viewModel = viewModel<CameraContentViewModel>()
    val context = LocalContext.current
    val windowContainer = LocalWindowInfo.current.containerSize
    viewModel.removeUseCases()

    listOf(
        providePreviewUseCase(viewModel::update),
        provideBarcodeAnalysis(
            context = context,
            options = provideQrScanningOptions(
                provideZoomOptions(viewModel::getCurrentCamera)
            ),
            windowContainer = windowContainer,
            relativeScanningWidth = CANVAS_WIDTH_MULTIPLIER,
        ) { result ->
            when (result) {
                BarcodeScanResult.EmptyScan -> "Barcode data not found"
                is BarcodeScanResult.Success ->
                    result.mapToUrlStrings().firstOrNull() ?: "No URL found!"

                is BarcodeScanResult.Single ->
                    result.barcode.url?.url ?: "No URL found from single result!"

                is BarcodeScanResult.Failure -> result.message
            }.let { logMessage ->
                Log.i(
                    "CameraContentDemo",
                    "Barcode scanning result: $logMessage",
                )
            }
        }
    ).map(CameraUseCaseProvider::provide)
        .let(viewModel::addAll)

    Column(
        modifier = modifier
            .background(GdsLocalColorScheme.current.dialogBackground)
            .padding(spacingDouble)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PermissionScreen(
            permissionState = permissionState,
            hasPreviouslyDeniedPermission = hasPreviouslyDeniedPermission,
            onGrantPermission = {
                CameraContent(
                    coroutineScope = coroutineScope,
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("cameraViewfinder")
                        .qrCodeOverlay(
                            canvasWidthMultiplier = CANVAS_WIDTH_MULTIPLIER,
                        )
                )
            },
            onPermissionPermanentlyDenied = {
                Text(
                    text = "${permissionState.permission} is permanently denied.\n\n" +
                            "Please update your app settings.",
                    textAlign = TextAlign.Center,
                )

                GdsButton(
                    modifier = Modifier.testTag("permissionRationaleButton"),
                    text = stringResource(
                        R.string.dialogue_demo_camera_open_permissions
                    ),
                    buttonType = ButtonTypeV2.Primary(),
                    onClick = {
                        val intent = Intent(
                            ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts(
                                "package",
                                context.packageName,
                                null,
                            )
                        )
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    },
                )
            },
            onShowRationale = { launchPermission ->
                GdsButton(
                    modifier = Modifier.testTag("permissionRationaleButton"),
                    text = stringResource(
                        R.string.dialogue_demo_camera_permission_rationale
                    ),
                    buttonType = ButtonTypeV2.Primary(),
                    onClick = {
                        launchPermission()
                    },
                )
            },
            onRequirePermission = { launchPermission ->
                GdsButton(
                    modifier = Modifier.testTag("permissionRequiredButton"),
                    text = stringResource(
                        R.string.dialogue_demo_camera_permission_required
                    ),
                    buttonType = ButtonTypeV2.Primary(),
                    onClick = {
                        launchPermission()
                    },
                )
            }
        )
    }
}


@Suppress("LongParameterList")
private fun drawBorder(
    contentDrawScope: ContentDrawScope,
    canvasWidth: Float,
    canvasHeight: Float,
    width: Float,
    borderLength: Float,
    color: Color,
) {
    val leftX = (canvasWidth - width) / 2
    val rightX = leftX + width
    val topY = canvasHeight * CANVAS_HEIGHT_MULTIPLIER
    val bottomY = topY + width
    val topLeft = Offset(leftX, topY)
    val topLeftOffset = Offset(leftX - BORDER_OFFSET, topY)
    val topRight = Offset(rightX, topY)
    val topRightOffset = Offset(rightX + BORDER_OFFSET, topY)
    val bottomLeft = Offset(leftX, bottomY)
    val bottomLeftOffset = Offset(leftX - BORDER_OFFSET, bottomY)
    val bottomRight = Offset(rightX, bottomY)
    val bottomRightOffset = Offset(rightX, bottomY + BORDER_OFFSET)
    val topLeftPlusX = Offset(leftX + borderLength, topY)
    val topLeftPlusY = Offset(leftX, topY + borderLength)
    val topRightMinusX = Offset(rightX - borderLength, topY)
    val topRightPlusY = Offset(rightX, topY + borderLength)
    val bottomRightMinusY = Offset(rightX, bottomY - borderLength)
    val bottomRightMinusX = Offset(rightX - borderLength, bottomY)
    val bottomLeftPlusX = Offset(leftX + borderLength, bottomY)
    val bottomLeftMinusY = Offset(leftX, bottomY - borderLength)

    drawBorderLine(contentDrawScope, topLeftOffset, topLeftPlusX, color)
    drawBorderLine(contentDrawScope, topRightMinusX, topRightOffset, color)
    drawBorderLine(contentDrawScope, topRight, topRightPlusY, color)
    drawBorderLine(contentDrawScope, bottomRightMinusY, bottomRightOffset, color)
    drawBorderLine(contentDrawScope, bottomRight, bottomRightMinusX, color)
    drawBorderLine(contentDrawScope, bottomLeftPlusX, bottomLeftOffset, color)
    drawBorderLine(contentDrawScope, bottomLeft, bottomLeftMinusY, color)
    drawBorderLine(contentDrawScope, topLeftPlusY, topLeft, color)
}

private const val CANVAS_WIDTH_MULTIPLIER = .6f
private const val CANVAS_HEIGHT_MULTIPLIER = 0.3f
private const val BORDER_OFFSET = 7f

private fun drawBorderLine(
    contentDrawScope: ContentDrawScope,
    start: Offset,
    end: Offset,
    color: Color,
) {
    contentDrawScope.apply {
        drawLine(
            start = start,
            end = end,
            strokeWidth = 5.dp.toPx(),
            color = color,
        )
    }
}

fun Modifier.qrCodeOverlay(
    canvasWidthMultiplier: Float = CANVAS_WIDTH_MULTIPLIER,
    canvasHeightMultiplier: Float = CANVAS_HEIGHT_MULTIPLIER,
    overlayTint: Color =Color(0x80000000),
    qrBorderColor: Color = Color(0xFFFFFFFF),
) = this.then(
    Modifier
        .drawWithContent {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val width = canvasWidth * canvasWidthMultiplier
            val borderLength = width * canvasHeightMultiplier
            val rectangleSize = Size(width, width)
            val rectangleOffset = Offset(
                (canvasWidth - width) / 2,
                canvasHeight * canvasHeightMultiplier,
            )

            drawContent()
            drawRect(overlayTint)

            // Draws the rectangle in the middle
            drawRect(
                topLeft = rectangleOffset,
                size = rectangleSize,
                color = Color.Transparent,
                blendMode = BlendMode.SrcIn,
            )

            drawBorder(
                this,
                canvasWidth,
                canvasHeight,
                width,
                borderLength,
                qrBorderColor,
            )
        }.onGloballyPositioned { layoutCoordinates ->
            layoutCoordinates.boundsInRoot()
        }
)
