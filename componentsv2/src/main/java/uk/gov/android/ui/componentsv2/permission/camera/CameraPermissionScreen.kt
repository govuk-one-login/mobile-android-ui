package uk.gov.android.ui.componentsv2.permission.camera

import android.Manifest
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import uk.gov.android.ui.componentsv2.permission.PermissionScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionScreen(
    onGrantPermission: @Composable () -> Unit = {},
    onRequirePermission: @Composable (launchPermission: () -> Unit) -> Unit = {},
    onShowRationale: @Composable () -> Unit = {},
) {
    PermissionScreen(
        permission = Manifest.permission.CAMERA,
        onGrantPermission = onGrantPermission,
        onRequirePermission = onRequirePermission,
        onShowRationale = onShowRationale,
    )
}
