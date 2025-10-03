package uk.gov.android.ui.testwrapper.componentsv2.topappbar

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GdsTopAppBarDemoTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources

    @Test
    fun testMenu() {
        val itemTitle1 = "Item does something"
        composeTestRule.setContent {
            GdsTopAppBarDemo(dismiss = {})
        }
        composeTestRule
            .onNodeWithContentDescription(
                resources.getString(uk.gov.android.ui.componentsv2.R.string.info_icon_button)
            )
            .performClick()
        composeTestRule.onNodeWithText(itemTitle1).assertIsDisplayed()
    }
}
