package uk.gov.android.ui.components.m3

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.em
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.bannerElevation
import uk.gov.android.ui.theme.bannerShapeSize
import uk.gov.android.ui.theme.bannerSpotColor
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsInformationBanner(
    parameters: InformationBannerParameters,
) {
    val cardContentDescription = stringResource(
        id = R.string.preview__GdsInformationBanner__contentDescription,
    )
    parameters.apply {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = Color.Black,
            ),
            shape = RoundedCornerShape(bannerShapeSize),
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = bannerElevation,
                    spotColor = bannerSpotColor,
                    ambientColor = bannerSpotColor,
                    shape = RoundedCornerShape(bannerShapeSize),
                )
                .semantics { contentDescription = cardContentDescription }
                .then(bannerModifier),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = smallPadding,
                        end = smallPadding,
                    ),
            ) {
                TitleRow(title = title, onClick = onDismiss)
                Text(
                    text = stringResource(id = content),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(vertical = xsmallPadding),
                )
                DisplayLink(link = link, icon = icon, onClick = onClick)
            }
        }
    }
}

data class InformationBannerParameters(
    val title: Int,
    val content: Int,
    val link: Int,
    val icon: ImageVector? = null,
    val bannerModifier: Modifier = Modifier,
    val onClick: () -> Unit = {},
    val onDismiss: () -> Unit = {},
)

@Composable
private fun TitleRow(
    title: Int,
    onClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Heading(
            text = title,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            size = HeadingSize.HeadlineSmall(),
            textAlign = TextAlign.Justify,
            padding = PaddingValues(top = xsmallPadding),
            modifier = Modifier
                .weight(1f),
        ).generate()
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(
                    id = R.string.preview__GdsInformationBanner__dismissIcon__contentDescription,
                ),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun DisplayLink(
    link: Int,
    icon: ImageVector? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(bottom = smallPadding),
    ) {
        val linkStr = stringResource(id = link)
        icon?.let {
            val iconId = stringResource(id = R.string.inLine__IconId)
            val annotatedString = buildAnnotatedString {
                append(AnnotatedString(linkStr))
                appendInlineContent(iconId, "[icon]")
            }
            val inLineContent = mapOf(
                Pair(
                    iconId,
                    InlineTextContent(
                        Placeholder(
                            width = 2.em,
                            height = 1.em,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                        ),
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(
                                id = R.string
                                    .preview__GdsInformationBanner__linkIcon__contentDescription,
                            ),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    },
                ),
            )
            Text(
                text = annotatedString,
                inlineContent = inLineContent,
                color = MaterialTheme.colorScheme.primary,
            )
        } ?: run {
            Text(
                text = linkStr,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

class InformationBannerParamProvider : PreviewParameterProvider<InformationBannerParameters> {
    override val values: Sequence<InformationBannerParameters> = sequenceOf(
        InformationBannerParameters(
            title = R.string.preview__GdsInformationBanner__title,
            content = R.string.preview__GdsInformationBanner__content,
            link = R.string.preview__GdsInformationBanner__link,
            icon = Icons.AutoMirrored.Filled.ArrowForward,
        ),
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun InformationBannerPreview(
    @PreviewParameter(InformationBannerParamProvider::class)
    parameters: InformationBannerParameters,
) {
    GdsTheme {
        GdsInformationBanner(
            parameters = parameters,
        )
    }
}
