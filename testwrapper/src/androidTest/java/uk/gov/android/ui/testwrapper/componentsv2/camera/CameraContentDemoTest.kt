package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.Manifest
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class CameraContentDemoTest {
    @get:Rule
    val screenRule = CameraContentDemoRule(createComposeRule())

    @get:Rule
    val permissionsRule: GrantPermissionRule =
        GrantPermissionRule.grant(
            Manifest.permission.CAMERA,
        )

    @Test
    fun cameraViewfinderExists() =
        runTest {
            screenRule.apply {
                render(Modifier)
                assertPermissionButtonDoesNotExist()
                assertCameraIsRendered()
            }
        }
}
