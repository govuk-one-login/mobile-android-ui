package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GdsSelectionTest {
    private val expectedParameterSize = 6
    private val contentList = RadioSelectionProvider().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            contentList.size,
        )
    }

    @Test
    fun testAllElementsAreDisplayed() {
        val sampleContent = RadioSelectionProvider().values.toList()[1]

        composeTestRule.setContent {
            GdsSelection(
                radioSelectionItems = sampleContent.items,
                title = sampleContent.title,
            )
        }

        sampleContent.items.forEach { option ->
            composeTestRule.onNodeWithText(option, useUnmergedTree = true).assertExists()
        }

        sampleContent.title?.let {
            composeTestRule.onNodeWithText(it.text, useUnmergedTree = true).assertExists()
        }

        composeTestRule.onNode(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.Selected) and
                SemanticsMatcher.expectValue(SemanticsProperties.Selected, true),
            useUnmergedTree = true,
        ).assertExists()

        composeTestRule.onNode(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.Selected) and
                SemanticsMatcher.expectValue(SemanticsProperties.Selected, false),
            useUnmergedTree = true,
        ).assertExists()
    }

    @Test
    fun testChangeSelection() {
        val sampleContent = RadioSelectionProvider().values.toList()[1]

        composeTestRule.setContent {
            GdsSelection(
                radioSelectionItems = sampleContent.items,
                title = sampleContent.title,
            )
        }

        val secondOption = sampleContent.items[1]
        composeTestRule.onNodeWithText(secondOption, useUnmergedTree = true).performClick()

        composeTestRule.onNode(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.Selected) and
                SemanticsMatcher.expectValue(SemanticsProperties.Selected, false),
            useUnmergedTree = true,
        ).assertExists()

        composeTestRule.onNode(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.Selected) and
                SemanticsMatcher.expectValue(SemanticsProperties.Selected, true),
            useUnmergedTree = true,
        ).assertExists()
    }
}
