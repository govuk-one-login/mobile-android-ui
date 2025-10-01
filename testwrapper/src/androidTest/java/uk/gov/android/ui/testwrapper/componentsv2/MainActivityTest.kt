package uk.gov.android.ui.testwrapper.componentsv2

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.testwrapper.MainActivity
import uk.gov.android.ui.testwrapper.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources

    @Test
    fun goToStatusOverlay() {
        val itemTitle1 = "Item does something"
        composeTestRule
            .onNodeWithText(resources.getString(R.string.display_top_app_bar_demo))
            .performClick()
        composeTestRule
            .onNodeWithContentDescription(
                resources.getString(uk.gov.android.ui.componentsv2.R.string.info_icon_button)
            )
            .performClick()
        composeTestRule.onNodeWithText(itemTitle1).assertIsDisplayed()
    }
}
