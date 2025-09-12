package uk.gov.android.ui.componentsv2.status

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class StatusOverlayTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOverlay() {
        val expectedText = "Test message"
        composeTestRule.setContent {
            GdsSnackBar(expectedText)
        }

        composeTestRule
            .onNodeWithText(text = expectedText, useUnmergedTree = true)
            .assertIsDisplayed()
    }
}
