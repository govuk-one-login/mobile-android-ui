package uk.gov.android.ui.componentsv2.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.xsmallPadding

@Composable
fun GdsAnnotatedString(
    text: String,
    fontWeight: FontWeight,
    icon: ImageVector,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
    iconId: String = stringResource(R.string.in_line_icon_id),
    color: Color = MaterialTheme.colorScheme.onBackground,
    iconColor: Color? = null,
    iconBackgroundColor: Color = MaterialTheme.colorScheme.background,
    isIconTrailing: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
) {
    val annotatedString: AnnotatedString = buildAnnotatedString {
        if (isIconTrailing) {
            append(AnnotatedString(text))
            appendInlineContent(iconId, " $iconContentDescription")
        } else {
            appendInlineContent(iconId, "$iconContentDescription ")
            append(AnnotatedString(text))
        }
    }
    val inlineIconContent = mapOf(
        Pair(
            iconId,
            InlineTextContent(
                Placeholder(
                    width = 1.5.em,
                    height = 1.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                ),
            ) {
                GdsIcon(
                    image = icon,
                    contentDescription = iconContentDescription,
                    color = iconColor ?: color,
                    backgroundColor = iconBackgroundColor,
                    modifier = if (isIconTrailing) {
                        Modifier.padding(start = xsmallPadding)
                    } else {
                        Modifier
                    }.semantics { invisibleToUser() },
                )
            },
        ),
    )
    Text(
        modifier = modifier.padding(top = 2.dp),
        text = annotatedString,
        inlineContent = inlineIconContent,
        fontWeight = fontWeight,
        style = Typography.labelLarge,
        textAlign = textAlign,
        color = color,
    )
}

internal data class AnnotatedStringPreviewParameters(
    val text: Int,
    val fontWeight: FontWeight,
    val icon: Int,
    val iconId: Int = R.string.in_line_icon_id,
    val iconContentDescription: Int,
    val iconColor: Color = Color.Unspecified,
    val iconBackgroundColor: Color = Color.Unspecified,
    val isIconTrailing: Boolean = true,
)

internal class AnnotatedStringPreviewParametersProvider : PreviewParameterProvider<AnnotatedStringPreviewParameters> {
    override val values: Sequence<AnnotatedStringPreviewParameters> = sequenceOf(
        AnnotatedStringPreviewParameters(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            isIconTrailing = true,
        ),
        AnnotatedStringPreviewParameters(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            isIconTrailing = false,
            iconColor = Color.Green,
        ),
    )
}

@Composable
@PreviewLightDark
internal fun AnnotatedStringPreview(
    @PreviewParameter(AnnotatedStringPreviewParametersProvider::class)
    parameters: AnnotatedStringPreviewParameters,
) {
    GdsTheme {
        Column(
            Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background),
        ) {
            GdsAnnotatedString(
                text = stringResource(parameters.text),
                fontWeight = parameters.fontWeight,
                icon = ImageVector.vectorResource(parameters.icon),
                iconContentDescription = stringResource(parameters.iconContentDescription),
                iconId = stringResource(parameters.iconId),
                iconColor = parameters.iconColor,
                iconBackgroundColor = parameters.iconBackgroundColor,
                isIconTrailing = parameters.isIconTrailing,
            )
        }
    }
}
