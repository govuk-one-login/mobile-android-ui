package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.PRIMARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SECONDARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SUPPORTING_TEXT

@RunWith(RobolectricTestRunner::class)
class BottomContentParametersTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayDefaultSupportText() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = BottomContentParameters.generateDefault(
                        supportingText = SUPPORTING_TEXT,
                        primaryButton = null,
                        secondaryButton = null,
                    ),
                )
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayDefaultPrimaryButton() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = BottomContentParameters.generateDefault(
                        supportingText = null,
                        primaryButton = LeftAlignedScreenButton(
                            text = PRIMARY_BUTTON_TEXT,
                            onClick = {},
                        ),
                        secondaryButton = null,
                    ),
                )
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayDefaultSecondaryButton() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = BottomContentParameters.generateDefault(
                        supportingText = null,
                        primaryButton = null,
                        secondaryButton = LeftAlignedScreenButton(
                            text = SECONDARY_BUTTON_TEXT,
                            onClick = {},
                        ),
                    ),
                )
            }
            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }

    @Test
    fun displayAllDefaultOptions() {
        with(composeTestRule) {
            setContent {
                BottomContentV2(
                    parameters = BottomContentParameters.generateDefault(
                        supportingText = SUPPORTING_TEXT,
                        primaryButton = LeftAlignedScreenButton(
                            text = PRIMARY_BUTTON_TEXT,
                            onClick = {},
                        ),
                        secondaryButton = LeftAlignedScreenButton(
                            text = SECONDARY_BUTTON_TEXT,
                            onClick = {},
                        ),
                    ),
                )
            }

            onNodeWithText(SUPPORTING_TEXT).assertIsDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
            onNodeWithText(SECONDARY_BUTTON_TEXT)
                .assertIsDisplayed()
                .assertHasClickAction()
        }
    }
}
