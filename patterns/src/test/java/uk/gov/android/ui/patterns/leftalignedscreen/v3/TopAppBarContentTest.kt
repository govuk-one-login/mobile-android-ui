package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBarDefaults
import uk.gov.android.ui.patterns.leftalignedscreen.v3.TopAppBarContentParametersProvider.Companion.TITLE

@OptIn(ExperimentalMaterial3Api::class)
@RunWith(RobolectricTestRunner::class)
class TopAppBarContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val content = TopAppBarContentParametersProvider()

    @Test
    fun displayTitle() {
        with(composeTestRule) {
            setContent {
                TopAppBarContentV3(
                    parameters = content.onlyTitle,
                    scrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour(),
                )
            }

            onNodeWithText(TITLE).assertIsDisplayed()
        }
    }

    @Test
    fun displayWithoutNavigation() {
        with(composeTestRule) {
            setContent {
                TopAppBarContentV3(
                    parameters = content.noNavigation,
                    scrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour(),
                )
            }

            onNodeWithText(TITLE).assertIsDisplayed()
        }
    }

    @Test
    fun displayCentreAligned() {
        with(composeTestRule) {
            setContent {
                TopAppBarContentV3(
                    parameters = content.centreAligned,
                    scrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour(),
                )
            }

            onNodeWithText(TITLE).assertIsDisplayed()
        }
    }

    // Documents the no-op case: a null slot still renders (an empty bar) without crashing.
    @Test
    fun rendersEmptyWhenSlotNull() {
        with(composeTestRule) {
            setContent {
                TopAppBarContentV3(
                    parameters = TopAppBarContentParameters(),
                    scrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour(),
                )
            }

            onNodeWithText(TITLE).assertIsNotDisplayed()
        }
    }
}
