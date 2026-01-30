package uk.gov.android.ui.patterns.errorscreen

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
class ErrorScreenContentProviderTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = ErrorScreenContentProvider().values.toImmutableList()

    @Test
    fun testFirstItem() {
        val content = contentList[0]
        setupScreen(content)
        val title = hasText(content.title)
        composeTestRule
            .onNode(title)
            .assertIsDisplayed()
    }

    private fun setupScreen(content: ErrorScreenContent) {
        composeTestRule.setContent {
            GdsTheme {
                ErrorScreen(
                    icon = content.icon,
                    title = content.title,
                )
            }
        }
    }
}
