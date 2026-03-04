package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performScrollTo
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class CentreAlignedScreenContentProviderTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = CentreAlignedScreenContentProvider().values.toImmutableList()

    @Test
    fun testFirstItem() {
        val content = contentList[0]
        setupScreen(content)
        val title = hasText(content.title)
        val textLine = hasText(ONE_LINE)
        composeTestRule
            .onNode(title)
            .assertIsDisplayed()

        composeTestRule
            .onAllNodes(textLine)
            .assertAreDisplayed()
    }

    @Test
    fun testButtonsWithIcons() {
        val content = contentList[1]
        setupScreen(content)
        val secondaryButton = hasText(content.secondaryButton?.text ?: "Secondary button")
        val primaryButton = hasText(content.primaryButton?.text ?: "Primary button")

        composeTestRule
            .onAllNodes(primaryButton)
            .assertAreDisplayed()
        composeTestRule
            .onAllNodes(primaryButton)
            .assertAreDisplayed()

        composeTestRule
            .onAllNodes(secondaryButton)
            .assertAreDisplayed()

        composeTestRule
            .onAllNodes(secondaryButton)
            .assertAreDisplayed()
    }

    private fun setupScreen(content: CentreAlignedScreenContent) {
        composeTestRule.setContent {
            GdsTheme {
                CentreAlignedScreen(
                    title = content.title,
                    image = content.image,
                    body = content.body,
                    supportingText = content.supportingText,
                    primaryButton = content.primaryButton,
                    secondaryButton = content.secondaryButton,
                )
            }
        }
    }

    private fun SemanticsNodeInteractionCollection.assertAreDisplayed(): SemanticsNodeInteractionCollection {
        fetchSemanticsNodes().forEachIndexed { index, _ ->
            get(index).performScrollTo().assertIsDisplayed()
        }
        return this
    }
}
