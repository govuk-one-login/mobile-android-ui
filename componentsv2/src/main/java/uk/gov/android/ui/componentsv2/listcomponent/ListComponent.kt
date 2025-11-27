package uk.gov.android.ui.componentsv2.listcomponent

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
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

@Suppress("LongMethod")
@Composable
fun ListComponent(
    title: String,
    modifier: Modifier = Modifier,
    leadingImage: Image? = null,
    subtitle: String? = null,
    trailingText: String? = null,
    trailingIcon: ListComponentTrailingIcon? = null,
    showDivider: Boolean = true,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(Backgrounds.list.toMappedColors())
            .clickable(
                onClick = onClick
            ),
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
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = smallPadding, top = 8.dp, bottom = 8.dp),
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
                        style = Typography.bodySmall,
                    )
                }
            }
            trailingText?.let {
                Text(
                    text = trailingText,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 16.dp),
                )
            }
            trailingIcon?.let { icon ->
                when (icon) {
                    is ListComponentTrailingIcon.NavigateNext -> {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.navigate_next),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(start = smallPadding),
                        )
                    }

                    is ListComponentTrailingIcon.OpenInNew -> Image(
                        imageVector = ImageVector.vectorResource(R.drawable.open_in_new),
                        contentDescription = "Opens in web browser. ",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(start = smallPadding),
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

internal data class ListComponentPreviewParameters(
    val title: String,
    val leadingImage: Image? = null,
    val subtitle: String? = null,
    val trailingText: String? = null,
    val trailingIcon: ListComponentTrailingIcon? = null,
    val showDivider: Boolean = true,
)

internal class ListComponentPreviewParametersProvider :
    PreviewParameterProvider<ListComponentPreviewParameters> {
    override val values: Sequence<ListComponentPreviewParameters> = sequenceOf(
        ListComponentPreviewParameters(
            title = "Title 1",
            leadingImage = null,
            subtitle = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.OpenInNew,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title 2",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            subtitle = null,
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.NavigateNext,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title 3",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            subtitle = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.NavigateNext,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title 4",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.NavigateNext,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title 5",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.OpenInNew,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title 6",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = null,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title 7",
            leadingImage = null,
            subtitle = null,
            trailingText = "100+",
            trailingIcon = ListComponentTrailingIcon.NavigateNext,
            showDivider = true,
        ),

        ListComponentPreviewParameters(
            title = "Title 8 - Really long title string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            subtitle = "Really long subtitle string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            trailingText = "100+",
            trailingIcon = ListComponentTrailingIcon.OpenInNew,
            showDivider = false,
        ),
        ListComponentPreviewParameters(
            title = "Title 9 - Large image",
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
internal fun ListComponentPreview(
    @PreviewParameter(ListComponentPreviewParametersProvider::class)
    parameters: ListComponentPreviewParameters,
) {
    GdsTheme {
        ListComponent(
            title = parameters.title,
            leadingImage = parameters.leadingImage,
            subtitle = parameters.subtitle,
            trailingText = parameters.trailingText,
            trailingIcon = parameters.trailingIcon,
            showDivider = parameters.showDivider,
            onClick = {},
        )
    }
}
