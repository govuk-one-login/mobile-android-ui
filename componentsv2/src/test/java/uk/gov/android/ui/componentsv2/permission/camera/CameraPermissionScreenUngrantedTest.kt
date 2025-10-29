package uk.gov.android.ui.componentsv2.permission.camera

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
class CameraPermissionScreenUngrantedTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant()

    private var required = false

    @Test
    fun ungrantedPermissionsDeferToOnRequirePermission() {
        composeTestRule.setContent {
            CameraPermissionScreen(
                onGrantPermission = {
                    fail("The permission shouldn't have been granted!")
                },
                onRequirePermission = { launchPermission ->
                    required = true
                },
                onShowRationale = {
                    fail("The rationale shouldn't have been shown!")
                },
            )
        }

        assertTrue("The permission should have been required!", required)
    }
}
