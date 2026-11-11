package uk.gov.android.ui.componentsv2.button

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * @param icon The icon to display
 * @param contentDescription The content description to apply to the icon
 * @param position The icon position relative to the button text
 */
class ButtonIcon(
    val icon: ImageVector,
    val contentDescription: String,
    val position: ButtonIconPosition = ButtonIconPosition.Trailing,
) {
    companion object {

        /**
         * Create a [ButtonIcon] to use with [GdsButton] that will open in a web browser
         */
        @Deprecated(
            message =
                "Replace with ButtonIcon.opensExternalDestination(ExternalDestination.WebBrowser)" +
                    "- aim  to remove by 8th December 2026",
            replaceWith = ReplaceWith(
                "ButtonIcon.opensExternalDestination(ExternalDestination.WebBrowser)",
            ),
            level = DeprecationLevel.WARNING,
        )
        @Composable
        fun opensInWebBrowser(): ButtonIcon = ButtonIcon(
            icon = ImageVector.vectorResource(R.drawable.ic_external_site),
            contentDescription = stringResource(R.string.opens_in_external_browser),
            position = ButtonIconPosition.Trailing,
        )

        /**
         * Create a [ButtonIcon] to use with [GdsButton] based on the external destination
         */
        @Composable
        fun opensExternalDestination(destination: ExternalDestination): ButtonIcon =
            when (destination) {
                ExternalDestination.WebBrowser -> ButtonIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_external_site),
                    contentDescription = stringResource(R.string.opens_in_external_browser),
                    position = ButtonIconPosition.Trailing,
                )

                ExternalDestination.Settings -> ButtonIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_external_site),
                    contentDescription = stringResource(R.string.opens_in_settings),
                    position = ButtonIconPosition.Trailing,
                )

                ExternalDestination.PlayStore -> ButtonIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_external_site),
                    contentDescription = stringResource(R.string.opens_in_play_store),
                    position = ButtonIconPosition.Trailing,
                )
            }
    }
}

/**
 *  The destination to open when a [GdsButton] icon is clicked
 */
enum class ExternalDestination {
    WebBrowser,
    Settings,
    PlayStore,
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
    Trailing,

    ;

    internal fun isTrailing(): Boolean = this == Trailing
}

internal enum class ButtonIconPreview {
    Trailing,
    Leading,
    Settings,
    PlayStore,
}

@Composable
@PreviewLightDark
internal fun ButtonIconPreview(
    @PreviewParameter(ButtonIconPreviewDataProvider::class)
    preview: ButtonIconPreview,
) = GdsTheme {
    Surface {
        GdsButton(
            text = preview.name,
            buttonType = ButtonTypeV2.Primary(),
            onClick = {},
            icon = preview.toButtonIcon(),
        )
    }
}

internal class ButtonIconPreviewDataProvider : PreviewParameterProvider<ButtonIconPreview> {
    override val values: Sequence<ButtonIconPreview> = ButtonIconPreview.entries.asSequence()
}

@Composable
internal fun ButtonIconPreview.toButtonIcon() = when (this) {
    ButtonIconPreview.Trailing -> ButtonIcon.opensExternalDestination(
        ExternalDestination.WebBrowser,
    )

    ButtonIconPreview.Settings -> ButtonIcon.opensExternalDestination(
        ExternalDestination.Settings,
    )

    ButtonIconPreview.PlayStore -> ButtonIcon.opensExternalDestination(
        ExternalDestination.PlayStore,
    )

    ButtonIconPreview.Leading -> ButtonIcon(
        icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
        contentDescription = stringResource(R.string.icon_content_desc),
        position = ButtonIconPosition.Leading,
    )
}
