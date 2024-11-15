package uk.gov.android.ui.theme.m3

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ShapesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun previewTest() {
        composeTestRule.setContent {
            ShapesPreview()
        }
    }
}
