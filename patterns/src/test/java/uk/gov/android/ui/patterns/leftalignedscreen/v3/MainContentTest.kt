package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.BODY_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.TITLE_TEXT

@RunWith(RobolectricTestRunner::class)
class MainContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val content = MainContentParametersProvider()

    @Test
    fun displayOnlyTitle() {
        with(composeTestRule) {
            setContent {
                Column { MainContentV2(content.onlyTitleContent) }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayOnlyBody() {
        with(composeTestRule) {
            setContent {
                Column { MainContentV2(content.onlyBodyContent) }
            }

            onNodeWithText(TITLE_TEXT).assertIsNotDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
        }
    }

    @Test
    fun displayTitleAndBody() {
        with(composeTestRule) {
            setContent {
                Column { MainContentV2(content.allStandardParams) }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
        }
    }
}
