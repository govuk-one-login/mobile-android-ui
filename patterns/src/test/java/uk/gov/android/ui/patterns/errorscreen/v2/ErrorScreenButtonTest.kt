package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.utils.BDD.And
import uk.gov.android.ui.patterns.utils.BDD.Given
import uk.gov.android.ui.patterns.utils.BDD.Then
import uk.gov.android.ui.patterns.utils.BDD.When
import uk.gov.android.ui.patterns.utils.TestUtils.getString
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI
import uk.gov.android.ui.componentsv2.R as componentsR

@OptIn(UnstableDesignSystemAPI::class)
@RunWith(RobolectricTestRunner::class)
@Suppress("LargeClass")
class ErrorScreenButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mandatoryIcon: ErrorScreenIcon
    private lateinit var mandatoryTitle: String
    private lateinit var mandatoryButtonText: String
    private lateinit var mandatoryButtonOnClick: () -> Unit

    @Test
    fun `test primary button mandatory parameters - text and action`() = with(composeTestRule) {
        var actionPerformed = false

        Given("a button with mandatory text and an action") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            mandatoryButtonText = "Primary button"
            mandatoryButtonOnClick = { actionPerformed = true }
        }

        When("the screen is composed with the button") {
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
                                text = mandatoryButtonText,
                                onClick = mandatoryButtonOnClick,
                            ),
                        )
                    },
                )
            }
        }

        And("the button is clicked") {
            onNodeWithText(mandatoryButtonText)
                .assertIsEnabled()
                .assertHasClickAction()
                .performClick()
        }

        Then("the button should be displayed") {
            onNodeWithText(mandatoryButtonText).assertIsDisplayed()
        }

        And("the action should be performed") {
            assertTrue(actionPerformed)
        }
    }

    @Test
    fun `test primary button default parameters - when no show icon provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button without a custom show icon value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Primary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText)
                    .assertIsEnabled()
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the default show icon value should be applied") {
                onNodeWithText(mandatoryButtonText).assertIsDisplayed()
                onNodeWithText(getString(componentsR.string.opens_in_external_browser))
                    .assertIsNotDisplayed()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test primary button default parameters - when show icon provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button with a custom show icon value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Primary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                    showIcon = true,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertIsEnabled()
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the custom show icon value should be applied") {
                onNodeWithText(mandatoryButtonText, substring = true).assertIsDisplayed()
                onNodeWithContentDescription(getString(componentsR.string.opens_in_external_browser))
                    .assertIsDisplayed()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test primary button default parameters - when no enabled provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button without a custom enabled value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Primary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText)
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the default enabled value should be applied") {
                onNodeWithText(mandatoryButtonText)
                    .assertIsDisplayed()
                    .assertIsEnabled()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test primary button default parameters - when enabled provided`() = with(composeTestRule) {
        var actionPerformed = false

        Given("a button with a custom enabled value") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            mandatoryButtonText = "Primary button"
            mandatoryButtonOnClick = { actionPerformed = true }
        }

        When("the screen is composed with the button") {
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
                                text = mandatoryButtonText,
                                onClick = mandatoryButtonOnClick,
                                enabled = false,
                            ),
                        )
                    },
                )
            }
        }

        And("the default button is clicked") {
            onNodeWithText(mandatoryButtonText, substring = true)
                .assertHasClickAction()
                .performClick()
        }

        Then("the custom enabled value should be applied") {
            onNodeWithText(mandatoryButtonText, substring = true)
                .assertIsDisplayed()
                .assertIsNotEnabled()
        }

        And("the action should not be performed") {
            assertFalse(actionPerformed)
        }
    }

    @Test
    fun `test secondary button mandatory parameters - text and action`() = with(composeTestRule) {
        var actionPerformed = false

        Given("a button with mandatory text and an action") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            mandatoryButtonText = "Secondary button"
            mandatoryButtonOnClick = { actionPerformed = true }
        }

        When("the screen is composed with the button") {
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
                                text = mandatoryButtonText,
                                onClick = mandatoryButtonOnClick,
                            ),
                        )
                    },
                )
            }
        }

        And("the button is clicked") {
            onNodeWithText(mandatoryButtonText)
                .assertIsEnabled()
                .assertHasClickAction()
                .performClick()
        }

        Then("the button should be displayed") {
            onNodeWithText(mandatoryButtonText).assertIsDisplayed()
        }

        And("the action should be performed") {
            assertTrue(actionPerformed)
        }
    }

    @Test
    fun `test secondary button default parameters - when no show icon provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button without a custom show icon value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Secondary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText)
                    .assertIsEnabled()
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the default show icon value should be applied") {
                onNodeWithText(mandatoryButtonText).assertIsDisplayed()
                onNodeWithText(getString(componentsR.string.opens_in_external_browser))
                    .assertIsNotDisplayed()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test secondary button default parameters - when show icon provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button with a custom show icon value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Secondary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                    showIcon = true,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertIsEnabled()
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the custom show icon value should be applied") {
                onNodeWithText(mandatoryButtonText, substring = true).assertIsDisplayed()
                onNodeWithContentDescription(getString(componentsR.string.opens_in_external_browser))
                    .assertIsDisplayed()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test secondary button default parameters - when no enabled provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button without a custom enabled value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Secondary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText)
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the default enabled value should be applied") {
                onNodeWithText(mandatoryButtonText)
                    .assertIsDisplayed()
                    .assertIsEnabled()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test secondary button default parameters - when enabled provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button with a custom enabled value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Secondary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                    enabled = false,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the custom enabled value should be applied") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertIsDisplayed()
                    .assertIsNotEnabled()
            }

            And("the action should not be performed") {
                assertFalse(actionPerformed)
            }
        }

    @Test
    fun `test tertiary button mandatory parameters - text and action`() = with(composeTestRule) {
        var actionPerformed = false

        Given("a button with mandatory text and an action") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            mandatoryButtonText = "Tertiary button"
            mandatoryButtonOnClick = { actionPerformed = true }
        }

        When("the screen is composed with the button") {
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
                                text = mandatoryButtonText,
                                onClick = mandatoryButtonOnClick,
                            ),
                        )
                    },
                )
            }
        }

        And("the button is clicked") {
            onNodeWithText(mandatoryButtonText)
                .assertIsEnabled()
                .assertHasClickAction()
                .performClick()
        }

        Then("the button should be displayed") {
            onNodeWithText(mandatoryButtonText).assertIsDisplayed()
        }

        And("the action should be performed") {
            assertTrue(actionPerformed)
        }
    }

    @Test
    fun `test tertiary button default parameters - when no show icon provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button without a custom show icon value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Tertiary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText)
                    .assertIsEnabled()
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the default show icon value should be applied") {
                onNodeWithText(mandatoryButtonText).assertIsDisplayed()
                onNodeWithText(getString(componentsR.string.opens_in_external_browser))
                    .assertIsNotDisplayed()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test tertiary button default parameters - when show icon provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button with a custom show icon value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Tertiary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                    showIcon = true,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertIsEnabled()
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the custom show icon value should be applied") {
                onNodeWithText(mandatoryButtonText, substring = true).assertIsDisplayed()
                onNodeWithContentDescription(getString(componentsR.string.opens_in_external_browser))
                    .assertIsDisplayed()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test tertiary button default parameters - when no enabled provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button without a custom enabled value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Tertiary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText)
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the default enabled value should be applied") {
                onNodeWithText(mandatoryButtonText)
                    .assertIsDisplayed()
                    .assertIsEnabled()
            }

            And("the action should be performed") {
                assertTrue(actionPerformed)
            }
        }

    @Test
    fun `test tertiary button default parameters - when enabled provided`() =
        with(composeTestRule) {
            var actionPerformed = false

            Given("a button with a custom enabled value") {
                mandatoryIcon = ErrorScreenIcon.ErrorIcon
                mandatoryTitle = "Title"
                mandatoryButtonText = "Tertiary button"
                mandatoryButtonOnClick = { actionPerformed = true }
            }

            When("the screen is composed with the button") {
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
                                    text = mandatoryButtonText,
                                    onClick = mandatoryButtonOnClick,
                                    enabled = false,
                                ),
                            )
                        },
                    )
                }
            }

            And("the default button is clicked") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertHasClickAction()
                    .performClick()
            }

            Then("the custom enabled value should be applied") {
                onNodeWithText(mandatoryButtonText, substring = true)
                    .assertIsDisplayed()
                    .assertIsNotEnabled()
            }

            And("the action should not be performed") {
                assertFalse(actionPerformed)
            }
        }
}
