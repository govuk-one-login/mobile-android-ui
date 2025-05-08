package uk.gov.android.ui.theme.m3

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ThemeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun previewTest() {
        composeTestRule.setContent {
            ThemePreview()
        }
    }

    @Test
    fun edgeToEdgePreviewTest() {
        composeTestRule.setContent {
            ThemeE2EPreview()
        }
    }
}
