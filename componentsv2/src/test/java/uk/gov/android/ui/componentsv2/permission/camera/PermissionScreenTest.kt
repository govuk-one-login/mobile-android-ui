package uk.gov.android.ui.componentsv2.permission.camera

import android.Manifest
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.rule.GrantPermissionRule
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.permission.PermissionScreen

@RunWith(RobolectricTestRunner::class)
class PermissionScreenTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
    )

    private var hasReachedHappyPath = false

    @OptIn(ExperimentalPermissionsApi::class)
    @Test
    fun grantedPermissionsDeferToOnGrantPermission() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = rememberPermissionState(Manifest.permission.CAMERA),
                onGrantPermission = {
                    hasReachedHappyPath = true
                },
                onRequirePermission = { launchPermission ->
                    fail("The permission shouldn't be required!")
                },
                onShowRationale = {
                    fail("The rationale shouldn't have been shown!")
                },
            )
        }

        assertTrue(
            "The permission should have been granted!",
            hasReachedHappyPath,
        )
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Test
    fun ungrantedPermissionsDeferToOnRequirePermission() {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = rememberPermissionState(Manifest.permission.BLUETOOTH),
                onGrantPermission = {
                    fail("The permission shouldn't have been granted!")
                },
                onRequirePermission = { launchPermission ->
                    hasReachedHappyPath = true
                },
                onShowRationale = {
                    fail("The rationale shouldn't have been shown!")
                },
            )
        }

        assertTrue("The permission should have been required!", hasReachedHappyPath)
    }
}
