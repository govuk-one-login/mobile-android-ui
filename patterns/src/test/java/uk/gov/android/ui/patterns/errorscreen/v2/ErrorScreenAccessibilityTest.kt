package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.printToString
import androidx.compose.ui.test.swipeUp
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenTitleTestTag.ERROR_SCREEN_TITLE_TEST_TAG
import uk.gov.android.ui.patterns.utils.BDD.And
import uk.gov.android.ui.patterns.utils.BDD.Given
import uk.gov.android.ui.patterns.utils.BDD.Then
import uk.gov.android.ui.patterns.utils.BDD.When
import uk.gov.android.ui.patterns.utils.TestUtils.getString
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@RunWith(RobolectricTestRunner::class)
class ErrorScreenAccessibilityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val singleLineText = "Body single line"
    private val primaryButtonText = "Primary Button"
    private val secondaryButtonText = "Secondary Button"
    private val tertiaryButtonText = "Tertiary Button"

    private lateinit var mandatoryIcon: ErrorScreenIcon
    private lateinit var mandatoryTitle: String

    @Test
    fun `test accessibility for merged element - icon and title heading`() = with(composeTestRule) {
        Given("an error screen with an icon and a title") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with the provided icon and title") {
            setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
                )
            }
        }

        Then("the icon and title should merged into one accessible element") {
            val hasChildWithTitleHeading = hasAnyChild(hasText(mandatoryTitle).and(isHeading()))
            val hasChildWithIcon =
                hasAnyChild(hasContentDescription(getString(mandatoryIcon.description)))
            onNode(matcher = hasChildWithTitleHeading and hasChildWithIcon)
                .assertIsDisplayed()
        }

        And("the merged element should have a meaningful content description") {
            val description = "[${getString(mandatoryIcon.description)}, $mandatoryTitle]"
            val mergedSemantics = onRoot(useUnmergedTree = false).printToString()
            assertTrue(mergedSemantics.contains(description))
        }
    }

    @Suppress("LongMethod")
    @Test
    fun `test accessibility traversal`() = with(composeTestRule) {
        Given("an error screen with a content") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }
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
                    item {
                        Text(singleLineText)
                    }
                },
                primaryButton = {
                    PrimaryButton(
                        ErrorScreenButton(
                            text = primaryButtonText,
                            onClick = {},
                        ),
                    )
                },
                secondaryButton = {
                    SecondaryButton(
                        ErrorScreenButton(
                            text = secondaryButtonText,
                            onClick = {},
                        ),
                    )
                },
                tertiaryButton = {
                    SecondaryButton(
                        ErrorScreenButton(
                            text = tertiaryButtonText,
                            onClick = {},
                        ),
                    )
                },
            )
        }

        And("the lazy column content is composed") {
            onNodeWithTag(ERROR_SCREEN_TITLE_TEST_TAG)
                .performTouchInput { swipeUp() }
                .onChildren()
                .onLast()
        }

        Then("the semantics nodes are in order") {
            val semanticsNodes = onAllNodes(isRoot().not()).fetchSemanticsNodes()

            // root node is skipped due to no accessibility label (text or content description)
            assertEquals(ERROR_SCREEN_TITLE_TEST_TAG, accessibilityLabel(semanticsNodes[1]))

            // expected order:body, 3 buttons
            assertEquals("[Body single line]", accessibilityLabel(semanticsNodes[2]))
            assertEquals("[Primary Button]", accessibilityLabel(semanticsNodes[3]))
            assertEquals("[Secondary Button]", accessibilityLabel(semanticsNodes[4]))
            assertEquals("[Tertiary Button]", accessibilityLabel(semanticsNodes[5]))
        }
    }

    private fun accessibilityLabel(node: SemanticsNode): String {
        val label = node.config.getOrNull(SemanticsProperties.TestTag)
            ?: node.config.getOrNull(SemanticsProperties.ContentDescription)
            ?: node.config.getOrNull(SemanticsProperties.Text)
        return label?.toString().orEmpty()
    }

    @Test
    fun `title has content description and heading role`() {
        Given("an error screen with an icon and a title") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with the provided icon and title") {
            composeTestRule.setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
                )
            }
        }

        Then("the title should have a meaningful content description and heading role") {
            composeTestRule
                .onNodeWithTag(ERROR_SCREEN_TITLE_TEST_TAG)
                .assertContentDescriptionContains(mandatoryTitle)
                .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
        }
    }
}
