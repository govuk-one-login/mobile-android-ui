package uk.gov.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.ui.components.inputs.radio.GdsRadioSelection
import uk.gov.ui.components.inputs.radio.RadioSelectionParameters
import uk.gov.ui.components.inputs.radio.RadioSelectionProvider
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class RadioOptionsTest {
    private val expectedParameterSize = 1
    private val parameterList = RadioSelectionProvider().values.toList()

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
    fun verifyFirstParameters() = radioOptionsTests(parameterList[0])

    private fun radioOptionsTests(parameters: RadioSelectionParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsRadioSelection(
                        parameters
                    )
                }
            }

            parameters.radioOptions.forEach {
                onNodeWithText(it.text).apply {
                    assertIsDisplayed()
                }
            }
        }
    }
}
