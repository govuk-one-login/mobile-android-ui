package uk.gov.android.ui.theme.m3

import android.os.Build
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Test under API 34 where edge-to-edge isn't enabled by default.
 *
 * > Once you target SDK 35 or higher on a device running Android 15 or higher,
 * > your app is displayed edge-to-edge.
 *
 * - https://developer.android.com/develop/ui/views/layout/edge-to-edge
 */
private const val ANDROID_SDK_LEVEL = 34

/**
 * This test and the associated opt-out functionality may be removed once the minimum target SDK
 * level is API 36 and it is impossible to opt-out from edge-to-edge display.
 *
 * https://developer.android.com/about/versions/16/behavior-changes-16#edge-to-edge
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [ANDROID_SDK_LEVEL])
class GdsThemeEdgeToEdgeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `given edge-to-edge starts disabled, default theme enables edge-to-edge`() {
        assertFalse(isEdgeToEdgeEnabled())

        composeTestRule.setContent {
            GdsTheme { }
        }

        assertTrue(isEdgeToEdgeEnabled())
    }

    @Test
    fun `given edge-to-edge starts disabled, theme configured with opt-out leaves it disabled`() {
        assertFalse(isEdgeToEdgeEnabled())

        composeTestRule.setContent {
            GdsTheme(enableActivityEdgeToEdge = false) { }
        }

        assertFalse(isEdgeToEdgeEnabled())
    }

    private fun isEdgeToEdgeEnabled(): Boolean {
        if (Build.VERSION.SDK_INT !in 30..<35) {
            // Tests currently run under API 34
            error("Not implemented")
        }

        return isEdgeToEdgeEnabledApi30To34()
    }

    private fun isEdgeToEdgeEnabledApi30To34(): Boolean =
        composeTestRule.activity.window
            .decorView.systemUiVisibility and
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE != 0
}
