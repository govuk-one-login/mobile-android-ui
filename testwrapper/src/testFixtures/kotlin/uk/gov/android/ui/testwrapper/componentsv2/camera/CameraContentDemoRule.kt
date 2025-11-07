package uk.gov.android.ui.testwrapper.componentsv2.camera

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import uk.gov.android.ui.componentsv2.Renderer
import uk.gov.android.ui.componentsv2.rules.ComposeContentTestRuleExtensions.onAllNodesWithRole
import uk.gov.android.ui.componentsv2.rules.ComposeContentTestRuleExtensions.onNodeWithRole

/**
 * Custom [ComposeContentTestRule] for the [CameraContentDemo] screen.
 */
class CameraContentDemoRule(
    composeTestRule: ComposeContentTestRule,
    private val cameraViewfinderTag: String = "cameraViewfinder",
) : ComposeContentTestRule by composeTestRule,
    Renderer<Modifier> {
    override fun render(input: Modifier) {
        setContent {
            CameraContentDemo(modifier = input)
        }
    }

    fun assertPermissionButtonDoesNotExist() =
        onAllNodesWithRole(Role.Button)
            .assertCountEquals(0)

    fun assertPermissionButtonExists(containedText: String) =
        onNodeWithRole(Role.Button)
            .assertExists()
            .assertIsDisplayed()
            .assertTextContains(containedText)

    fun assertCameraIsRendered() =
        onNodeWithTag(cameraViewfinderTag)
            .assertExists()
            .assertIsDisplayed()

    fun assertCameraIsNotRendered() =
        onNodeWithTag(cameraViewfinderTag)
            .assertDoesNotExist()
}
