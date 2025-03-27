package uk.gov.android.ui.patterns.errorscreen

import androidx.annotation.DrawableRes
import uk.gov.android.ui.patterns.R

internal data class ErrorScreenContent(
    val configurationDescription: String,
    val title: String,
    val icon: ErrorScreenIcon,
)

enum class ErrorScreenIcon(
    @DrawableRes val icon: Int,
    val description: String,
) {
    ErrorIcon(
        icon = R.drawable.ic_warning_error,
        description = "Error",
    ),
    WarningIcon(
        icon = R.drawable.ic_warning_error,
        description = "Warning",
    ),
}
