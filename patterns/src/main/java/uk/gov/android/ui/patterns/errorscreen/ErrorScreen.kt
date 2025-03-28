package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreen
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

/**
 * Renders a centre-aligned error screen with a structured layout.
 *
 * This screen is designed for displaying an warning/error icon, title, body content,
 * and bottom content with primary/secondary buttons in a visually consistent manner.
 *
 * @param icon image displayed at the top of the screen.
 * @param title represents the main title. Use of [GdsHeading] is recommended
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param body list of [ErrorScreenBodyContent] representing the main content (optional).
 * @param buttons list of [ErrorScreenButton] representing the primary/secondary buttons at the bottom of the screen (optional).
 */
@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun ErrorScreen(
    icon: ErrorScreenIcon,
    title: String,
    modifier: Modifier = Modifier,
    body: ImmutableList<ErrorScreenBodyContent>? = null,
    buttons: ImmutableList<ErrorScreenButton>? = null,
) {
    CentreAlignedScreen(
        title = { horizontalPadding ->
            GdsHeading(
                text = title,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                textAlign = TextAlign.Center,
            )
        },
        image = { horizontalPadding ->
            Icon(
                imageVector = ImageVector.vectorResource(icon.icon),
                contentDescription = stringResource(icon.description),
                modifier = Modifier.padding(horizontal = horizontalPadding),
                tint = colorScheme.onBackground,
            )
        },
        modifier = modifier,
        body = body?.let {
            { horizontalPadding ->
                toBodyContent(body = body, horizontalItemPadding = horizontalPadding)
            }
        },
        bottomContent = buttons?.let {
            { horizontalPadding ->
                ToBottomContent(buttons = buttons, horizontalItemPadding = horizontalPadding)
            }
        },
    )
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewErrorScreen(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreen(
            title = content.title,
            icon = content.icon,
            body = content.body,
            buttons = content.buttons,
        )
    }
}

@PreviewLightDark
@Preview(showBackground = true, fontScale = 2f)
@Composable
internal fun PreviewErrorScreenAccessibility(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreen(
            title = content.title,
            icon = content.icon,
            body = content.body,
            buttons = content.buttons,
        )
    }
}
