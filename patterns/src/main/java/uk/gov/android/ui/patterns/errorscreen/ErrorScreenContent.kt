package uk.gov.android.ui.patterns.errorscreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton

internal data class ErrorScreenContent(
    val configurationDescription: String,
    val title: String,
    val icon: ErrorScreenIcon = ErrorScreenIcon.ErrorIcon,
    val body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    val primaryButton: CentreAlignedScreenButton? = null,
    val secondaryButton: CentreAlignedScreenButton? = null,
    val tertiaryButton: CentreAlignedScreenButton? = null,
)

enum class ErrorScreenIcon(
    @DrawableRes val icon: Int,
    @StringRes val description: Int,
) {
    ErrorIcon(
        icon = R.drawable.ic_warning_error,
        description = R.string.error_icon_description,
    ),
    WarningIcon(
        icon = R.drawable.ic_warning_error,
        description = R.string.warning_icon_description,
    ),
}
