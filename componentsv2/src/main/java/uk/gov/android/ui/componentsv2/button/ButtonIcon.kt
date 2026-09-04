package uk.gov.android.ui.componentsv2.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import uk.gov.android.ui.componentsv2.R

/**
 * @param icon The icon to display
 * @param contentDescription The content description to apply to the icon
 * @param position The icon position relative to the button text
 */
class ButtonIcon(
    val icon: ImageVector,
    val contentDescription: String,
    val position: ButtonIconPosition = ButtonIconPosition.Trailing
) {
    companion object {
        /**
         * Create a [ButtonIcon] to use with [GdsButton] that will open in a web browser
         */
        @Composable
        fun opensInWebBrowser(): ButtonIcon = ButtonIcon(
            icon = ImageVector.vectorResource(R.drawable.ic_external_site),
            contentDescription = stringResource(R.string.opens_in_external_browser),
            position = ButtonIconPosition.Trailing
        )
    }
}

/**
 * The position of a [GdsButton]'s icon relative to the text.
 */
enum class ButtonIconPosition {
    /**
     * Icon is appended at the end of the button text
     */
    Leading,

    /**
     * Icon is prepended at the start of the button text
     */
    Trailing;

    internal fun isTrailing(): Boolean = this == Trailing
}

internal enum class ButtonIconPreview {
    Trailing,
    Leading
}

@Composable
internal fun ButtonIconPreview.toButtonIcon() = when (this) {
    ButtonIconPreview.Trailing -> ButtonIcon.opensInWebBrowser()

    ButtonIconPreview.Leading -> ButtonIcon(
        icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
        contentDescription = stringResource(R.string.icon_content_desc),
        position = ButtonIconPosition.Leading
    )
}
