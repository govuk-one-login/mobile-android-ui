package uk.gov.android.ui.pages.modal

import android.content.Context
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
import uk.gov.android.ui.components.m3.buttons.ButtonType

class ModalDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var closeButton: SemanticsMatcher
    private lateinit var title: SemanticsMatcher
    private lateinit var header: SemanticsMatcher
    private lateinit var bullets: SemanticsMatcher
    private lateinit var footer: SemanticsMatcher
    private lateinit var primaryButton: SemanticsMatcher

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        closeButton = hasContentDescription(context.getString(R.string.preview__CloseButton__close))
        title = hasText(parameters.title)
        header = hasText(parameters.header.text)
        bullets = hasText(parameters.bullets.first(), substring = true)
        footer = hasText(parameters.footer.text)
        primaryButton = hasText(parameters.buttonParams.text)
    }

    @Test
    fun verifyUI() {
        composeTestRule.setContent {
            ModalDialog(parameters)
        }
        composeTestRule.onNode(closeButton).assertIsDisplayed()
        composeTestRule.onNode(title).assertIsDisplayed()
        composeTestRule.onNode(header).assertIsDisplayed()
        composeTestRule.onNode(bullets).assertIsDisplayed()
        composeTestRule.onNode(footer).assertIsDisplayed()
        composeTestRule.onNode(primaryButton).assertIsDisplayed()
    }

    @Test
    fun onPrimary() {
        var actual = false
        composeTestRule.setContent {
            ModalDialog(
                parameters.copy(
                    buttonParams = buttonParams.copy(
                        onClick = { actual = true },
                    ),
                ),
            )
        }
        composeTestRule.onNode(primaryButton).assertHasClickAction()
        composeTestRule.onNode(primaryButton).assertIsEnabled()
        composeTestRule.onNode(primaryButton).performClick()
        assertEquals(true, actual)
    }

    @Test
    fun previewTest() {
        composeTestRule.setContent {
            ModalDialogPreview()
        }
    }

    companion object {
        private val buttonParams = ModalDialogParameters.ButtonParameters(
            text = "Sign out and delete preferences",
            buttonType = ButtonType.PRIMARY(),
            isEnabled = true,
            onClick = {},
        )
        private val parameters = modalDialogPreviewParams.copy(buttonParams = buttonParams)
    }
}
