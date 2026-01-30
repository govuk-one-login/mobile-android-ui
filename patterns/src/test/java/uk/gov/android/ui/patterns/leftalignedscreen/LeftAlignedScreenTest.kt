package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class LeftAlignedScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = LeftAlignedScreenContentProvider().values.toImmutableList()
    private val accessibilityList = LeftAlignedScreenContentAccessibilityProvider().values.toImmutableList()

    @Test
    fun testFirstItem() {
        val content = contentList[0]
        setupScreen(content)
        verifyTitle(content)
    }

    @Test
    fun accessibilityTest() {
        val content = accessibilityList[0]
        setupScreenWithAccessibilityContent(content)
        verifyTitle(content)
    }

    private fun setupScreen(content: LeftAlignedScreenContent) {
        composeTestRule.setContent {
            GdsTheme {
                PreviewLeftAlignedScreen(content)
            }
        }
    }

    private fun setupScreenWithAccessibilityContent(content: LeftAlignedScreenContent) {
        composeTestRule.setContent {
            GdsTheme {
                PreviewLeftAlignedScreenAccessibility(content)
            }
        }
    }

    private fun verifyTitle(content: LeftAlignedScreenContent) {
        val title = hasText(content.title)
        composeTestRule
            .onNode(title)
            .assertIsDisplayed()
    }
}
