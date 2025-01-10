package uk.gov.android.ui.componentsv2.text

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.em
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.componentsv2.images.IconParameters
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3_theme_dark_primary
import uk.gov.android.ui.theme.m3_theme_light_background
import uk.gov.android.ui.theme.xsmallPadding

@Composable
fun GdsAnnotatedString(
    parameters: AnnotatedStringParameters,
) {
    with(parameters) {
        val iconIdStr = stringResource(id = iconId)
        val annotatedString: AnnotatedString = buildAnnotatedString {
            if (isIconTrailing) {
                append(AnnotatedString(stringResource(id = text)))
                appendInlineContent(iconIdStr, "[icon]")
            } else {
                appendInlineContent(iconIdStr, "[icon]")
                append(AnnotatedString(stringResource(id = text)))
            }
        }
        val inlineIconContent = mapOf(
            Pair(
                iconIdStr,
                InlineTextContent(
                    Placeholder(
                        width = 2.em,
                        height = 1.em,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                    ),
                ) {
                    GdsIcon(
                        parameters = IconParameters(
                            image = icon,
                            contentDescription = iconContentDescription,
                            color = iconColor,
                            backgroundColor = iconBackgroundColor,
                            modifier = Modifier
                                .padding(start = xsmallPadding),
                        ),
                    )
                },
            ),
        )
        Text(
            text = annotatedString,
            inlineContent = inlineIconContent,
            fontWeight = fontWeight,
            style = Typography.labelLarge,
            textAlign = TextAlign.Center,
        )
    }
}

data class AnnotatedStringParameters(
    val text: Int,
    val fontWeight: FontWeight,
    val icon: Int,
    val iconId: Int = R.string.in_line_icon_id,
    val iconContentDescription: Int,
    val iconColor: Color = Color.Unspecified,
    val iconBackgroundColor: Color = Color.Unspecified,
    val isIconTrailing: Boolean = true,
)

class AnnotatedStringPreviewParameters : PreviewParameterProvider<AnnotatedStringParameters> {
    override val values: Sequence<AnnotatedStringParameters> = sequenceOf(
        AnnotatedStringParameters(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            isIconTrailing = true,
            iconColor = m3_theme_dark_primary,
            iconBackgroundColor = m3_theme_light_background,
        ),
        AnnotatedStringParameters(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            isIconTrailing = false,
            iconColor = m3_theme_dark_primary,
            iconBackgroundColor = m3_theme_light_background,
        ),
    )
}

@Composable
@PreviewLightDark
fun AnnotatedStringPreview(
    @PreviewParameter(AnnotatedStringPreviewParameters::class)
    parameters: AnnotatedStringParameters,
) {
    GdsTheme {
        GdsAnnotatedString(parameters)
    }
}
