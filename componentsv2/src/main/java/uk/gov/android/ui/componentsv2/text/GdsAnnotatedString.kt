package uk.gov.android.ui.componentsv2.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.theme.buttonShadowSize
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.xsmallPadding

// Caps oversized inline vectors while preserving the established layout.
private val MAX_INLINE_ICON_SIZE = 24.dp

// Preserves the legacy inline-placeholder width proportion.
private const val INLINE_ICON_PLACEHOLDER_MULTIPLIER = 1.5f

@Composable
fun GdsAnnotatedString(
    text: String,
    fontWeight: FontWeight,
    icon: ImageVector,
    iconContentDescription: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    iconId: String = stringResource(R.string.in_line_icon_id),
    color: Color = MaterialTheme.colorScheme.onBackground,
    iconColor: Color? = null,
    iconBackgroundColor: Color = MaterialTheme.colorScheme.background,
    isIconTrailing: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
) {
    val annotatedString = createAnnotatedString(
        text = text,
        iconId = iconId,
        iconContentDescription = iconContentDescription,
        isIconTrailing = isIconTrailing,
    )

    val inlineIconContent = createInlineIconContent(
        iconId = iconId,
        icon = icon,
        iconContentDescription = iconContentDescription,
        iconColor = iconColor,
        color = color,
        iconBackgroundColor = iconBackgroundColor,
        isIconTrailing = isIconTrailing,
    )

    Text(
        modifier = modifier.padding(top = buttonShadowSize),
        text = annotatedString,
        inlineContent = inlineIconContent,
        fontWeight = fontWeight,
        style = textStyle,
        textAlign = textAlign,
        color = color,
    )
}

private fun createAnnotatedString(
    text: String,
    iconId: String,
    iconContentDescription: String,
    isIconTrailing: Boolean,
): AnnotatedString = buildAnnotatedString {
    if (isIconTrailing) {
        append(AnnotatedString(text))
        appendInlineContent(iconId, " $iconContentDescription")
    } else {
        appendInlineContent(iconId, "$iconContentDescription ")
        append(AnnotatedString(text))
    }
}

@Composable
private fun createInlineIconContent(
    iconId: String,
    icon: ImageVector,
    iconContentDescription: String,
    iconColor: Color?,
    color: Color,
    iconBackgroundColor: Color,
    isIconTrailing: Boolean,
): Map<String, InlineTextContent> {
    val density = LocalDensity.current
    val cappedIconHeight = minOf(icon.defaultHeight, MAX_INLINE_ICON_SIZE)
    val cappedIconWidth = minOf(icon.defaultWidth, MAX_INLINE_ICON_SIZE)
    val inlineIconVerticalOffset = -(buttonShadowSize / 2)

    return mapOf(
        Pair(
            iconId,
            InlineTextContent(
                Placeholder(
                    height = with(density) { cappedIconHeight.toSp() },
                    width = with(density) {
                        (cappedIconWidth * INLINE_ICON_PLACEHOLDER_MULTIPLIER).toSp()
                    },
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                ),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().then(
                        if (isIconTrailing) {
                            Modifier.padding(start = xsmallPadding)
                        } else {
                            Modifier.padding(end = xsmallPadding)
                        },
                    ),
                ) {
                    GdsIcon(
                        image = icon,
                        contentDescription = iconContentDescription,
                        color = iconColor ?: color,
                        backgroundColor = iconBackgroundColor,
                        modifier = Modifier
                            .offset(y = inlineIconVerticalOffset)
                            .semantics { hideFromAccessibility() },
                    )
                }
            },
        ),
    )
}

@Composable
@PreviewLightDark
internal fun GdsAnnotatedStringPreview(
    @PreviewParameter(GdsAnnotatedStringPreviewDataProvider::class)
    preview: GdsAnnotatedStringPreview,
) = GdsTheme {
    Surface {
        val parameters = preview.toData()
        GdsAnnotatedString(
            text = parameters.text,
            fontWeight = parameters.fontWeight,
            icon = ImageVector.vectorResource(parameters.icon),
            iconContentDescription = stringResource(parameters.iconContentDescription),
            iconId = stringResource(parameters.iconId),
            iconColor = parameters.iconColor,
            iconBackgroundColor = parameters.iconBackgroundColor,
            isIconTrailing = parameters.isIconTrailing,
            textStyle = parameters.textStyle,
            color = parameters.color,
        )
    }
}
