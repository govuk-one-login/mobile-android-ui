package uk.gov.android.ui.componentsv2.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
 * @param color - default to [GdsIconButtonDefaults.colors] (default colors) - controls container/ content (icon)/ and all disabled stated for the icon button.
 *
 * **To change any of the colors, you can either define all colors or just copy the [GdsIconButtonDefaults.colors] and amend only the colors required**
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    size: Dp = GdsIconButtonDefaults.buttonSize,
    content: IconButtonContent = GdsIconButtonDefaults.defaultCloseContent,
    color: IconButtonColors = GdsIconButtonDefaults.colors(),
) {
    var focusStateEnabled by remember { mutableStateOf(false) }
    val colors = getFocusStateColors(focusStateEnabled)
    val rippleAlpha = RippleAlpha(
        draggedAlpha = RippleDefaults.RippleAlpha.draggedAlpha,
        focusedAlpha = RippleDefaults.RippleAlpha.focusedAlpha,
        hoveredAlpha = RippleDefaults.RippleAlpha.hoveredAlpha,
        pressedAlpha = RIPPLE_ALPHA,
    )
    val gdsRippleConfiguration = RippleConfiguration(
        color = getRippleColour(focusStateEnabled),
        rippleAlpha = rippleAlpha,
    )
    CompositionLocalProvider(LocalRippleConfiguration provides gdsRippleConfiguration) {
        IconButton(
            modifier = modifier.size(size),
            onClick = onClick,
            colors = colors,
        ) {
            Icon(
                imageVector = content.icon,
                contentDescription = stringResource(content.contentDescription),
                tint = color.contentColor,
                modifier = contentModifier,
            )
        }
    }
}

@Composable
private fun getFocusStateColors(focusState: Boolean) = if (focusState) {
    GdsIconButtonDefaults.colors().copy(
        containerColor = GdsLocalColorScheme.current.focusState,
        contentColor = GdsLocalColorScheme.current.focusStateContent,
    )
} else {
    GdsIconButtonDefaults.colors()
}

@Composable
private fun getRippleColour(isInFocus: Boolean) = if (isInFocus) {
    GdsLocalColorScheme.current.focusButtonHighlighted
} else {
    GdsLocalColorScheme.current.primaryButtonHighlighted
}

data class IconButtonContent(
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
)

object GdsIconButtonDefaults {
    val buttonSize = closeButtonSize

    @Composable
    fun colors() = IconButtonDefaults.iconButtonColors(
        containerColor = Color.Transparent,
        contentColor = GdsLocalColorScheme.current.iconDefault,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    val defaultCloseContent = IconButtonContent(
        icon = Icons.Filled.Close,
        contentDescription = R.string.close_icon_button,
    )

    val defaultBackContent = IconButtonContent(
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = R.string.back_icon_button,
    )
}

data class GdsIconButtonPreviewParams(
    val content: IconButtonContent,
    val color: Color? = null,
)

internal class GdsIconButtonPreviewProvider : PreviewParameterProvider<GdsIconButtonPreviewParams> {
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
    @PreviewParameter(GdsIconButtonPreviewProvider::class) params: GdsIconButtonPreviewParams,
) {
    GdsTheme {
        params.color?.let {
            GdsIconButton(
                onClick = {},
                content = params.content,
                color = GdsIconButtonDefaults.colors().copy(contentColor = it),
            )
        } ?: GdsIconButton(
            onClick = {},
            content = params.content,
        )
    }
}

private const val RIPPLE_ALPHA = 0.5f
