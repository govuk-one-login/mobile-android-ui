package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton
import uk.gov.android.ui.patterns.utils.BDD.Given
import uk.gov.android.ui.patterns.utils.BDD.Then
import uk.gov.android.ui.patterns.utils.BDD.When
import uk.gov.android.ui.patterns.utils.TestUtils.getString

@RunWith(RobolectricTestRunner::class)
class ErrorScreenParameterTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val singleLineText = CentreAlignedScreenBodyContent.Text("Body single line")
    private val body = persistentListOf(singleLineText)
    private val primaryButton = CentreAlignedScreenButton(text = "Primary Button", onClick = {})
    private val secondaryButton = CentreAlignedScreenButton(text = "Secondary Button", onClick = {})
    private val tertiaryButton = CentreAlignedScreenButton(text = "Tertiary Button", onClick = {})

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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                )
            }
        }

        Then("the body should be not displayed") {
            onNodeWithText(body.first().bodyText).assertIsNotDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                    body = body,
                )
            }
        }

        Then("the body should be displayed") {
            onNodeWithText(body.first().bodyText).assertIsDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                )
            }
        }

        Then("the primary button should be not displayed") {
            onNodeWithText(primaryButton.text).assertIsNotDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                    primaryButton = primaryButton,
                )
            }
        }

        Then("the primary button should be displayed") {
            onNodeWithText(primaryButton.text).assertIsDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                )
            }
        }

        Then("the secondary button should be not displayed") {
            onNodeWithText(secondaryButton.text).assertIsNotDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                    secondaryButton = secondaryButton,
                )
            }
        }

        Then("the secondary button should be displayed") {
            onNodeWithText(secondaryButton.text).assertIsDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                )
            }
        }

        Then("the tertiary button should be not displayed") {
            onNodeWithText(tertiaryButton.text).assertIsNotDisplayed()
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
                    icon = mandatoryIcon,
                    title = mandatoryTitle,
                    tertiaryButton = tertiaryButton,
                )
            }
        }

        Then("the tertiary button should be displayed") {
            onNodeWithText(tertiaryButton.text).assertIsDisplayed()
        }
    }
}
