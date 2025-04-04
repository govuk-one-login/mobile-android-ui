package uk.gov.android.ui.componentsv2.heading

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@RunWith(RobolectricTestRunner::class)
class GdsHeadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDefaultHeadingDisplayedCorrectly() {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsHeading(
                        text = "Default Heading",
                    )
                }
            }
            onNodeWithText("Default Heading").apply {
                assertIsDisplayed()
            }
        }
    }

    @Test
    fun testTitleOneHeadingDisplayedCorrectly() {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsHeading(
                        text = "Title1 Heading",
                        style = GdsHeadingStyle.Title1,
                    )
                }
            }
            onNodeWithText("Title1 Heading").apply {
                assertIsDisplayed()
            }
        }
    }

    @Test
    fun testTitleTwoHeadingDisplayedCorrectly() {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsHeading(
                        text = "Title2 Heading",
                        style = GdsHeadingStyle.Title2,
                    )
                }
            }
            onNodeWithText("Title2 Heading").apply {
                assertIsDisplayed()
            }
        }
    }

    @Test
    fun testTitleThreeHeadingDisplayedCorrectly() {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsHeading(
                        text = "Title3 Heading",
                        style = GdsHeadingStyle.Title2,
                    )
                }
            }
            onNodeWithText("Title3 Heading").apply {
                assertIsDisplayed()
            }
        }
    }

    @Test
    fun testCustomHeadingColors() {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsHeading(
                        text = "Custom Heading",
                        style = GdsHeadingStyle.Title2,
                    )
                }
            }
            onNodeWithText("Custom Heading").apply {
                assertIsDisplayed()
            }
        }
    }
}
