package uk.gov.android.ui.componentsv2.listcomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextLayoutResult
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
) {
    val height = remember { mutableStateOf(56.dp) }
    Column (
        modifier = modifier
            .height(height.value)
            .background(Backgrounds.list.toMappedColors()),
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
                        .padding(end = smallPadding),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = Typography.bodyLarge,
                    onTextLayout = { textLayoutResult: TextLayoutResult ->
                        if (textLayoutResult.lineCount > 1) {
                            height.value = 1000.dp
                        }
                    }
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
                        contentDescription = "",
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
            title = "Title",
            leadingImage = null,
            subtitle = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.OpenInNew {},
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title",
            leadingImage = Image(
                R.drawable.palceholder_leading_image,
                "",
            ),
            subtitle = null,
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.NavigateNext {},
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title",
            leadingImage = Image(
                R.drawable.palceholder_leading_image,
                "",
            ),
            subtitle = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.NavigateNext {},
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.NavigateNext {},
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = ListComponentTrailingIcon.OpenInNew {},
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title",
            leadingImage = null,
            subtitle = null,
            trailingText = null,
            trailingIcon = null,
            showDivider = true,
        ),
        ListComponentPreviewParameters(
            title = "Title",
            leadingImage = null,
            subtitle = null,
            trailingText = "100+",
            trailingIcon = ListComponentTrailingIcon.NavigateNext {},
            showDivider = true,
        ),

        ListComponentPreviewParameters(
            title = "Really long title string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            leadingImage = Image(
                R.drawable.palceholder_leading_image,
                "",
            ),
            subtitle = "Really long subtitle string that potentially spans multiple lines and " +
                    "takes up a lot of space with no divider",
            trailingText = "100+",
            trailingIcon = ListComponentTrailingIcon.OpenInNew {},
            showDivider = false,
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
        )
    }
}
