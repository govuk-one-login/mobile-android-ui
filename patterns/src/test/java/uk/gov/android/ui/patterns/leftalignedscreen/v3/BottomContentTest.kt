package uk.gov.android.ui.patterns.leftalignedscreen.v3

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
}
