package uk.gov.android.ui.componentsv2.button

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme

/**
 * A full-width secondary outlined button that wraps [GdsButton].
 *
 * Uses the secondary colour token for border and text, with focus state support.
 *
 * @param text String resource id for the button label.
 * @param onClick Action to invoke when the button is clicked.
 * @param modifier Modifier to be applied to the button.
 * @param icon Optional icon to display alongside the label.
 * @param iconContentDescription Optional content description for the icon (for screen readers).
 * @param isIconTrailing When true the icon appears after the label; when false it appears before.
 * @param opensInBrowser When true, appends " - opens in web browser" to accessibility announcement.
 * @param borderWidth Width of the outline border.
 * @param enabled Controls whether the button is interactive.
 */
@Composable
fun SecondaryOutlinedButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconContentDescription: String? = null,
    isIconTrailing: Boolean = true,
    opensInBrowser: Boolean = false,
    borderWidth: Dp = 1.dp,
    enabled: Boolean = true,
) {
    var isFocused by remember { mutableStateOf(false) }

    val focusStateBackground = GdsLocalColorScheme.current.focusState
    val focusStateContent = GdsLocalColorScheme.current.focusStateContent
    val disabledContent = GdsLocalColorScheme.current.disabledButtonContent
    val defaultContent = colorScheme.secondary

    val backgroundColour = if (isFocused) focusStateBackground else Color.Transparent
    val contentColour = when {
        !enabled -> disabledContent
        isFocused -> focusStateContent
        else -> defaultContent
    }
    val borderColour = when {
        !enabled -> disabledContent
        isFocused -> focusStateContent
        else -> defaultContent
    }

    val buttonText = stringResource(text)
    val opensInBrowserSuffix = stringResource(R.string.opens_in_external_browser)
    val accessibilityText = if (opensInBrowser) "$buttonText - $opensInBrowserSuffix" else buttonText

    val buttonType = ButtonTypeV2.Custom(
        contentColor = contentColour,
        containerColor = backgroundColour,
    )

    val buttonIcon = icon?.let {
        ButtonIcon(
            icon = it,
            contentDescription = iconContentDescription ?: "",
            position = if (isIconTrailing) ButtonIconPosition.Trailing else ButtonIconPosition.Leading,
        )
    }

    GdsButton(
        text = accessibilityText,
        buttonType = buttonType,
        onClick = onClick,
        enabled = enabled,
        icon = buttonIcon,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .border(width = borderWidth, color = borderColour, shape = RectangleShape),
    )
}
