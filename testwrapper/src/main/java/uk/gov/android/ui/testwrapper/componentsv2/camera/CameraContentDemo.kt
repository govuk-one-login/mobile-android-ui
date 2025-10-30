package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.Manifest
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
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
    viewModel.removeUseCases()

    listOf(
        providePreviewUseCase(viewModel::update),
        provideBarcodeAnalysis(
            options = provideQrScanningOptions(
                provideZoomOptions(viewModel::getCurrentCamera)
            )
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