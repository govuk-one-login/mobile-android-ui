package uk.gov.android.ui.componentsv2.permission

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    permissionState: PermissionState,
    onGrantPermission: @Composable () -> Unit = {},
    onRequirePermission: @Composable (launchPermission: () -> Unit) -> Unit = {},
    onShowRationale: @Composable (launchPermission: () -> Unit) -> Unit = {},
) {
    when {
        permissionState.status.isGranted -> onGrantPermission()
        permissionState.status.shouldShowRationale ->
            onShowRationale {
                permissionState.launchPermissionRequest()
            }
        else -> {
            onRequirePermission {
                permissionState.launchPermissionRequest()
            }
        }
    }
}
