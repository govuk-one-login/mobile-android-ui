package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.PRIMARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SECONDARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SUPPORTING_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.IMAGE_DESCRIPTION
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.TITLE_TEXT

/**
 * Tests for [LeftAlignedScreenV3] with [BottomContentBehaviour.Adaptive].
 *
 * The Adaptive layout measures the bottom content with a SubcomposeLayout and compares it against
 * one third of the screen height ([LocalConfiguration].screenHeightDp) to decide whether the bottom
 * content stays as a sticky footer or moves into the scrollable body:
 *  - Under the 1/3 threshold -> stays sticky, composed once (a single node per element).
 *  - Over the 1/3 threshold -> moved into the body AND measured by the SubcomposeLayout, so each
 *    element resolves to TWO nodes. A single-node matcher ([onNodeWithText]) would fail with
 *    "2 nodes found", so [onAllNodesWithText] is used for that case.
 *
 * Because the threshold depends on the screen height, each test pins an explicit screen size (via
 * [LocalConfiguration] and a matching [Modifier.size]) so the over/under-threshold relationship is
 * obvious and independent of the host device's default dimensions.
 */
@RunWith(RobolectricTestRunner::class)
class LeftAlignedScreenAdaptiveV3Test {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mainContent = MainContentParametersProvider()
    private val bottomContent = BottomContentParametersProvider()

    @Test
    fun displayTitleAndStickyBottomContent() {
        // Tall screen -> threshold = 900 / 3 = 300dp. A single primary button (~62dp) is well under
        // the threshold, so the bottom content stays sticky and is composed once (one node).
        with(composeTestRule) {
            setContent {
                val configuration = Configuration(LocalConfiguration.current).apply {
                    screenWidthDp = SCREEN_WIDTH_DP
                    screenHeightDp = TALL_SCREEN_HEIGHT_DP
                }
                CompositionLocalProvider(LocalConfiguration provides configuration) {
                    LeftAlignedScreenV3(
                        modifier = Modifier.size(
                            width = SCREEN_WIDTH_DP.dp,
                            height = TALL_SCREEN_HEIGHT_DP.dp,
                        ),
                        mainContentParameters = mainContent.onlyTitleContent,
                        bottomContentParameters = bottomContent.onlyPrimaryButton,
                        bottomContentBehaviour = BottomContentBehaviour.Adaptive,
                    )
                }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }

    @Test
    fun displayBodyWithoutTitle() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreenV3(
                    mainContentParameters = mainContent.onlyBodyContent,
                    bottomContentParameters = BottomContentParameters(),
                    bottomContentBehaviour = BottomContentBehaviour.Adaptive,
                )
            }

            onNodeWithText(TITLE_TEXT).assertIsNotDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
        }
    }

    @Test
    fun displayMainContentWithoutBottomContent() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreenV3(
                    mainContentParameters = mainContent.onlyTitleContent,
                    bottomContentParameters = BottomContentParameters(),
                    bottomContentBehaviour = BottomContentBehaviour.Adaptive,
                )
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun bottomContentOverThreshold_isDuplicatedAndMovedIntoBody() {
        // Short screen -> threshold = 200 / 3 ≈ 66dp. Supporting text + two buttons is far taller
        // than 66dp, so the bottom content is moved into the scrollable body AND measured by the
        // SubcomposeLayout, producing two nodes per element.
        with(composeTestRule) {
            setContent {
                val configuration = Configuration(LocalConfiguration.current).apply {
                    screenWidthDp = SCREEN_WIDTH_DP
                    screenHeightDp = SHORT_SCREEN_HEIGHT_DP
                }
                CompositionLocalProvider(LocalConfiguration provides configuration) {
                    LeftAlignedScreenV3(
                        modifier = Modifier.size(
                            width = SCREEN_WIDTH_DP.dp,
                            height = SHORT_SCREEN_HEIGHT_DP.dp,
                        ),
                        mainContentParameters = mainContent.onlyTitleContent,
                        bottomContentParameters = bottomContent.allStandardParams,
                        bottomContentBehaviour = BottomContentBehaviour.Adaptive,
                    )
                }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()

            onAllNodesWithText(SUPPORTING_TEXT).assertCountEquals(2)
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).assertCountEquals(2)
            onAllNodesWithText(SECONDARY_BUTTON_TEXT).assertCountEquals(2)

            // Both duplicate nodes share the same onClick lambda, so the first match is sufficient
            // to verify the button is wired up.
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).onFirst().assertHasClickAction()
        }
    }

    private companion object {
        const val SCREEN_WIDTH_DP = 320

        // 300dp threshold - comfortably above a single button, so small bottom content stays sticky.
        const val TALL_SCREEN_HEIGHT_DP = 900

        // ~66dp threshold - below supporting text + two buttons, forcing the over-threshold path.
        const val SHORT_SCREEN_HEIGHT_DP = 200
    }
}
