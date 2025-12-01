package uk.gov.android.ui.componentsv2.row

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.Dividers
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

@Suppress("LongMethod")
@Composable
fun Row(
    title: String,
    modifier: Modifier = Modifier,
    leadingImage: Image? = null,
    scaleLeadingImageWithFontSize: Boolean = false,
    subtitle: String? = null,
    trailingText: String? = null,
    trailingIcon: RowTrailingIcon? = null,
    showDivider: Boolean = true,
    clickEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    val trailingIconSize = if (scaleLeadingImageWithFontSize) LocalDensity.current.fontScale else 1f
    Column(
        modifier = modifier
            .background(Backgrounds.list.toMappedColors())
            .clickable(
                enabled = clickEnabled,
                onClick = onClick,
            )
            .semantics{ role = Role.Button },
        verticalArrangement = if (showDivider) {
            Arrangement.SpaceBetween
        } else {
            Arrangement.Center
        },
    ) {
        if (showDivider) {
            Spacer(modifier = Modifier.size(0.dp))
        }
        Row(
            modifier = Modifier
                .padding(
                    start = smallPadding,
                    end = mediumPadding,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingImage?.let {
                Image(
                    imageVector = ImageVector.vectorResource(it.drawable),
                    contentDescription = it.contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(end = smallPadding, top = xsmallPadding, bottom = xsmallPadding)
                        .scale(trailingIconSize)
                        .align(alignment = Alignment.CenterVertically),
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = smallPadding, bottom = smallPadding)
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = Typography.bodyLarge,
                )
                subtitle?.let {
                    Text(
                        text = subtitle,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = Typography.labelMedium,
                    )
                }
            }
            trailingText?.let {
                Text(
                    text = trailingText,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = Typography.labelMedium,
                    maxLines = 1,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = smallPadding),
                )
            }
            trailingIcon?.let { icon ->
                when (icon) {
                    is RowTrailingIcon.NavigateNext -> {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.navigate_next),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(alignment = icon.verticalAlignment)
                                .padding(start = smallPadding, top = smallPadding, bottom = smallPadding)
                                .semantics {
                                    this.role = Role.Button
                                },
                        )
                    }

                    is RowTrailingIcon.OpenInNew -> Image(
                        imageVector = ImageVector.vectorResource(R.drawable.open_in_new),
                        contentDescription = stringResource(R.string.opens_in_external_browser),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .align(alignment = icon.verticalAlignment)
                            .padding(start = smallPadding, top = smallPadding, bottom = smallPadding)
                            .semantics {
                                role = Role.Button
                            },
                    )
                }
            }
        }
        if (showDivider) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Dividers.list.toMappedColors(),
                modifier = Modifier,
            )
        }
    }
}

internal data class RowPreviewParameters(
    val title: String,
    val leadingImage: Image? = null,
    val scaleLeadingImageWithFontSize: Boolean = false,
    val subtitle: String? = null,
    val trailingText: String? = null,
    val trailingIcon: RowTrailingIcon? = null,
    val showDivider: Boolean = true,
)

internal class RowPreviewParametersProvider :
    PreviewParameterProvider<RowPreviewParameters> {
    override val values: Sequence<RowPreviewParameters> = sequenceOf(
        RowPreviewParameters(
            title = "Title 1",
            leadingImage = null,
            subtitle = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
            trailingText = null,
            trailingIcon = RowTrailingIcon.OpenInNew(),
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 2",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            subtitle = null,
            trailingText = null,
            trailingIcon = RowTrailingIcon.NavigateNext(),
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 3",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            subtitle = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
            trailingText = null,
            trailingIcon = RowTrailingIcon.NavigateNext(),
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 4",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = RowTrailingIcon.NavigateNext(),
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 5",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = RowTrailingIcon.OpenInNew(),
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 6",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = null,
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 7",
            leadingImage = null,
            subtitle = null,
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.NavigateNext(),
            showDivider = true,
        ),
        RowPreviewParameters(
            title = "Title 8 - Really long title string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            scaleLeadingImageWithFontSize = true,
            subtitle = "Really long subtitle string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.OpenInNew(),
            showDivider = false,
        ),
        RowPreviewParameters(
            title = "Title 9 - Really long title string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            scaleLeadingImageWithFontSize = true,
            subtitle = "Really long subtitle string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.OpenInNew(Alignment.Top),
            showDivider = false,
        ),
        RowPreviewParameters(
            title = "Title 10 - Really long title string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            scaleLeadingImageWithFontSize = true,
            subtitle = "Really long subtitle string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.OpenInNew(Alignment.Bottom),
            showDivider = false,
        ),
        RowPreviewParameters(
            title = "Title 11 - Large image",
            leadingImage = Image(
                R.drawable.placeholder_leading_image_portrait,
                "",
            ),
            subtitle = null,
            trailingText = "100+",
            trailingIcon = null,
            showDivider = true,
        ),
    )
}


@Composable
@PreviewLightDark
internal fun RowPreview(
    @PreviewParameter(RowPreviewParametersProvider::class)
    parameters: RowPreviewParameters,
) {
    GdsTheme {
        Row(
            title = parameters.title,
            leadingImage = parameters.leadingImage,
            scaleLeadingImageWithFontSize = parameters.scaleLeadingImageWithFontSize,
            subtitle = parameters.subtitle,
            trailingText = parameters.trailingText,
            trailingIcon = parameters.trailingIcon,
            showDivider = parameters.showDivider,
            onClick = {},
        )
    }
}
