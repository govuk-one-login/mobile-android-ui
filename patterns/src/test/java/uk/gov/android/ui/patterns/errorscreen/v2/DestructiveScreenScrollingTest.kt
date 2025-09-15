package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenIcon
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenTitleTestTag.ERROR_BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.utils.BDD.And
import uk.gov.android.ui.patterns.utils.BDD.Given
import uk.gov.android.ui.patterns.utils.BDD.Then
import uk.gov.android.ui.patterns.utils.BDD.When
import uk.gov.android.ui.patterns.utils.TestUtils.loremIpsum
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@RunWith(RobolectricTestRunner::class)
class DestructiveScreenScrollingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mandatoryIcon: ErrorScreenIcon
    private lateinit var mandatoryTitle: String

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun `test scrolling behaviour in the body content`() = with(composeTestRule) {
        Given("a body content with multiple lines and paragraphs") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }
        val topLine = ErrorScreenBodyContent.Text("Top single line")
        val paragraph = ErrorScreenBodyContent.Text(loremIpsum(50))
        val bottomLine = ErrorScreenBodyContent.Text("Bottom single line")
        val body = persistentListOf(topLine, paragraph, paragraph, paragraph, paragraph, bottomLine)

        When("the screen is composed with the provided body") {
            setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
                    body = {
                        toBodyContent(body, spacingDouble)
                    },
                )
            }
        }

        Then("the top line should be visible") {
            onNodeWithText(topLine.bodyText).assertIsDisplayed()
        }

        And("the bottom line should not be visible") {
            onNodeWithText(bottomLine.bodyText).assertIsNotDisplayed()
        }

        When("body content is scrolled to the bottom") {
            onNodeWithTag(ERROR_BODY_LAZY_COLUMN_TEST_TAG)
                .performTouchInput { swipeUp() }
                .onChildren()
                .onLast()
        }

        Then("the bottom line should be visible") {
            onNodeWithText(bottomLine.bodyText).assertIsDisplayed()
        }

        And("the top line should not be visible") {
            onNodeWithText(topLine.bodyText).assertIsNotDisplayed()
        }

        When("body content is scrolled back to the top") {
            onNodeWithTag(ERROR_BODY_LAZY_COLUMN_TEST_TAG)
                .performTouchInput { swipeDown() }
                .onChildren()
                .onFirst()
        }

        Then("the top line should be visible") {
            onNodeWithText(topLine.bodyText).assertIsDisplayed()
        }

        And("the bottom line should not be visible") {
            onNodeWithText(bottomLine.bodyText).assertIsNotDisplayed()
        }
    }
}
