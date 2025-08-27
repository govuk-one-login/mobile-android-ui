package uk.gov.android.ui.theme.m3

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TypographyTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test() {
        composeTestRule.setContent {
            TypographyPreview()
        }
        TypographyPreviewParams.types.forEach {
            composeTestRule.apply {
                onNodeWithText(it.first).performScrollTo().assertIsDisplayed()
                onNodeWithText("- ${it.second.fontSize.value.toInt()}sp")
            }
        }
    }
}
