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
    val icon: ErrorScreenIcon = ErrorScreenIcon.ErrorIcon,
    val body: ImmutableList<ErrorScreenBodyContent>? = null,
    val buttons: ImmutableList<ErrorScreenButton>? = null,
)

sealed class ErrorScreenButton {
    data class PrimaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
    ) : ErrorScreenButton()
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
    ) : ErrorScreenButton()
}

sealed class ErrorScreenBodyContent {
    data class Text(
        val text: String,
        val type: TextType = TextType.Regular,
    ) : ErrorScreenBodyContent()
    data class BulletList(
        val title: BulletedListTitle? = null,
        val items: ImmutableList<String>,
    ) : ErrorScreenBodyContent()
    data class Button(
        val text: String,
        val onClick: () -> Unit,
        val buttonAlignment: ErrorScreenButtonAlignment = Center,
        val showIcon: Boolean = false
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
