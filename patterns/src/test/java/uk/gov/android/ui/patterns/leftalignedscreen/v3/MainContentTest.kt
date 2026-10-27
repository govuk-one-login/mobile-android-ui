package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
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
                Column { MainContentV3(content.onlyTitleContent) }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayOnlyBody() {
        with(composeTestRule) {
            setContent {
                Column { MainContentV3(content.onlyBodyContent) }
            }

            onNodeWithText(TITLE_TEXT).assertIsNotDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
        }
    }

    @Test
    fun displayTitleAndBody() {
        with(composeTestRule) {
            setContent {
                Column { MainContentV3(content.allStandardParams) }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
        }
    }

    // Exercises the `with(config)` padding path when a non-default MainContentConfig is passed into
    // the composable (MainContentConfigTest only tests the data class in isolation).
    @Test
    fun rendersWithCustomConfig() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV3(
                        parameters = content.allStandardParams,
                        config = MainContentConfig(
                            horizontalPadding = 24.dp,
                            verticalPadding = 32.dp,
                        ),
                    )
                }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
        }
    }

    // Covers the `bottomContent?.let { item { bottom() } }` branch, which is never exercised
    // elsewhere (the bottomContent parameter is always null in other tests).
    @Test
    fun appendsBottomContentWhenProvided() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV3(
                        parameters = content.allStandardParams,
                        bottomContent = { Text(BOTTOM_CONTENT_TEXT) },
                    )
                }
            }

            onNodeWithText(TITLE_TEXT).assertIsDisplayed()
            onNodeWithText(BOTTOM_CONTENT_TEXT).assertIsDisplayed()
        }
    }

    // Documents the no-op case: both slots null still renders the LazyColumn without crashing.
    @Test
    fun rendersEmptyWhenAllSlotsNull() {
        with(composeTestRule) {
            setContent {
                Column { MainContentV3(MainContentParameters()) }
            }

            onNodeWithText(TITLE_TEXT).assertIsNotDisplayed()
            onNodeWithText(BODY_TEXT).assertIsNotDisplayed()
        }
    }

    private companion object {
        const val BOTTOM_CONTENT_TEXT = "Bottom content"
    }
}
