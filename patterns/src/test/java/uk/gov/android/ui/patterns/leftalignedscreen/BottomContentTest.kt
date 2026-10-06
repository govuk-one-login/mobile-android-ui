package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BottomContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val contentList = BottomContentParametersProvider().values.toImmutableList()

    @Test
    fun displaySupportText() {
        with(composeTestRule) {
            setContent {
                BottomContent(contentList[0])
            }

            onNodeWithText("Check if your passport", substring = true).assertIsDisplayed()
            onNodeWithText("Primary Button").assertIsNotDisplayed()
            onNodeWithText("Secondary Button").assertIsNotDisplayed()
        }
    }

    @Test
    fun displayPrimaryButton() {
        with(composeTestRule) {
            setContent {
                BottomContent(contentList[1])
            }

            onNodeWithText("Check if your passport", substring = true).assertIsNotDisplayed()
            onNodeWithText("Primary Button").assertIsDisplayed()
            onNodeWithText("Secondary Button").assertIsNotDisplayed()
        }
    }

    @Test
    fun displaySecondaryButton() {
        with(composeTestRule) {
            setContent {
                BottomContent(contentList[2])
            }

            onNodeWithText("Check if your passport", substring = true).assertIsNotDisplayed()
            onNodeWithText("Primary Button").assertIsNotDisplayed()
            onNodeWithText("Secondary Button").assertIsDisplayed()
        }
    }

    @Test
    fun displayAllOptions() {
        with(composeTestRule) {
            setContent {
                BottomContent(contentList[5])
            }

            onNodeWithText("Check if your passport", substring = true).assertIsDisplayed()
            onNodeWithText("Primary Button").assertIsDisplayed()
            onNodeWithText("Secondary Button").assertIsDisplayed()
        }
    }
}
