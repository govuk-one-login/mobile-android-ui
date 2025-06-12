package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
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

class LeftAlignedScreenTest {
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
    fun onClickWhenButtonIsEnabled() {
        var didClick = false

        composeTestRule.setContent {
            LeftAlignedScreen(
                titleText,
                primaryButton = LeftAlignedScreenButton(
                    buttonText,
                    onClick = { didClick = true },
                    enabled = true,
                ),
            )
        }

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHasClickAction()
            .performClick()

        assertTrue(didClick)
    }

    @Test
    fun onClickWhenButtonIsDisabled() {
        var didClick = false

        composeTestRule.setContent {
            LeftAlignedScreen(
                titleText,
                primaryButton = LeftAlignedScreenButton(
                    buttonText,
                    onClick = { didClick = true },
                    enabled = false,
                ),
            )
        }

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsNotEnabled()
            .assertHasClickAction()
            .performClick()

        assertFalse(didClick)
    }

    @Test
    fun titleHasContentDescriptionAndHeadingRole() {
        composeTestRule.setContent {
            LeftAlignedScreen(
                titleText,
            )
        }

        composeTestRule
            .onNode(title)
            .assertContentDescriptionEquals(titleText)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
    }
}
