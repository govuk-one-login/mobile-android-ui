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
    logic: PermissionLogic,
    hasPreviouslyDeniedPermission: Boolean = false,
) = PermissionScreen(
    hasPreviouslyDeniedPermission = hasPreviouslyDeniedPermission,
    onGrantPermission = logic.onGrantPermission,
    onPermissionPermanentlyDenied = logic.onPermissionPermanentlyDenied,
    onRequirePermission = logic.onRequirePermission,
    onShowRationale = logic.onShowRationale,
    permissionState = permissionState,
)

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
    onPermissionPermanentlyDenied: @Composable (
        permissionState: PermissionState,
    ) -> Unit = { _ -> },
    onRequirePermission: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit,
    ) -> Unit = { _, _ -> },
    onShowRationale: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit,
    ) -> Unit = { _, _ -> },
) {
    when {
        permissionState.status.isGranted -> onGrantPermission()
        permissionState.status.shouldShowRationale ->
            onShowRationale(permissionState) {
                permissionState.launchPermissionRequest()
            }
        permissionState.isPermanentlyDenied(hasPreviouslyDeniedPermission) ->
            onPermissionPermanentlyDenied(permissionState)
        else -> {
            onRequirePermission(permissionState) {
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
