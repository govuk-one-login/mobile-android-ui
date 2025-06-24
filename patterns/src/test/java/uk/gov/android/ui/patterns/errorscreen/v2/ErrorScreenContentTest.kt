package uk.gov.android.ui.patterns.errorscreen.v2

import junit.framework.TestCase.assertEquals
import org.junit.Test
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.utils.BDD.Given
import uk.gov.android.ui.patterns.utils.BDD.Then
import uk.gov.android.ui.patterns.utils.BDD.When

class ErrorScreenContentTest {

    @Test
    fun `test error screen icon enum specification`() {
        Given("expected enum values of error screen icons")
        val expectedIcons = listOf(ErrorScreenIcon.ErrorIcon, ErrorScreenIcon.WarningIcon)

        When("retrieving all enum values")
        val actualIcons = ErrorScreenIcon.entries.toList()

        Then("the list of values should match the expected values") {
            assertEquals(expectedIcons.size, ErrorScreenIcon.entries.size)
            assertEquals(expectedIcons, actualIcons)
        }
    }

    @Test
    fun `test error icon specification`() {
        val expectedIcon = R.drawable.ic_warning_error
        val expectedDescription = R.string.error_icon_description

        val errorIcon = ErrorScreenIcon.ErrorIcon

        assertEquals(expectedIcon, errorIcon.icon)
        assertEquals(expectedDescription, errorIcon.description)
    }

    @Test
    fun `test warning icon specification`() {
        val expectedIcon = R.drawable.ic_warning_error
        val expectedDescription = R.string.warning_icon_description

        val errorIcon = ErrorScreenIcon.WarningIcon

        assertEquals(expectedIcon, errorIcon.icon)
        assertEquals(expectedDescription, errorIcon.description)
    }
}
