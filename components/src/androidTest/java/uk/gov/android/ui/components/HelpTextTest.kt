package uk.gov.android.ui.components

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class HelpTextTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 1
    private val parameterList = HelpTextProvider().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            parameterList.size
        )
    }

    @Test
    fun verifyFirstParameters() = helpTextTests(parameterList[0])

    private fun helpTextTests(parameters: HelpTextParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsHelpText(
                        parameters
                    )
                }
            }

            onNodeWithText(resources.getString(parameters.text)).apply {
                assertIsDisplayed()
            }
        }
    }
}
