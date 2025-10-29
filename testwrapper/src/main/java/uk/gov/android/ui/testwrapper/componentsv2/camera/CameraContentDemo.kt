package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.Manifest
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.camera.CameraContent
import uk.gov.android.ui.componentsv2.camera.CameraContentViewModel
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeScanResult
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.provideBarcodeAnalysis
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.providePreviewUseCase
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.provideQrScanningOptions
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.provideZoomOptions
import uk.gov.android.ui.componentsv2.permission.PermissionScreen
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.testwrapper.R
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CameraContentDemo(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val viewModel = CameraContentViewModel()

    Scaffold(
        modifier = modifier,
        topBar = {
            GdsTopAppBar(
                title = "Camera Content: Demo",
                navigationButton = GdsIconButtonDefaults.defaultCloseContent(),
                onClick = {
                    coroutineScope.cancel()
                    onDismiss()
                },
                scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior(),
                alignment = TopAppBarAlignment.Start,
            )
        }
    ) { paddingValues ->
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
            modifier = Modifier
                .background(GdsLocalColorScheme.current.dialogBackground)
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            PermissionScreen(
                permissionState = permissionState,
                onGrantPermission = {
                    CameraContent(
                        coroutineScope = coroutineScope,
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                onShowRationale = { launchPermission ->
                    GdsButton(
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
}