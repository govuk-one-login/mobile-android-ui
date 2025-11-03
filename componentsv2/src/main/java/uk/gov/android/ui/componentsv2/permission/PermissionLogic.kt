package uk.gov.android.ui.componentsv2.permission

import androidx.compose.runtime.Composable

/**
 * Wrapper data class for holding all logical outcomes when requesting an Android runtime
 * permission.
 */
data class PermissionLogic(
    val onGrantPermission: @Composable () -> Unit = {},
    val onPermissionPermanentlyDenied: @Composable () -> Unit = {},
    val onRequirePermission: @Composable (launchPermission: () -> Unit) -> Unit = {},
    val onShowRationale: @Composable (launchPermission: () -> Unit) -> Unit = {},
)
