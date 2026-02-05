package uk.gov.android.ui.testwrapper.componentsv2.camera

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.uitestwrapper.R

class CameraContentDemoRequiredPermissionTest {
    @get:Rule
    val screenRule = CameraContentDemoRule(createComposeRule())

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant()

    private val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    @Test
    fun permissionRequestButtonExists() =
        runTest {
            screenRule.apply {
                render(Modifier)
                assertPermissionButtonExists(
                    resources.getString(R.string.dialogue_demo_camera_permission_required),
                )
                assertCameraIsNotRendered()
            }
        }
}
