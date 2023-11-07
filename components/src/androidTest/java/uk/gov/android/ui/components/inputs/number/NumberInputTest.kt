package uk.gov.ui.components.inputs.number

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class NumberInputTest {
    private val expectedParameterSize = 1
    private val parameterList = NumberInputProvider().values.toList()

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
    fun verifyFirstParameters() = numberInputTests(parameterList[0])

    private fun numberInputTests(parameters: NumberInputParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsNumberInput(
                        parameters
                    )
                }
            }
            onNodeWithTag("numberInput").apply {
                assertIsDisplayed()
            }
        }
    }
}
