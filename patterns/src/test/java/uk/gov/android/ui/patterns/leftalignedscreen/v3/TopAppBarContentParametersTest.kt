package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBarDefaults
import uk.gov.android.ui.patterns.leftalignedscreen.v3.TopAppBarContentParameters.Companion.generateDefault
import uk.gov.android.ui.patterns.testutils.TestUtils

@OptIn(ExperimentalMaterial3Api::class)
@RunWith(RobolectricTestRunner::class)
class TopAppBarContentParametersTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // A null model produces an empty parameters object (null slot), mirroring the simple-data
    // entry point passing `topAppBar = null`.
    @Test
    fun generateDefaultWithNullModelProducesNullSlot() {
        var parameters: TopAppBarContentParameters? = null
        composeTestRule.setContent {
            parameters = generateDefault(null)
        }
        assertNull(parameters?.topAppBar)
    }

    @Test
    fun generateDefaultDisplaysTitle() {
        with(composeTestRule) {
            setContent {
                generateDefault(LeftAlignedScreenTopAppBar(title = TITLE)).topAppBar
                    ?.invoke(GdsTopAppBarDefaults.scrollBehaviour())
            }

            onNodeWithText(TITLE).assertIsDisplayed()
        }
    }

    // Covers the `navigationButton ?: defaultBackContent().takeIf { showDefaultNavigation }` branch
    // where the GDS default back button is resolved.
    @Test
    fun generateDefaultShowsDefaultBackNavigation() {
        val backDescription = TestUtils.getString(R.string.back_icon_button)
        with(composeTestRule) {
            setContent {
                generateDefault(LeftAlignedScreenTopAppBar(title = TITLE)).topAppBar
                    ?.invoke(GdsTopAppBarDefaults.scrollBehaviour())
            }

            onNodeWithContentDescription(backDescription).assertIsDisplayed()
        }
    }

    // Covers the branch where showDefaultNavigation is false, so no navigation icon is resolved.
    @Test
    fun generateDefaultOmitsNavigationWhenDisabled() {
        val backDescription = TestUtils.getString(R.string.back_icon_button)
        with(composeTestRule) {
            setContent {
                generateDefault(
                    LeftAlignedScreenTopAppBar(
                        title = TITLE,
                        showDefaultNavigation = false,
                    ),
                ).topAppBar?.invoke(GdsTopAppBarDefaults.scrollBehaviour())
            }

            onNodeWithText(TITLE).assertIsDisplayed()
            onNodeWithContentDescription(backDescription).assertIsNotDisplayed()
        }
    }

    // Covers the navigation click plumbing: tapping the resolved back button invokes onNavigationClick.
    @Test
    fun generateDefaultWiresNavigationClick() {
        var clicked = false
        val backDescription = TestUtils.getString(R.string.back_icon_button)
        with(composeTestRule) {
            setContent {
                generateDefault(
                    LeftAlignedScreenTopAppBar(
                        title = TITLE,
                        onNavigationClick = { clicked = true },
                    ),
                ).topAppBar?.invoke(GdsTopAppBarDefaults.scrollBehaviour())
            }

            onNodeWithContentDescription(backDescription).performClick()
        }
        assert(clicked)
    }

    private companion object {
        const val TITLE = "Top app bar"
    }
}
