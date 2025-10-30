package uk.gov.android.ui.componentsv2

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

@OptIn(ExperimentalPermissionsApi::class)
object PermissionStateStubs {
    fun granted(
        permission: String,
        onPermissionLaunch: () -> Unit = {},
    ) = object : PermissionState {
        override val permission: String
            get() = permission
        override val status: PermissionStatus
            get() = PermissionStatus.Granted

        override fun launchPermissionRequest() = onPermissionLaunch()
    }

    fun denied(
        permission: String,
        onPermissionLaunch: () -> Unit = {},
    ) = object : PermissionState {
        override val permission: String
            get() = permission
        override val status: PermissionStatus
            get() = PermissionStatus.Denied(false)

        override fun launchPermissionRequest() = onPermissionLaunch()
    }

    fun rationale(
        permission: String,
        onPermissionLaunch: () -> Unit = {},
    ) = object : PermissionState {
        override val permission: String
            get() = permission
        override val status: PermissionStatus
            get() = PermissionStatus.Denied(true)

        override fun launchPermissionRequest() = onPermissionLaunch()
    }
}
