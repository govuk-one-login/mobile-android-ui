package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton

@RunWith(RobolectricTestRunner::class)
class ErrorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var title: SemanticsMatcher
    private lateinit var continueButton: SemanticsMatcher

    private val titleText = "Title"
    private val buttonText = "Continue"

    @Before
    fun setUp() {
        title = hasText(titleText)
        continueButton = hasText(buttonText)
    }

    @Test
    fun onClickWhenPrimaryButtonIsEnabled() {
        var didClick = false

        composeTestRule.setContent {
            ErrorScreen(
                ErrorScreenIcon.ErrorIcon,
                titleText,
                primaryButton = CentreAlignedScreenButton(
                    text = buttonText,
                    onClick = { didClick = true },
                ),
            )
        }

        composeTestRule
            .onNode(title)
            .assertIsDisplayed()

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHasClickAction()
            .performClick()

        assertTrue(didClick)
    }

    @Test
    fun onClickWhenPrimaryButtonIsDisabled() {
        var didClick = false

        composeTestRule.setContent {
            ErrorScreen(
                ErrorScreenIcon.ErrorIcon,
                titleText,
                primaryButton = CentreAlignedScreenButton(
                    text = buttonText,
                    onClick = { didClick = true },
                    enabled = false,
                ),
            )
        }

        composeTestRule
            .onNode(title)
            .assertIsDisplayed()

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsNotEnabled()
            .assertHasClickAction()
            .performClick()

        assertFalse(didClick)
    }

    @Test
    fun onClickWhenSecondaryButtonIsEnabled() {
        var didClick = false

        composeTestRule.setContent {
            ErrorScreen(
                ErrorScreenIcon.ErrorIcon,
                titleText,
                secondaryButton = CentreAlignedScreenButton(
                    text = buttonText,
                    onClick = { didClick = true },
                ),
            )
        }

        composeTestRule
            .onNode(title)
            .assertIsDisplayed()

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHasClickAction()
            .performClick()

        assertTrue(didClick)
    }

    @Test
    fun onClickWhenSecondaryButtonIsDisabled() {
        var didClick = false

        composeTestRule.setContent {
            ErrorScreen(
                ErrorScreenIcon.ErrorIcon,
                titleText,
                secondaryButton = CentreAlignedScreenButton(
                    text = buttonText,
                    onClick = { didClick = true },
                    enabled = false,
                ),
            )
        }

        composeTestRule
            .onNode(title)
            .assertIsDisplayed()

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsNotEnabled()
            .assertHasClickAction()
            .performClick()

        assertFalse(didClick)
    }
}
