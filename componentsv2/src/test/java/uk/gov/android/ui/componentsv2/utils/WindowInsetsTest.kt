package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.WindowInsets
import androidx.compose.ui.test.assertIsEqualTo
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.utils.WindowInsetsTest.Companion.CONTENT_TAG
import uk.gov.android.ui.componentsv2.utils.WindowInsetsTest.Companion.WINDOW_TAG

@RunWith(RobolectricTestRunner::class)
class WindowInsetsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `safe screen reserves the status and navigation bars`() {
        val insets = createWindowInsets(statusBarTop = 10, navigationBarBottom = 20)
        composeTestRule.setContent(insets) {
            Content(modifier = Modifier.safeScreenPadding())
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(top = 10.dp, bottom = 20.dp)
    }

    @Test
    fun `safe screen reserves a landscape display cutout`() {
        val insets = createWindowInsets(displayCutoutLeft = 30)
        composeTestRule.setContent(insets) {
            Content(modifier = Modifier.safeScreenPadding())
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(left = 30.dp)
    }

    @Test
    fun `safe screen adds vertical gestures and ignores horizontal gestures`() {
        val insets =
            createWindowInsets(
                gestureLeft = 40,
                gestureRight = 50,
                gestureTop = 60,
                gestureBottom = 70,
            )
        composeTestRule.setContent(insets) {
            Content(modifier = Modifier.safeScreenPadding())
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(top = 60.dp, bottom = 70.dp)
    }

    @Test
    fun `safe component reserves horizontal gestures and ignores vertical gestures`() {
        val insets =
            createWindowInsets(
                gestureLeft = 40,
                gestureRight = 50,
                gestureTop = 60,
                gestureBottom = 70,
            )
        composeTestRule.setContent(insets) {
            Content(modifier = Modifier.safeComponentPadding())
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(left = 40.dp, right = 50.dp)
    }

    @Test
    fun `safe component raises horizontal edges up to the minimum padding`() {
        val insets = createWindowInsets(gestureLeft = 4, gestureRight = 40)
        composeTestRule.setContent(insets) {
            Content(modifier = Modifier.safeComponentPadding(16.dp))
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(left = 16.dp, right = 40.dp)
    }

    @Test
    fun `screen padding then component padding inset content without double counting`() {
        val insets = createWindowInsets(displayCutoutLeft = 30, gestureLeft = 40, gestureRight = 50)
        composeTestRule.setContent(insets) {
            Box(modifier = Modifier.safeScreenPadding()) {
                Content(modifier = Modifier.safeComponentPadding())
            }
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(left = 40.dp, right = 50.dp)
    }

    @Test
    fun `screen padding then component padding resolved eagerly without double counting`() {
        val insets = createWindowInsets(displayCutoutLeft = 30, gestureLeft = 40, gestureRight = 50)
        composeTestRule.setContent(insets) {
            val screenPadding = WindowInsets.safeScreen.asPaddingValues()

            // When using asPaddingValues() to access the WindowInsets, we don't have any info
            // about which insets have already been consumed. So we need to manually exclude
            // the safeScreen insets from the safeComponent insets.
            val componentPadding = WindowInsets.safeComponent()
                .exclude(WindowInsets.safeScreen).asPaddingValues()

            Box(modifier = Modifier.windowInsetsPadding(screenPadding)) {
                Content(modifier = Modifier.windowInsetsPadding(componentPadding))
            }
        }

        val padding = composeTestRule.getContentPadding()

        padding.assertEqualTo(left = 40.dp, right = 50.dp)
    }

    private fun ComposeContentTestRule.setContent(
        windowInsets: WindowInsetsCompat,
        content: @Composable () -> Unit,
    ) {
        setContent {
            DeviceConfigurationOverride(
                override = DeviceConfigurationOverride.WindowInsets(windowInsets),
                content = {
                    Window { content() }
                },
            )
        }

        waitForIdle()
    }

    private fun ComposeTestRule.getContentPadding(): ResolvedPadding {
        val window = onNodeWithTag(WINDOW_TAG).getUnclippedBoundsInRoot()
        val content = onNodeWithTag(CONTENT_TAG).getUnclippedBoundsInRoot()

        return ResolvedPadding(
            content.left - window.left,
            content.top - window.top,
            window.right - content.right,
            window.bottom - content.bottom,
        )
    }

    private fun ResolvedPadding.assertEqualTo(
        left: Dp = 0.dp,
        top: Dp = 0.dp,
        right: Dp = 0.dp,
        bottom: Dp = 0.dp,
    ) {
        this.left.assertIsEqualTo(left, "left")
        this.top.assertIsEqualTo(top, "top")
        this.right.assertIsEqualTo(right, "right")
        this.bottom.assertIsEqualTo(bottom, "bottom")
    }

    private data class ResolvedPadding(val left: Dp, val top: Dp, val right: Dp, val bottom: Dp)

    internal companion object {
        const val WINDOW_TAG = "window"
        const val CONTENT_TAG = "component"
    }
}

@Composable
private fun Window(content: @Composable () -> Unit) = Box(
    Modifier
        .fillMaxSize()
        .testTag(WINDOW_TAG),
) {
    content()
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .testTag(CONTENT_TAG),
    )
}
