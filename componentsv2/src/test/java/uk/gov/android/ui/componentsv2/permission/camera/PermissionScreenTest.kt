package uk.gov.android.ui.componentsv2.permission.camera

import android.Manifest
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.PermissionStateStubs
import uk.gov.android.ui.componentsv2.permission.PermissionScreen

@OptIn(ExperimentalPermissionsApi::class)
@RunWith(RobolectricTestRunner::class)
class PermissionScreenTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    private val deniedUnhappyPathFailure = "The permission shouldn't be required!"
    private val grantedUnhappyPathFailure = "The permission shouldn't have been granted!"
    private val permanentlyDeniedUnhappyPathFailure =
        "The permission shouldn't be permanently denied!"
    private val permission = Manifest.permission.CAMERA
    private val rationaleUnhappyPathFailure = "The rationale shouldn't have been shown!"
    private var hasReachedHappyPath = false

    @Test
    fun grantedPermissionsDeferToOnGrantPermission() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = PermissionStateStubs.granted(permission),
                hasPreviouslyDeniedPermission = false,
                onGrantPermission = {
                    hasReachedHappyPath = true
                },
                onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
                onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
                onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
            )
        }

        assertTrue(
            "The permission should have been granted!",
            hasReachedHappyPath,
        )
    }

    @Test
    fun secondarilyRequestedPermissionsDeferToShowingTheRationale() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState =
                PermissionStateStubs.rationale(permission),
                hasPreviouslyDeniedPermission = false,
                onGrantPermission = { fail(grantedUnhappyPathFailure) },
                onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
                onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
                onShowRationale = { _, launchPermission ->
                    hasReachedHappyPath = true
                },
            )
        }

        assertTrue("The permission should have been required!", hasReachedHappyPath)
    }

    @Test
    fun ungrantedPermissionsDeferToOnRequirePermission() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = PermissionStateStubs.denied(permission),
                hasPreviouslyDeniedPermission = false,
                onGrantPermission = { fail(grantedUnhappyPathFailure) },
                onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
                onRequirePermission = { _, launchPermission ->
                    hasReachedHappyPath = true
                },
                onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
            )
        }

        assertTrue("The permission should have been required!", hasReachedHappyPath)
    }

    @Test
    fun permanentlyDeniedPermissionsRelyOnOuterBooleanFlagAndDeniedStatus() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = PermissionStateStubs.denied(permission),
                hasPreviouslyDeniedPermission = true,
                onGrantPermission = { fail(grantedUnhappyPathFailure) },
                onPermissionPermanentlyDenied = {
                    hasReachedHappyPath = true
                },
                onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
                onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
            )
        }

        assertTrue("The permission should have been required!", hasReachedHappyPath)
    }

    @Test
    fun callingLaunchPermissionViaRequiredPermission() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = PermissionStateStubs.denied(permission) {
                    hasReachedHappyPath = true
                },
                hasPreviouslyDeniedPermission = false,
                onGrantPermission = { fail(grantedUnhappyPathFailure) },
                onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
                onRequirePermission = { _, launchPermission ->
                    launchPermission()
                },
                onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
            )
        }

        assertTrue(
            "The hoisted lambda should've been called via `onRequirePermission`!",
            hasReachedHappyPath,
        )
    }

    @Test
    fun callingLaunchPermissionViaPermissionRationale() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = PermissionStateStubs.rationale(permission) {
                    hasReachedHappyPath = true
                },
                hasPreviouslyDeniedPermission = false,
                onGrantPermission = { fail(grantedUnhappyPathFailure) },
                onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
                onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
                onShowRationale = { _, launchPermission ->
                    launchPermission()
                },
            )
        }

        assertTrue(
            "The hoisted lambda should've been called via `onShowRationale`!",
            hasReachedHappyPath,
        )
    }
}
