package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import junit.framework.TestCase.assertEquals
import kotlinx.collections.immutable.persistentListOf
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.utils.BDD.Given
import uk.gov.android.ui.patterns.utils.BDD.Then
import uk.gov.android.ui.patterns.utils.BDD.When
import uk.gov.android.ui.patterns.utils.TestUtils.getString

@RunWith(RobolectricTestRunner::class)
class DestructiveScreenPreviewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val singleLineText = CentreAlignedScreenBodyContent.Text("Body single line")
    private val body = persistentListOf(singleLineText)
    private val primaryButton = CentreAlignedScreenButton(text = "Primary Button", onClick = {})
    private val secondaryButton = CentreAlignedScreenButton(text = "Secondary Button", onClick = {})
    private val tertiaryButton = CentreAlignedScreenButton(text = "Tertiary Button", onClick = {})
    private val contentConfigurationDescription = "Test content"

    private lateinit var mandatoryIcon: ErrorScreenIcon
    private lateinit var mandatoryTitle: String
    private lateinit var content: ErrorScreenContent

    @Ignore(
        "This pattern will be removed once replaced with the v2 ErrorScreen - investigate how " +
            "to test this when bottom content is drawn twice but only displayed once",
    )
    @Test
    fun `test mandatory parameters - preview`() = with(composeTestRule) {
        Given("a preview with a content provided") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            content = ErrorScreenContent(
                configurationDescription = "Test content",
                title = mandatoryTitle,
                icon = mandatoryIcon,
                body = body,
                primaryButton = primaryButton,
                secondaryButton = secondaryButton,
                tertiaryButton = tertiaryButton,
            )
        }

        When("the preview is composed with the content") {
            setContent {
                PreviewErrorScreen(content)
            }
        }

        Then("the content should be displayed") {
            assertEquals(contentConfigurationDescription, content.configurationDescription)
            onNodeWithText(content.title).assertIsDisplayed()
            onNodeWithContentDescription(getString(mandatoryIcon.description)).assertIsDisplayed()
            val bodyChild = content.body?.first() as CentreAlignedScreenBodyContent.Text
            onNodeWithTag(BODY_LAZY_COLUMN_TEST_TAG)
                .performTouchInput { swipeUp() }
                .onChildren()
                .onLast()
                .assertTextContains(bodyChild.bodyText)
            onNodeWithText(content.primaryButton!!.text).assertIsDisplayed()
            onNodeWithText(content.secondaryButton!!.text).assertIsDisplayed()
            onNodeWithText(content.tertiaryButton!!.text).assertIsDisplayed()
        }
    }

    @Test
    fun `test default parameters - when no icon provided`() = with(composeTestRule) {
        Given("a content without an icon") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            content = ErrorScreenContent(
                configurationDescription = "Test content",
                title = mandatoryTitle,
                body = body,
                primaryButton = primaryButton,
                secondaryButton = secondaryButton,
                tertiaryButton = tertiaryButton,
            )
        }

        When("the screen is composed with the content") {
            setContent {
                PreviewErrorScreen(content)
            }
        }

        Then("the default error icon should be applied") {
            val errorIcon = ErrorScreenIcon.ErrorIcon
            onNodeWithContentDescription(getString(errorIcon.description)).assertIsDisplayed()
        }
    }

    @Ignore(
        "This pattern will be removed once replaced with the v2 ErrorScreen - investigate how " +
            "to test this when bottom content is drawn twice but only displayed once",
    )
    @Test
    fun `test mandatory parameters - accessibility preview`() = with(composeTestRule) {
        Given("an accessibility preview  with a content provided") {
            mandatoryIcon = ErrorScreenIcon.ErrorIcon
            mandatoryTitle = "Title"
            content = ErrorScreenContent(
                configurationDescription = "Test content",
                title = mandatoryTitle,
                icon = mandatoryIcon,
                body = body,
                primaryButton = primaryButton,
                secondaryButton = secondaryButton,
                tertiaryButton = tertiaryButton,
            )
        }

        When("the accessibility preview is composed with the content") {
            setContent {
                PreviewErrorScreenAccessibility(content)
            }
        }

        Then("the content should be displayed") {
            onNodeWithText(content.title).assertIsDisplayed()
            onNodeWithContentDescription(getString(mandatoryIcon.description)).assertIsDisplayed()
            val bodyChild = content.body?.first() as CentreAlignedScreenBodyContent.Text
            onNodeWithTag(BODY_LAZY_COLUMN_TEST_TAG)
                .performTouchInput { swipeUp() }
                .onChildren()
                .onLast()
                .assertTextContains(bodyChild.bodyText)
            onNodeWithText(content.primaryButton!!.text).assertIsDisplayed()
            onNodeWithText(content.secondaryButton!!.text).assertIsDisplayed()
            onNodeWithText(content.tertiaryButton!!.text).assertIsDisplayed()
        }
    }
}
