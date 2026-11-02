package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.PRIMARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SECONDARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SUPPORTING_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.IMAGE_DESCRIPTION
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.TITLE_TEXT
import uk.gov.android.ui.patterns.utils.matchers.ScrollableWithKeyboardMatchersV3.hasKeyboardScroll

/**
 * Tests for [LeftAlignedScreen].
 *
 * [BottomContentBehaviour.Fixed] and [BottomContentBehaviour.Adaptive] now share a single
 * `LeftAlignedLayout`; the only fork is whether the bottom content may move into the scrollable
 * body. The behaviour-agnostic cases assert the shared layout for both behaviours, while the
 * behaviour-specific cases cover the sticky-vs-moved fork.
 *
 * Threshold note: the Adaptive layout measures the bottom content with a SubcomposeLayout and
 * compares it against one third of the screen height ([LocalConfiguration].screenHeightDp):
 *  - Under the 1/3 threshold -> stays sticky, composed once (a single node per element).
 *  - Over the 1/3 threshold -> moved into the body AND measured by the SubcomposeLayout, so each
 *    element resolves to TWO nodes. A single-node matcher ([onNodeWithText]) would fail with
 *    "2 nodes found", so [onAllNodesWithText] is used for that case.
 *
 * Threshold-sensitive tests pin an explicit screen size (via [LocalConfiguration] and a matching
 * [Modifier.size]) so the over/under-threshold relationship is independent of the host device's
 * default dimensions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@RunWith(RobolectricTestRunner::class)
class LeftAlignedScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mainContent = MainContentParametersProvider()
    private val bottomContent = BottomContentParametersProvider()

    // --- Behaviour-agnostic: the shared layout behaves identically for both behaviours when the
    // bottom content is under (or at) the threshold. ---

    @Test
    fun displayBodyWithoutTitle_fixed() = displayBodyWithoutTitle(BottomContentBehaviour.Fixed)

    @Test
    fun displayBodyWithoutTitle_adaptive() =
        displayBodyWithoutTitle(BottomContentBehaviour.Adaptive)

    private fun displayBodyWithoutTitle(behaviour: BottomContentBehaviour) {
        with(composeTestRule) {
            setScreen(
                behaviour = behaviour,
                mainContentParameters = mainContent.onlyBodyContent,
                bottomContentParameters = BottomContentParameters(),
            )

            onNodeWithText(TITLE_TEXT).assertIsNotDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
        }
    }

    @Test
    fun displayMainContentWithoutBottomContent_fixed() =
        displayMainContentWithoutBottomContent(BottomContentBehaviour.Fixed)

    @Test
    fun displayMainContentWithoutBottomContent_adaptive() =
        displayMainContentWithoutBottomContent(BottomContentBehaviour.Adaptive)

    private fun displayMainContentWithoutBottomContent(behaviour: BottomContentBehaviour) {
        with(composeTestRule) {
            setScreen(
                behaviour = behaviour,
                mainContentParameters = mainContent.onlyTitleContent,
                bottomContentParameters = BottomContentParameters(),
            )

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }

    // --- Fixed-specific ---

    @Test
    fun fixed_displayTitleAndBottomContent() {
        with(composeTestRule) {
            setScreen(
                behaviour = BottomContentBehaviour.Fixed,
                mainContentParameters = mainContent.allStandardParams,
                bottomContentParameters = bottomContent.allStandardParams,
            )

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
            onNodeWithText(SECONDARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }

    @Test
    fun fixed_largeBottomContentStaysPinnedAndIsComposedOnce() {
        // Short screen -> an Adaptive layout would move this bottom content into the body (two
        // nodes per element). Fixed must keep it pinned as a sticky footer, composed once.
        with(composeTestRule) {
            setScreen(
                behaviour = BottomContentBehaviour.Fixed,
                mainContentParameters = mainContent.onlyTitleContent,
                bottomContentParameters = bottomContent.allStandardParams,
                screenHeightDp = SHORT_SCREEN_HEIGHT_DP,
            )

            // On this deliberately tiny screen the title may be pushed out of the viewport; its
            // existence is enough. The point of this test is that the footer is composed once.
            onNodeWithText(TITLE_TEXT).assertExists()
            onAllNodesWithText(SUPPORTING_TEXT).assertCountEquals(1)
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).assertCountEquals(1)
            onAllNodesWithText(SECONDARY_BUTTON_TEXT).assertCountEquals(1)
        }
    }

    // --- Adaptive-specific ---

    @Test
    fun adaptive_displayTitleAndStickyBottomContent() {
        // Tall screen -> threshold = 900 / 3 = 300dp. A single primary button (~62dp) is well under
        // the threshold, so the bottom content stays sticky and is composed once (one node).
        with(composeTestRule) {
            setScreen(
                behaviour = BottomContentBehaviour.Adaptive,
                mainContentParameters = mainContent.onlyTitleContent,
                bottomContentParameters = bottomContent.onlyPrimaryButton,
                screenHeightDp = TALL_SCREEN_HEIGHT_DP,
            )

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }

    @Test
    fun adaptive_bottomContentOverThreshold_isDuplicatedAndMovedIntoBody() {
        // Short screen -> threshold = 200 / 3 ≈ 66dp. Supporting text + two buttons is far taller
        // than 66dp, so the bottom content is moved into the scrollable body AND measured by the
        // SubcomposeLayout, producing two nodes per element.
        with(composeTestRule) {
            setScreen(
                behaviour = BottomContentBehaviour.Adaptive,
                mainContentParameters = mainContent.onlyTitleContent,
                bottomContentParameters = bottomContent.allStandardParams,
                screenHeightDp = SHORT_SCREEN_HEIGHT_DP,
            )

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()

            onAllNodesWithText(SUPPORTING_TEXT).assertCountEquals(2)
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).assertCountEquals(2)
            onAllNodesWithText(SECONDARY_BUTTON_TEXT).assertCountEquals(2)

            // Both duplicate nodes share the same onClick lambda, so the first match is sufficient
            // to verify the button is wired up.
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).onFirst().assertHasClickAction()
        }
    }

    // --- Public convenience overloads ---
    // The behaviour tests above all use the parameter-object overload; these cover the slot-based
    // and simple-data overloads and the screen-level background fallback.

    @Test
    fun slotOverload_displaysTopAppBarAboveBody() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreen(
                    topAppBar = { GdsHeading(TOP_APP_BAR_TEXT) },
                    title = { GdsHeading(TITLE_TEXT) },
                    body = { GdsHeading(BODY_SLOT_TEXT) },
                )
            }

            onNodeWithText(TOP_APP_BAR_TEXT).assertIsDisplayed()
            onNodeWithText(TITLE_TEXT).assertIsDisplayed()

            // The top app bar slot is rendered above the scrollable body.
            val topAppBarTop = onNodeWithText(TOP_APP_BAR_TEXT)
                .getUnclippedBoundsInRoot().top
            val titleTop = onNodeWithText(TITLE_TEXT)
                .getUnclippedBoundsInRoot().top
            assertTrue(topAppBarTop < titleTop)
        }
    }

    @Test
    fun slotOverload_topAppBarSlotReceivesScrollBehaviour() {
        var providedScrollBehaviour: TopAppBarScrollBehavior? = null
        with(composeTestRule) {
            setContent {
                LeftAlignedScreen(
                    topAppBar = { scrollBehaviour ->
                        providedScrollBehaviour = scrollBehaviour
                        GdsHeading(TOP_APP_BAR_TEXT)
                    },
                    title = { GdsHeading(TITLE_TEXT) },
                    body = { GdsHeading(BODY_SLOT_TEXT) },
                )
            }

            onNodeWithText(TOP_APP_BAR_TEXT).assertIsDisplayed()
        }
        // The screen owns a single scroll behaviour and hands it to the slot so the bar can react
        // to the body scrolling.
        assertTrue(providedScrollBehaviour != null)
    }

    @Test
    fun simpleDataOverload_displaysTopAppBar() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreen(
                    topAppBar = LeftAlignedScreenTopAppBar(title = TOP_APP_BAR_TEXT),
                    title = TITLE_TEXT,
                    body = persistentListOf(LeftAlignedScreenBody.Text(BODY_SLOT_TEXT)),
                )
            }

            onNodeWithText(TOP_APP_BAR_TEXT).assertIsDisplayed()
            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
        }
    }

    @Test
    fun slotOverload_displaysAllSlots() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreen(
                    title = { GdsHeading(TITLE_TEXT) },
                    body = { GdsHeading(BODY_SLOT_TEXT) },
                    supportingText = { _, _ ->
                        GdsSupportingText(SUPPORTING_TEXT)
                    },
                    primaryButton = {
                        GdsButton(
                            text = PRIMARY_BUTTON_TEXT,
                            buttonType = ButtonTypeV2.Primary(),
                            onClick = {},
                        )
                    },
                    secondaryButton = {
                        GdsButton(
                            text = SECONDARY_BUTTON_TEXT,
                            buttonType = ButtonTypeV2.Secondary(),
                            onClick = {},
                        )
                    },
                )
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_SLOT_TEXT).assertIsDisplayed()
            // These overloads always use the Adaptive default. On the host's default (small)
            // screen the bottom content exceeds the 1/3 threshold and is composed twice, so each
            // bottom element resolves to two nodes; onFirst() selects the visible one.
            onAllNodesWithText(SUPPORTING_TEXT).onFirst().assertIsDisplayed()
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).onFirst()
                .assertIsDisplayed()
                .assertHasClickAction()
            onAllNodesWithText(SECONDARY_BUTTON_TEXT).onFirst()
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }

    @Test
    fun bodyAlwaysHasKeyboardScroll() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreen(
                    title = { GdsHeading(TITLE_TEXT) },
                )
            }

            onNodeWithTag(LeftAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG)
                .assert(hasKeyboardScroll())
        }
    }

    @Test
    fun simpleDataOverload_displaysContentAndWiresPrimaryButton() {
        var clicked = false
        with(composeTestRule) {
            setContent {
                LeftAlignedScreen(
                    title = TITLE_TEXT,
                    body = persistentListOf(LeftAlignedScreenBody.Text(BODY_SLOT_TEXT)),
                    supportingText = SUPPORTING_TEXT,
                    primaryButton = LeftAlignedScreenButton(
                        text = PRIMARY_BUTTON_TEXT,
                        onClick = { clicked = true },
                    ),
                    secondaryButton = LeftAlignedScreenButton(
                        text = SECONDARY_BUTTON_TEXT,
                        onClick = {},
                    ),
                )
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_SLOT_TEXT).assertIsDisplayed()
            // Adaptive default -> bottom content may be composed twice on the default screen; take
            // the first node for the duplicable bottom-content elements.
            onAllNodesWithText(SUPPORTING_TEXT).onFirst().assertIsDisplayed()
            onAllNodesWithText(SECONDARY_BUTTON_TEXT).onFirst()
                .assertIsDisplayed()
                .assertHasClickAction()
            onAllNodesWithText(PRIMARY_BUTTON_TEXT).onFirst().performClick()
        }
        assertTrue(clicked)
    }

    @Test
    fun customBackgroundColorRenders() {
        // Covers the screen-level `backgroundColor.takeUnless { it == Color.Unspecified }` branch
        // in LeftAlignedLayout where a specified colour is used instead of the theme surface.
        with(composeTestRule) {
            setScreen(
                behaviour = BottomContentBehaviour.Adaptive,
                mainContentParameters = mainContent.allStandardParams,
                bottomContentParameters = bottomContent.onlyPrimaryButton,
                backgroundColor = Color.Red,
            )

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsDisplayed()
        }
    }

    /**
     * Composes [LeftAlignedScreen] with the given [behaviour].
     *
     * When [screenHeightDp] is provided the screen size is pinned (both via [LocalConfiguration]
     * and a matching [Modifier.size]) so threshold-sensitive tests are independent of the host
     * device's default dimensions. When it is null the host default size is used.
     */
    private fun ComposeContentTestRule.setScreen(
        behaviour: BottomContentBehaviour,
        mainContentParameters: MainContentParameters,
        bottomContentParameters: BottomContentParameters,
        screenHeightDp: Int? = null,
        backgroundColor: Color = Color.Unspecified,
    ) = setContent {
        if (screenHeightDp == null) {
            LeftAlignedScreen(
                title = mainContentParameters.titleContent,
                body = mainContentParameters.bodyContent,
                supportingText = bottomContentParameters.supportingText,
                primaryButton = bottomContentParameters.primaryButton,
                secondaryButton = bottomContentParameters.secondaryButton,
                bottomContentBehaviour = behaviour,
                backgroundColor = backgroundColor,
            )
        } else {
            val configuration = Configuration(LocalConfiguration.current).apply {
                screenWidthDp = SCREEN_WIDTH_DP
                this.screenHeightDp = screenHeightDp
            }
            CompositionLocalProvider(LocalConfiguration provides configuration) {
                LeftAlignedScreen(
                    modifier = Modifier.size(
                        width = SCREEN_WIDTH_DP.dp,
                        height = screenHeightDp.dp,
                    ),
                    title = mainContentParameters.titleContent,
                    body = mainContentParameters.bodyContent,
                    supportingText = bottomContentParameters.supportingText,
                    primaryButton = bottomContentParameters.primaryButton,
                    secondaryButton = bottomContentParameters.secondaryButton,
                    bottomContentBehaviour = behaviour,
                    backgroundColor = backgroundColor,
                )
            }
        }
    }

    private companion object {
        const val SCREEN_WIDTH_DP = 320

        const val BODY_SLOT_TEXT = "Body slot content"

        const val TOP_APP_BAR_TEXT = "Top app bar content"

        // 300dp threshold - comfortably above a single button, so small bottom content stays sticky.
        const val TALL_SCREEN_HEIGHT_DP = 900

        // ~66dp threshold - below supporting text + two buttons, forcing the over-threshold path.
        const val SHORT_SCREEN_HEIGHT_DP = 200
    }
}
