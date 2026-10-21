package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.PRIMARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SECONDARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SUPPORTING_TEXT

@RunWith(RobolectricTestRunner::class)
class BottomContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val content = BottomContentParametersProvider()

    @Test
    fun displaySupportText() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(content.onlySupportingText)
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayPrimaryButton() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(content.onlyPrimaryButton)
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun displaySecondaryButton() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(content.onlySecondaryButton)
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsDisplayed()
        }
    }

    @Test
    fun displayAllOptions() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(content.allStandardParams)
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsDisplayed()
        }
    }

    // Covers the `backgroundColor.takeUnless { it == Color.Unspecified }` branch where a specified
    // colour is used instead of the MaterialTheme surface fallback.
    @Test
    fun rendersWithCustomBackgroundColor() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = content.allStandardParams,
                    config = BottomContentConfig(backgroundColor = Color.Red),
                )
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsDisplayed()
        }
    }

    // Covers the `top = if (isSticky) verticalPadding else 0.dp` branch where isSticky is false.
    @Test
    fun rendersWhenNotSticky() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = content.allStandardParams,
                    isSticky = false,
                )
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsDisplayed()
        }
    }

    // Exercises the `with(config)` destructuring / padding application when a non-default config is
    // passed into the composable (BottomContentConfigTest only tests the data class in isolation).
    @Test
    fun rendersWithCustomConfigPadding() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = content.allStandardParams,
                    config = BottomContentConfig(
                        backgroundColor = Color.Blue,
                        horizontalPadding = 24.dp,
                        verticalPadding = 32.dp,
                    ),
                    isSticky = false,
                )
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsDisplayed()
        }
    }

    // Documents the no-op case: all slots null still renders (Surface/Column) without crashing and
    // shows no content.
    @Test
    fun rendersEmptyWhenAllSlotsNull() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(BottomContentParameters())
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }
}
