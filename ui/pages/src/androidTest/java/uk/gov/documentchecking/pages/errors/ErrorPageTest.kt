package uk.gov.documentchecking.pages.errors

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class) // Parameterized tests seems to crash
class ErrorPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var hasClickedPrimary = false
    private val onPrimaryClick: () -> Unit = {
        hasClickedPrimary = true
    }

    private var hasClickedSecondary = false
    private val onSecondaryClick: () -> Unit = {
        hasClickedSecondary = true
    }

    private val errorPageParameterList = ErrorPageProvider().values.toList()
    private val expectedParameterListSize = 3

    @Before
    fun setUp() {
        assertEquals(
            "the expected provider values size doesn't match!",
            expectedParameterListSize,
            errorPageParameterList.size
        )
    }

    @Test
    fun verifySingleButtonErrorPage() = errorScreenTests(errorPageParameterList[0])

    @Test
    fun verifyDoubleButtonErrorPage() = errorScreenTests(errorPageParameterList[1])

    @Test
    fun verifyDoubleButtonStylisedErrorPage() = errorScreenTests(errorPageParameterList[2])

    private fun errorScreenTests(parameters: ErrorPageParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    ErrorPage(
                        parameters = parameters.copy(
                            primaryButtonParameters = parameters.primaryButtonParameters.copy(
                                onClick = onPrimaryClick
                            ),
                            secondaryButtonParameters = parameters.secondaryButtonParameters?.copy(
                                onClick = onSecondaryClick
                            )
                        )
                    )
                }
            }

            verifyButtonInteraction(
                ErrorPageTags.primaryButton,
                "The primary button should have been clicked!"
            ) {
                hasClickedPrimary
            }

            parameters.secondaryButtonParameters?.let {
                verifyButtonInteraction(
                    ErrorPageTags.secondaryButton,
                    "The secondary button should have been clicked!"
                ) {
                    hasClickedSecondary
                }
            } ?: assertFalse(
                "The secondary button shouldn't have been clicked!",
                hasClickedSecondary
            )
        }
    }

    private fun ComposeContentTestRule.verifyButtonInteraction(
        buttonTestTag: String,
        failedAssertionMessage: String,
        hasButtonClickedHandler: () -> Boolean
    ) {
        onNode(hasTestTag(buttonTestTag), true).performClick()
        waitForIdle()
        assertTrue(
            failedAssertionMessage,
            hasButtonClickedHandler()
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = ErrorPageProvider().values.toList()
    }
}
