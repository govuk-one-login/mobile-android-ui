package uk.gov.android.ui.pages.dialog

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.components.R

class FullScreenDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var closeButton: SemanticsMatcher
    private lateinit var title: SemanticsMatcher
    private lateinit var content: SemanticsMatcher

    private val titleText = "Title"
    private val contentText = "Content"

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        closeButton = hasContentDescription(context.getString(R.string.preview__CloseButton__close))
        title = hasText(titleText)
        content = hasText(contentText)
    }

    @Test
    fun verifyUI() {
        composeTestRule.setContent {
            FullScreenDialog(title = titleText) {
                Text(contentText)
            }
        }
        composeTestRule.onNode(closeButton).assertIsDisplayed()
        composeTestRule.onNode(title).assertIsDisplayed()
        composeTestRule.onNode(content).assertIsDisplayed()
    }

    @Test
    fun verifyClose() {
        var didClose = false

        composeTestRule.setContent {
            FullScreenDialog(
                title = titleText,
                onDismissRequest = {
                    didClose = true
                },
            ) {
                Text(contentText)
            }
        }

        composeTestRule.onNode(closeButton).assertHasClickAction()
        composeTestRule.onNode(closeButton).assertIsEnabled()
        composeTestRule.onNode(closeButton).performClick()

        assertEquals(true, didClose)
    }
}
