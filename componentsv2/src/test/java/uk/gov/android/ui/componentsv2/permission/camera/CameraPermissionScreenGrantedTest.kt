package uk.gov.android.ui.componentsv2.permission.camera

import android.Manifest
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.rule.GrantPermissionRule
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CameraPermissionScreenGrantedTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
    )

    private var granted = false

    @Test
    fun grantedPermissionsDeferToOnGrantPermission() {
        composeTestRule.setContent {
            CameraPermissionScreen(
                onGrantPermission = {
                    granted = true
                },
                onRequirePermission = { launchPermission ->
                    fail("The permission shouldn't be required!")
                },
                onShowRationale = {
                    fail("The rationale shouldn't have been shown!")
                },
            )
        }

        assertTrue("The permission should have been granted!", granted)
    }
}
