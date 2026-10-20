package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.PRIMARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SECONDARY_BUTTON_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentParametersProvider.Companion.SUPPORTING_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.IMAGE_DESCRIPTION
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.TITLE_TEXT

@RunWith(RobolectricTestRunner::class)
class LeftAlignedScreenFixedV3Test {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mainContent = MainContentParametersProvider()
    private val bottomContent = BottomContentParametersProvider()

    @Test
    fun displayTitleAndBottomContent() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreenV3(
                    mainContentParameters = mainContent.allStandardParams,
                    bottomContentParameters = bottomContent.allStandardParams,
                    bottomContentBehaviour = BottomContentBehaviour.Fixed,
                )
            }

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
    fun displayBodyWithoutTitle() {
        with(composeTestRule) {
            setContent {
                LeftAlignedScreenV3(
                    mainContentParameters = mainContent.onlyBodyContent,
                    bottomContentParameters = BottomContentParameters(),
                    bottomContentBehaviour = BottomContentBehaviour.Fixed,
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
                    bottomContentBehaviour = BottomContentBehaviour.Fixed,
                )
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(SUPPORTING_TEXT).assertIsNotDisplayed()
            onNodeWithText(PRIMARY_BUTTON_TEXT).assertIsNotDisplayed()
            onNodeWithText(SECONDARY_BUTTON_TEXT).assertIsNotDisplayed()
        }
    }
}
