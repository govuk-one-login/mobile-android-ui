package uk.gov.android.ui.componentsv2.date

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.util.Locale

class FormatDateTest {

    @Test
    fun `formatFullDate formats single digit day`() {
        val date = LocalDate.of(2025, 9, 6)

        val result = date.formatFullDate(Locale.UK)

        assertEquals("6 September 2025", result)
    }

    @Test
    fun `formatFullDate formats double digit day`() {
        val date = LocalDate.of(2025, 9, 15)

        val result = date.formatFullDate(Locale.UK)

        assertEquals("15 September 2025", result)
    }

    @Test
    fun `formatFullDate formats date in Welsh`() {
        val date = LocalDate.of(2025, 9, 6)

        val result = date.formatFullDate(Locale("cy", "GB"))

        assertEquals("6 Medi 2025", result)
    }
}
