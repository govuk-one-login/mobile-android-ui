package uk.gov.android.ui.componentsv2.permission

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    permission: String,
    onGrantPermission: @Composable () -> Unit = {},
    onRequirePermission: @Composable (launchPermission: () -> Unit) -> Unit = {},
    onShowRationale: @Composable () -> Unit = {},
) {
    val permissionState = rememberPermissionState(permission)

    when {
        permissionState.status.isGranted -> onGrantPermission()
        permissionState.status.shouldShowRationale -> onShowRationale()
        else -> {
            onRequirePermission(permissionState::launchPermissionRequest)
        }
    }
}
