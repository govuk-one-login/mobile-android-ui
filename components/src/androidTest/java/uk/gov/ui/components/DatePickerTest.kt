package uk.gov.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.ui.components.inputs.date.DatePickerParameters
import uk.gov.ui.components.inputs.date.DatePickerProvider
import uk.gov.ui.components.inputs.date.GdsDatePicker
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class DatePickerTest {
    private val expectedParameterSize = 1
    private val parameterList = DatePickerProvider().values.toList()

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
    fun verifyFirstParameters() = datePickerTests(parameterList[0])

    private fun datePickerTests(parameters: DatePickerParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsDatePicker(
                        parameters
                    )
                }
            }
            onNodeWithTag("datePicker").apply {
                assertIsDisplayed()
            }
        }
    }
}
