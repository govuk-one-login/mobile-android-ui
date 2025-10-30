package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.Manifest
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.componentsv2.matchers.SemanticsMatchers.hasRole
import uk.gov.android.ui.componentsv2.rules.ComposeContentTestRuleExtensions.onAllNodesWithRole
import uk.gov.android.ui.componentsv2.rules.ComposeContentTestRuleExtensions.onNodeWithRole
import uk.gov.android.ui.testwrapper.R

class CameraContentDemoTest {
    @get:Rule
    val screenRule = CameraContentDemoRule(createComposeRule())

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA
    )

    @Test
    fun cameraViewfinderExists() = runTest {
        screenRule.apply {
            render(Modifier)
            assertPermissionButtonDoesNotExist()
            assertCameraIsRendered()
        }
    }
}