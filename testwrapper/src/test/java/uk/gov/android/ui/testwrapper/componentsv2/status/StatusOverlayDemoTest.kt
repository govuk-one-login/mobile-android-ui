package uk.gov.android.ui.testwrapper.componentsv2.status

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class StatusOverlayDemoTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOverlay() {
        val buttonText = "Display overlay"
        composeTestRule.setContent {
            StatusOverlayDemo()
        }
        composeTestRule
            .onNodeWithText(text = buttonText, useUnmergedTree = true)
            .assertIsDisplayed()
    }
}
