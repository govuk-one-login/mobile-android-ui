package uk.gov.android.ui.patterns.errorscreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenButtonAlignment.Center

internal data class ErrorScreenContent(
    val configurationDescription: String,
    val title: String,
    val icon: ErrorScreenIcon,
    val body: ImmutableList<ErrorScreenBodyContent>? = null,
    val buttons: ImmutableList<ErrorScreenButton>? = null,
)

sealed class ErrorScreenButton {
    data class PrimaryButton(
        val text: String,
        val onClick: () -> Unit,
    ) : ErrorScreenButton()
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        @DrawableRes val icon: Int? = null,
        @StringRes val iconDescription: Int? = null,
    ) : ErrorScreenButton()
}

sealed class ErrorScreenBodyContent {
    data class Text(
        val text: String,
        val type: TextType,
    ) : ErrorScreenBodyContent()
    data class BulletList(
        val title: BulletedListTitle? = null,
        val items: ImmutableList<String>,
    ) : ErrorScreenBodyContent()
    data class Button(
        val text: String,
        val onClick: () -> Unit,
        val buttonAlignment: ErrorScreenButtonAlignment = Center,
        @DrawableRes val icon: Int? = null,
        @StringRes val iconDescription: Int? = null,
    ) : ErrorScreenBodyContent()
}

enum class TextType {
    Bold, Regular
}

enum class ErrorScreenButtonAlignment {
    Start,
    Center,
}

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
