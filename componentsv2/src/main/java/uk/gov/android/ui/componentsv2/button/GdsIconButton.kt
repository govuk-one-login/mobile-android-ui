package uk.gov.android.ui.componentsv2.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.closeButtonSize
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * This allows for an IconButton implementation that has default values for color and enforces the consumer
 * to pass in both the icon and content description, as required values.
 *
 * @param onClick - action for when the button is displayed - REQUIRED
 * @param modifier - default to [Modifier] without any add-ons - applied to the IconButton as a whole, not to the icon itself
 * @param contentModifier - default to [Modifier] without any add-ons - applied to the content/ Icon only
 * @param content - default to [GdsIconButtonDefaults.defaultCloseContent] (close/ x icon) - enables for default with the correct content description to be declared in [GdsIconButtonDefaults]
 * @param size - default to [GdsIconButtonDefaults.buttonSize] - the size of the button (clickable area)
 * @param color - default to [GdsIconButtonDefaults.defaultColor] (top bar icon color) - controls icon color
 *
 */
@Composable
fun GdsIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    size: Dp = GdsIconButtonDefaults.buttonSize,
    content: IconButtonContent = GdsIconButtonDefaults.defaultCloseContent,
    color: Color = GdsIconButtonDefaults.defaultColor(),
) {
    IconButton(
        modifier = modifier.size(size),
        onClick = onClick,
    ) {
        Icon(
            imageVector = content.icon,
            contentDescription = stringResource(content.contentDescription),
            tint = color,
            modifier = contentModifier,
        )
    }
}

data class IconButtonContent(
    val icon: ImageVector,
    @StringRes
    val contentDescription: Int,
)

object GdsIconButtonDefaults {
    val buttonSize = closeButtonSize

    val defaultCloseContent = IconButtonContent(
        icon = Icons.Filled.Close,
        contentDescription = R.string.close_icon_button,
    )

    val defaultBackContent = IconButtonContent(
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = R.string.back_icon_button,
    )

    @Composable
    fun defaultColor() = GdsLocalColorScheme.current.topBarIcon
}

data class GdsIconButtonPreviewParams(
    val content: IconButtonContent,
    val color: Color? = null,
)

internal class GdsIconButtonPreviewProvider :
    PreviewParameterProvider<GdsIconButtonPreviewParams> {
    override val values: Sequence<GdsIconButtonPreviewParams> = sequenceOf(
        GdsIconButtonPreviewParams(
            GdsIconButtonDefaults.defaultCloseContent,
        ),
        GdsIconButtonPreviewParams(
            GdsIconButtonDefaults.defaultBackContent,
        ),
        GdsIconButtonPreviewParams(
            GdsIconButtonDefaults.defaultBackContent,
            Color.Red,
        ),
        GdsIconButtonPreviewParams(
            IconButtonContent(
                icon = Icons.Filled.MoreVert,
                contentDescription = R.string.more_vert_icon_button,
            ),
        ),
    )
}

@PreviewLightDark
@Composable
internal fun GdsIconButtonPreview(
    @PreviewParameter(GdsIconButtonPreviewProvider::class)
    params: GdsIconButtonPreviewParams,
) {
    GdsTheme {
        params.color?.let {
            GdsIconButton(
                onClick = {},
                content = params.content,
                color = it,
            )
        } ?: GdsIconButton(
            onClick = {},
            content = params.content,
        )
    }
}
