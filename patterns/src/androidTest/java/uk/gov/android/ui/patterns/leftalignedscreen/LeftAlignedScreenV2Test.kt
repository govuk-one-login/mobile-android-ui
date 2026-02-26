package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
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
import androidx.compose.ui.test.performKeyInput
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.list.ListItem

class LeftAlignedScreenV2Test {
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
            LeftAlignedScreenV2(
                title = titleText,
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
            LeftAlignedScreenV2(
                title = titleText,
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
            LeftAlignedScreenV2(
                title = titleText,
            )
        }

        composeTestRule
            .onNode(title)
            .assertContentDescriptionEquals(titleText)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun scrollableContent() {
        composeTestRule.setContent {
            LeftAlignedScreenV2(
                title = titleText,
                body = persistentListOf(
                    LeftAlignedScreenBodyV2.BulletList(
                        items = longListItems()
                    )
                )
            )
        }

        composeTestRule
            .onNode(hasText("Item one"))
            .performKeyInput { keyDown(Key.DirectionDown) }
            .performKeyInput { keyDown(Key.DirectionUp) }
    }

    private fun longListItems(): PersistentList<ListItem> {
        return persistentListOf(
            ListItem(text = "Item one"),
            ListItem(text = "Item two"),
            ListItem(text = "Item three"),
            ListItem(text = "Item four"),
            ListItem(text = "Item five"),
            ListItem(text = "Item six"),
            ListItem(text = "Item seven"),
            ListItem(text = "Item eight"),
            ListItem(text = "Item nine"),
            ListItem(text = "Item ten"),
            ListItem(text = "Item eleven"),
            ListItem(text = "Item twelve"),
            ListItem(text = "Item thirteen"),
            ListItem(text = "Item fourteen"),
            ListItem(text = "Item fifteen"),
            ListItem(text = "Item sixteen"),
            ListItem(text = "Item seventeen"),
            ListItem(text = "Item eighteen"),
            ListItem(text = "Item nineteen"),
            ListItem(text = "Item twenty"),
            ListItem(text = "Item twenty one"),
            ListItem(text = "Item twenty two"),
            ListItem(text = "Item twenty three"),
            ListItem(text = "Item twenty four"),
        )
    }
}
