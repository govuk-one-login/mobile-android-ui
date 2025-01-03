package uk.gov.android.ui.components.inputs.date

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.theme.GdsTheme

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
            parameterList.size,
        )
    }

    @Test
    fun verifyFirstParameters() = datePickerTests(parameterList[0])

    private fun datePickerTests(parameters: DatePickerParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsDatePicker(
                        parameters,
                    )
                }
            }
            onNodeWithTag("datePicker").apply {
                assertIsDisplayed()
            }
        }
    }
}
