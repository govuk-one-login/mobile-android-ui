package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.testutils.BDD.Given
import uk.gov.android.ui.patterns.testutils.BDD.Then
import uk.gov.android.ui.patterns.testutils.BDD.When
import uk.gov.android.ui.patterns.testutils.TestUtils.getString

@RunWith(RobolectricTestRunner::class)
class ErrorScreenParameterTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val singleLineText = "Body single line"
    private val primaryButtonText = "Primary Button"
    private val secondaryButtonText = "Secondary Button"
    private val tertiaryButtonText = "Tertiary Button"

    private lateinit var mandatoryIcon: ErrorScreenIcon
    private lateinit var mandatoryTitle: String
    private lateinit var customModifier: Modifier

    @Test
    fun `test mandatory parameters - title and icon`() = with(composeTestRule) {
        Given("a title and an icon are available") {
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

        Then("the icon and title should be displayed on the screen") {
            onNodeWithText(mandatoryTitle).assertIsDisplayed()
            onNodeWithContentDescription(getString(mandatoryIcon.description)).assertIsDisplayed()
        }
    }

    @Test
    fun `test default parameters - when no modifier provided`() = with(composeTestRule) {
        Given("a screen without a custom modifier") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with default modifier") {
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

        Then("the default modifier should be applied") {
            onNodeWithText(mandatoryTitle).assertIsDisplayed()
            onNodeWithContentDescription(getString(mandatoryIcon.description)).assertIsDisplayed()
        }
    }

    @Test
    fun `test default parameters - when custom modifier provided`() = with(composeTestRule) {
        Given("a screen with a custom modifier") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            customModifier = Modifier.fillMaxSize(fraction = 0f)
        }

        When("the screen is composed with custom modifier") {
            setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
                    modifier = customModifier,
                )
            }
        }

        Then("the default modifier should be applied") {
            onNodeWithText(mandatoryTitle).assertIsNotDisplayed()
            onNodeWithContentDescription(getString(mandatoryIcon.description)).assertIsNotDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when no body provided`() = with(composeTestRule) {
        Given("a screen with no body provided (null)") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with no body") {
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

        Then("the body should be not displayed") {
            onNodeWithText(singleLineText).assertIsNotDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when body provided`() = with(composeTestRule) {
        Given("a screen with a body provided") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with the body") {
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
                )
            }
        }

        Then("the body should be displayed") {
            onNodeWithText(singleLineText).assertIsDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when no primary button provided`() = with(composeTestRule) {
        Given("a screen with no primary button provided (null)") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with no primary button") {
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

        Then("the primary button should be not displayed") {
            onNodeWithText(primaryButtonText).assertIsNotDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when primary button provided`() = with(composeTestRule) {
        Given("a screen with a primary button provided") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with the primary button") {
            setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
                    primaryButton = {
                        PrimaryButton(
                            ErrorScreenButton(
                                text = primaryButtonText,
                                onClick = {},
                            ),
                        )
                    },
                )
            }
        }

        Then("the primary button should be displayed") {
            onNodeWithText(primaryButtonText).assertIsDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when no secondary button provided`() = with(composeTestRule) {
        Given("a screen with no secondary button provided (null)") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with no secondary button") {
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

        Then("the secondary button should be not displayed") {
            onNodeWithText(secondaryButtonText).assertIsNotDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when secondary button provided`() = with(composeTestRule) {
        Given("a screen with a secondary button provided") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with the secondary button") {
            setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
                    secondaryButton = {
                        SecondaryButton(
                            ErrorScreenButton(
                                text = secondaryButtonText,
                                onClick = {},
                            ),
                        )
                    },
                )
            }
        }

        Then("the secondary button should be displayed") {
            onNodeWithText(secondaryButtonText).assertIsDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when no tertiary button provided`() = with(composeTestRule) {
        Given("a screen with no tertiary button provided (null)") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with no tertiary button") {
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

        Then("the tertiary button should be not displayed") {
            onNodeWithText(tertiaryButtonText).assertIsNotDisplayed()
        }
    }

    @Test
    fun `test optional parameters - when tertiary button provided`() = with(composeTestRule) {
        Given("a screen with a tertiary button provided") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
        }

        When("the screen is composed with the tertiary button") {
            setContent {
                ErrorScreen(
                    icon = {
                        GdsIcon(
                            image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                            contentDescription = "Error",
                        )
                    },
                    title = { GdsHeading(mandatoryTitle) },
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
        }

        Then("the tertiary button should be displayed") {
            onNodeWithText(tertiaryButtonText).assertIsDisplayed()
        }
    }

    @Test
    fun `lazy column has semantic collection info with rows and columns set to zero`() {
        composeTestRule.setContent {
            ErrorScreen(
                title = { },
                icon = { },
            )
        }

        composeTestRule
            .onNodeWithTag(ErrorScreenTitleTestTag.ERROR_BODY_LAZY_COLUMN_TEST_TAG)
            .assert(
                SemanticsMatcher.keyIsDefined(SemanticsProperties.CollectionInfo),
            )
            .assert(
                SemanticsMatcher("CollectionInfo has rowCount=0 and columnCount=0") { node ->
                    val collectionInfo = node.config.getOrNull(SemanticsProperties.CollectionInfo)
                    collectionInfo?.rowCount == 0 && collectionInfo.columnCount == 0
                },
            )
    }
}
