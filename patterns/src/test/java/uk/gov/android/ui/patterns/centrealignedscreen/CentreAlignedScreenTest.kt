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
import kotlinx.collections.immutable.persistentListOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType

@RunWith(RobolectricTestRunner::class)
class CentreAlignedScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var title: SemanticsMatcher
    private lateinit var continueButton: SemanticsMatcher
    private lateinit var exitButton: SemanticsMatcher

    private val titleText = "Title"
    private val buttonText = "Continue"
    private val secondaryButtonText = "Exit"

    @Before
    fun setUp() {
        title = hasText(titleText)
        continueButton = hasText(buttonText)
        exitButton = hasText(secondaryButtonText)
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
                body = persistentListOf(
                    CentreAlignedScreenBodyContent.BulletList(
                        title = ListTitle(text = "Bulleted list", titleType = TitleType.Text),
                        items = persistentListOf("Item 1", "Item 2"),
                    ),
                    CentreAlignedScreenBodyContent.NumberedList(
                        title = ListTitle(text = "Bulleted list", titleType = TitleType.Text),
                        items = persistentListOf(
                            ListItem("Item 1"),
                            ListItem("Item 2"),
                        ),
                    ),
                    CentreAlignedScreenBodyContent.Button(
                        text = secondaryButtonText,
                        onClick = {},
                    ),
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

        composeTestRule
            .onNode(exitButton)
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHasClickAction()

        assertTrue(didClick)
    }

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
