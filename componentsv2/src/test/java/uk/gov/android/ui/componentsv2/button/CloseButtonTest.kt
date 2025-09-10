package uk.gov.android.ui.componentsv2.button

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R

@RunWith(RobolectricTestRunner::class)
class CloseButtonTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private var onClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCloseButton() {
        composeTestRule.setContent {
            CloseButton { onClick++ }
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.close_icon_button),
            ).assertIsDisplayed().performClick()
        }
        assertEquals(1, onClick)
    }
}
