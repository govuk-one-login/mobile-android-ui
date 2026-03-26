package uk.gov.android.ui.componentsv2.utils

import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Format a date according to the GOV.UK style guide.
 *
 * https://www.gov.uk/guidance/style-guide/a-to-z#dates
 */
fun LocalDate.formatFullDate(locale: Locale): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", locale)

    return format(formatter)
}

/**
 * Format a date according to the GOV.UK style guide.
 *
 * https://www.gov.uk/guidance/style-guide/a-to-z#dates
 *
 * Uses the [Locale] that is currently active for formatting.
 */
@Composable
fun LocalDate.formatFullDate(): String =
    formatFullDate(primaryLocale())
