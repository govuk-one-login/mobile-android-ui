package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.ui.test.isNotSelected
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GdsRadioSelectionTest {
    private val expectedParameterSize = 4
    private val contentList = RadioSelectionProvider().values.toList()
    private val sampleContent = RadioSelectionProvider().values.first()
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
        composeTestRule.setContent {
            GdsRadioSelection(
                radioSelectionParams = sampleContent,
            )
        }

        sampleContent.optionText.forEach { option ->
            composeTestRule.onNodeWithText(option.text).assertExists()
        }

        composeTestRule.onNodeWithText("Example Heading")
            .assertExists()

        composeTestRule.onNode(isSelected()).assertExists()
        for(i in 1 until sampleContent.optionText.size){
            composeTestRule.onNode(isNotSelected()).assertExists()
        }
    }

    @Test
    fun testChangeSelection() {
        composeTestRule.setContent {
            GdsRadioSelection(
                radioSelectionParams = sampleContent,
            )
        }
        val secondOption = sampleContent.optionText[1]
        composeTestRule.onNodeWithText(secondOption.text).performClick()
        composeTestRule.onNode(isNotSelected()).assertExists()
        composeTestRule.onNode(isSelected()).assertExists()
    }
}