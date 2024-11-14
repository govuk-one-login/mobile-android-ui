package uk.gov.android.ui.components.m3.buttons

import android.content.Context
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.minimumTouchTarget

class CloseButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var closeButton: SemanticsMatcher

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        closeButton = hasContentDescription(context.getString(R.string.preview__CloseButton__close))
    }

    @Test
    fun minimumInteractionSize() {
        composeTestRule.setContent {
            CloseButton { }
        }
        composeTestRule.onNode(closeButton).assertHeightIsAtLeast(minimumTouchTarget)
        composeTestRule.onNode(closeButton).assertWidthIsAtLeast(minimumTouchTarget)
        composeTestRule.onNode(closeButton).assertIsDisplayed()
    }

    @Test
    fun onClose() {
        var actual = false
        composeTestRule.setContent {
            CloseButton { actual = true }
        }
        composeTestRule.onNode(closeButton).assertHasClickAction()
        composeTestRule.onNode(closeButton).assertIsEnabled()
        composeTestRule.onNode(closeButton).performClick()
        assertEquals(true, actual)
    }

    @Test
    fun previewTest() {
        composeTestRule.setContent {
            CloseButtonPreview()
        }
    }
}
