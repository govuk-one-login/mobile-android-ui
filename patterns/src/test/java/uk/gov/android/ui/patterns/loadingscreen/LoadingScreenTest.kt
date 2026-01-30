package uk.gov.android.ui.patterns.loadingscreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@RunWith(RobolectricTestRunner::class)
class LoadingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = LoadingScreenContentProvider().values.toImmutableList()

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun testLoadingScreen() {
        val content = contentList[0]
        composeTestRule.setContent {
            LoadingScreen(
                text = content,
            )
        }
        verifyText(content)
    }

    @Test
    fun testLoadingScreenPreview() {
        val content = contentList[0]
        composeTestRule.setContent {
            PreviewDefaultLoadingScreen(content)
        }
        verifyText(content)
    }

    @Test
    fun testLoadingScreenPreviewNoContent() {
        composeTestRule.setContent {
            PreviewDefaultLoadingScreenNoContent()
        }
        verifyText("Loading")
    }

    private fun verifyText(content: String) {
        val text = hasText(content)
        composeTestRule
            .onNode(text)
            .assertIsDisplayed()
    }
}
