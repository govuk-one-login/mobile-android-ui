package uk.gov.android.ui.componentsv2.permission

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

/**
 * Wrapper data class for holding all logical outcomes when requesting an Android runtime
 * permission.
 */
data class PermissionLogic
@OptIn(ExperimentalPermissionsApi::class)
constructor(
    val onGrantPermission: @Composable () -> Unit = {},
    val onPermissionPermanentlyDenied: @Composable (
        permissionState: PermissionState,
    ) -> Unit = { _ -> },
    val onRequirePermission: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit,
    ) -> Unit = { _, _ -> },
    val onShowRationale: @Composable (
        permissionState: PermissionState,
        launchPermission: () -> Unit,
    ) -> Unit = { _, _ -> },
)
