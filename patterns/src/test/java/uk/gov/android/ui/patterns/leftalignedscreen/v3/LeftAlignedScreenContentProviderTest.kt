package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.PRIMARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SECONDARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.TITLE_TEXT
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class LeftAlignedScreenContentProviderTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = LeftAlignedScreenContentProvider().values.toImmutableList()

    @Test
    fun testComplexBodyWithTopAppBarAndFullBottomContent() {
        setupScreen(contentList[0])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(PRIMARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(SECONDARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    @Test
    fun testWarningDividerNumberedListWithInBodySecondaryButton() {
        setupScreen(contentList[1])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(SECONDARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    @Test
    fun testImageAndRadioSelectionWithOnlyPrimaryButton() {
        setupScreen(contentList[2])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(PRIMARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    @Test
    fun testBulletListWithInBodySecondaryButtonAndFullBottomContent() {
        setupScreen(contentList[3])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(PRIMARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(SECONDARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    // [4] Row list in body, in-body secondary button, full bottom content.
    @Test
    fun testRowListWithInBodySecondaryButtonAndFullBottomContent() {
        setupScreen(contentList[4])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(PRIMARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(SECONDARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    @Test
    fun testNoBodyWithLongSupportingTextAndBothButtons() {
        setupScreen(contentList[5])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(PRIMARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(SECONDARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    @Test
    fun testMinimalTitleAndPrimaryButtonOnly() {
        setupScreen(contentList[6])
        composeTestRule.onNode(hasText(TITLE_TEXT)).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText(PRIMARY_BUTTON_TEXT)).onFirst().assertIsDisplayed()
    }

    private fun setupScreen(content: LeftAlignedScreenContent) {
        composeTestRule.setContent {
            val configuration = Configuration(LocalConfiguration.current).apply {
                screenWidthDp = SCREEN_WIDTH_DP
                screenHeightDp = SCREEN_HEIGHT_DP
            }
            CompositionLocalProvider(LocalConfiguration provides configuration) {
                GdsTheme {
                    LeftAlignedScreenV3(
                        modifier = Modifier.size(
                            width = SCREEN_WIDTH_DP.dp,
                            height = SCREEN_HEIGHT_DP.dp,
                        ),
                        topAppBar = content.topAppBar,
                        title = content.title,
                        body = content.body?.toImmutableList(),
                        supportingText = content.supportingText,
                        primaryButton = content.primaryButton?.let {
                            LeftAlignedScreenButton(
                                it,
                                {},
                            )
                        },
                        secondaryButton = content.secondaryButton?.let {
                            LeftAlignedScreenButton(
                                it,
                                {},
                            )
                        },
                    )
                }
            }
        }
    }

    private companion object {
        const val SCREEN_WIDTH_DP = 320

        // Threshold = 900 / 3 = 300dp. Buttons (~62dp) stay well under it, so bottom content
        // remains sticky and is composed once — assertIsDisplayed() is reliable.
        const val SCREEN_HEIGHT_DP = 900
    }
}
