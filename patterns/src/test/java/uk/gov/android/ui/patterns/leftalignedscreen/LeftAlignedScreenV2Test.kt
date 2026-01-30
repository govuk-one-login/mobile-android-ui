package uk.gov.android.ui.patterns.leftalignedscreen

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
class LeftAlignedScreenV2Test {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = LeftAlignedScreenContentProviderV2().values.toImmutableList()
    private val accessibilityList = LeftAlignedScreenContentAccessibilityProviderV2().values
        .toImmutableList()

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

    @Test
    fun testV2() {
        val content = contentList[0]
        setupV2Screen(content)
        verifyTitle(content)
    }

    @Test
    fun testContentParams() {
        val content = contentList[0]
        setupContentParams(content)
        verifyTitle(content)
    }

    @Test
    fun testWarningAndNumberedList() {
        val content = contentList[1]
        setupScreen(content)
        verifyNumberedList(content)
        verifyWarning(content)
    }

    private fun setupScreen(content: LeftAlignedScreenContentV2) {
        composeTestRule.setContent {
            GdsTheme {
                PreviewLeftAlignedScreenV2(content)
            }
        }
    }

    private fun setupV2Screen(content: LeftAlignedScreenContentV2) {
        composeTestRule.setContent {
            GdsTheme {
                LeftAlignedScreenV2(
                    title = content.title,
                    body = content.body?.toImmutableList(),
                    supportingText = content.supportingText,
                    primaryButton = LeftAlignedScreenButton(
                        text = content.primaryButton!!,
                        onClick = {},
                    ),
                )
            }
        }
    }

    private fun setupContentParams(content: LeftAlignedScreenContentV2) {
        composeTestRule.setContent {
            GdsTheme {
                LeftAlignedScreenFromContentParamsV2(content)
            }
        }
    }

    private fun setupScreenWithAccessibilityContent(content: LeftAlignedScreenContentV2) {
        composeTestRule.setContent {
            GdsTheme {
                PreviewLeftAlignedScreenV2Accessibility(content)
            }
        }
    }

    private fun verifyTitle(content: LeftAlignedScreenContentV2) {
        val title = hasText(content.title)
        composeTestRule
            .onNode(title)
            .assertIsDisplayed()
    }

    private fun verifyNumberedList(content: LeftAlignedScreenContentV2) {
        content.body?.forEach { body ->
            if (body is LeftAlignedScreenBodyV2.NumberedList) {
                val text = hasText(body.list[2].text)
                composeTestRule
                    .onAllNodes(text)
                    .assertAreDisplayed()
            }
        }
    }

    private fun verifyWarning(content: LeftAlignedScreenContentV2) {
        content.body?.forEach { body ->
            if (body is LeftAlignedScreenBodyV2.Warning) {
                val text = hasText(body.text)
                composeTestRule
                    .onNode(text)
                    .assertIsDisplayed()
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
