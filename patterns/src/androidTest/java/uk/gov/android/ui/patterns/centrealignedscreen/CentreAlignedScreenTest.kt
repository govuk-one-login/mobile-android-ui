package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

class CentreAlignedScreenTest {
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
            CentreAlignedScreen(
                title = titleText,
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

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun titleHasContentDescriptionAndHeadingRole() {
        val title = "Test title"

        composeTestRule.setContent {
            CentreAlignedScreen(
                title = { GdsHeading(text = title) },
            )
        }

        composeTestRule
            .onNodeWithText(title)
            .assertContentDescriptionEquals(title)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
    }
}
