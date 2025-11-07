package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.testwrapper.R

object CameraContentDemoButtons {
    @Composable
    fun CameraRequirePermissionButton(
        modifier: Modifier = Modifier,
        launchPermission: () -> Unit = {},
    ) {
        GdsButton(
            modifier = modifier.testTag("permissionRequiredButton"),
            text =
            stringResource(
                R.string.dialogue_demo_camera_permission_required,
            ),
            buttonType = ButtonTypeV2.Primary(),
            onClick = {
                launchPermission()
            },
        )
    }

    @Composable
    fun CameraPermissionRationaleButton(
        modifier: Modifier = Modifier,
        launchPermission: () -> Unit = {},
    ) {
        GdsButton(
            modifier = modifier.testTag("permissionRationaleButton"),
            text =
            stringResource(
                R.string.dialogue_demo_camera_permission_rationale,
            ),
            buttonType = ButtonTypeV2.Primary(),
            onClick = {
                launchPermission()
            },
        )
    }

    @Composable
    @OptIn(ExperimentalPermissionsApi::class)
    fun PermanentCameraDenial(
        permissionState: PermissionState,
        context: Context,
        modifier: Modifier = Modifier,
    ) {
        Text(
            text =
            "${permissionState.permission} is permanently denied.\n\n" +
                "Please update your app settings.",
            textAlign = TextAlign.Center,
        )

        val openSettingsText =
            stringResource(
                R.string.dialogue_demo_camera_open_permissions,
            )
        GdsButton(
            modifier = modifier.testTag("permissionRationaleButton"),
            text = openSettingsText,
            buttonType = ButtonTypeV2.Primary(),
            onClick = {
                val intent =
                    Intent(
                        ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts(
                            "package",
                            context.packageName,
                            null,
                        ),
                    )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            },
        )
    }
}
