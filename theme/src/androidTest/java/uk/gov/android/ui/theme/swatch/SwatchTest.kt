package uk.gov.android.ui.theme.swatch

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SwatchTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var colorName: SemanticsMatcher
    private lateinit var colorHex: SemanticsMatcher

    @Before
    fun setUp() {
        colorName = hasTestTag(LABEL_TEST_TAG)
        colorHex = hasTestTag(HEX_TEST_TAG)
    }

    @Test
    fun verifyUI() {
        composeTestRule.setContent {
            Swatch(
                data = SwatchColor(
                    Color.White,
                    "White",
                )
            )
        }

        composeTestRule.onNode(colorName).assertIsDisplayed()
        composeTestRule.onNode(colorHex).assertIsDisplayed()
    }

    @Test
    fun previewTest() {
        composeTestRule.setContent {
            SwatchPreview()
        }
    }
}
