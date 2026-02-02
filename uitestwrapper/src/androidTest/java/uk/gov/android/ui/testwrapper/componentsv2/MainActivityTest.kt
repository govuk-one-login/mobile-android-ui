package uk.gov.android.ui.testwrapper.componentsv2

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
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
    fun goToTopAppBar() {
        val itemTitle1 = "Item does something"
        val topAppBarButtonText = "Top App Bar"

        composeTestRule.onNodeWithTag("entries")
            .performScrollToNode(hasText(topAppBarButtonText))

        composeTestRule
            .onNodeWithText(topAppBarButtonText)
            .performClick()
        composeTestRule
            .onNodeWithText(topAppBarButtonText)
            .performClick()
        composeTestRule
            .onNodeWithContentDescription(
                resources.getString(uk.gov.android.ui.componentsv2.R.string.info_icon_button),
            ).performClick()
        composeTestRule.onNodeWithText(itemTitle1).assertIsDisplayed()
    }

    @Test
    fun navigateToDialogue() {
        val dialogueNavMenuButton = "Dialogue"
        val displayDialogueButton = "Display Dialogue"
        val dismissButton = "Dismiss"
        val dialogTitle = "Dialogue component"
        composeTestRule
            .onNodeWithText(dialogueNavMenuButton)
            .performClick()
        composeTestRule
            .onNodeWithText(dialogueNavMenuButton)
            .performClick()
        composeTestRule
            .onNodeWithText(displayDialogueButton)
            .performClick()
        composeTestRule
            .onNodeWithText(dialogTitle)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(dismissButton)
            .performClick()
        composeTestRule
            .onNodeWithText(displayDialogueButton)
            .assertIsDisplayed()
    }
}
