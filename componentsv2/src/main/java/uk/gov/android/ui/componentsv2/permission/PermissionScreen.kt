package uk.gov.android.ui.componentsv2.permission

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

/**
 * Composable wrapper for handling runtime permission request logic within an app.
 *
 * @param hasPreviouslyDeniedPermission Whether the User has previously denied the permission
 * requested within the [permissionState]. This commonly gets updated outside of the
 * [PermissionScreen] as part of the logic within the `onPermissionResult`
 * parameter within [com.google.accompanist.permissions.rememberPermissionState].
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    permissionState: PermissionState,
    hasPreviouslyDeniedPermission: Boolean = false,
    onGrantPermission: @Composable () -> Unit = {},
    onPermissionPermanentlyDenied: @Composable () -> Unit = {},
    onRequirePermission: @Composable (launchPermission: () -> Unit) -> Unit = {},
    onShowRationale: @Composable (launchPermission: () -> Unit) -> Unit = {},
) {
    when {
        permissionState.status.isGranted -> onGrantPermission()
        permissionState.status.shouldShowRationale ->
            onShowRationale {
                permissionState.launchPermissionRequest()
            }
        permissionState.isPermanentlyDenied(hasPreviouslyDeniedPermission) ->
            onPermissionPermanentlyDenied()
        else -> {
            onRequirePermission {
                permissionState.launchPermissionRequest()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private fun PermissionState.isPermanentlyDenied(
    hasPreviouslyDeniedPermission: Boolean,
): Boolean = !status.isGranted &&
    !status.shouldShowRationale &&
    hasPreviouslyDeniedPermission
