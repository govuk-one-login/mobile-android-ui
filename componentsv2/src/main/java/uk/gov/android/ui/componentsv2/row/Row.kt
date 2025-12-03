package uk.gov.android.ui.componentsv2.row

import androidx.annotation.VisibleForTesting
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.theme.m3.Dividers
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

/**
 * A single clickable row component.
 *
 * Displays content in a horizontal layout with consistent styling and an optional divider.
 * For multiple rows, use [RowList] can be used.
 *
 * @param title The main text content of the row
 * @param modifier Modifier to be applied to the root component
 * @param leadingImage Optional image to display at the start of the row
 * @param scaleLeadingImageWithFontSize Whether the leading image should scale with font size
 * as well as display size
 * @param subtitle Optional secondary text displayed below the title
 * @param trailingText Optional text displayed at the end of the row
 * @param trailingIcon Optional icon displayed at the end of the row
 * @param showDivider Whether to show a divider below the row
 * @param leftContentPadding Padding applied to the left side of row content
 * @param clickEnabled Whether the row is clickable
 * @param onClick Callback invoked when the row is clicked
 */
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
    leftContentPadding: Dp = smallPadding,
    clickEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    var imageScalingFactor = 1f
    leadingImage?.let {
        val displayScalingFactor = getDisplayScalingFactor()
        imageScalingFactor = if (scaleLeadingImageWithFontSize) {
            LocalDensity.current.fontScale * displayScalingFactor
        } else {
            displayScalingFactor
        }
    }

    Column(
        modifier = if (clickEnabled) {
            modifier
                .background(GdsLocalColorScheme.current.rowBackground)
                .clickable(
                    role = Role.Button,
                    onClick = onClick,
                )
        } else
            modifier
                .background(GdsLocalColorScheme.current.rowBackground)
                .semantics(mergeDescendants = true) {},
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
                    start = leftContentPadding,
                    end = mediumPadding,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingImage?.let {
                val image = ImageVector.vectorResource(it.drawable)
                Image(
                    imageVector = image,
                    contentDescription = it.contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(
                            height = image.defaultHeight * imageScalingFactor,
                            width = image.defaultWidth * imageScalingFactor,
                        )
                        .padding(end = smallPadding, top = xsmallPadding, bottom = xsmallPadding)
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
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.navigate_next),
                            contentDescription = null,
                            modifier = Modifier
                                .align(alignment = icon.verticalAlignment)
                                .padding(
                                    start = smallPadding,
                                    top = smallPadding,
                                    bottom = smallPadding,
                                ),
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }

                    is RowTrailingIcon.OpenInNew -> Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.open_in_new),
                        contentDescription = stringResource(R.string.opens_in_external_browser),
                        modifier = Modifier
                            .align(alignment = icon.verticalAlignment)
                            .padding(
                                start = smallPadding,
                                top = smallPadding,
                                bottom = smallPadding,
                            ),
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
        if (showDivider) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Dividers.row.toMappedColors(),
            )
        }
    }
}

@VisibleForTesting
@Composable
fun getDisplayScalingFactor(): Float {
    val displayMetrics = LocalResources.current.displayMetrics
    val defaultDensity =
        persistentListOf(displayMetrics.xdpi, displayMetrics.ydpi).average() / BASELINE_DENSITY
    if (defaultDensity <= 0.0) {
        return 1f
    }
    val displayScalingFactor = LocalDensity.current.density / defaultDensity.toFloat()
    return displayScalingFactor
}

private const val BASELINE_DENSITY = 160

internal data class RowPreviewParameters(
    val title: String,
    val leadingImage: Image? = null,
    val scaleLeadingImageWithFontSize: Boolean = false,
    val subtitle: String? = null,
    val trailingText: String? = null,
    val trailingIcon: RowTrailingIcon? = null,
    val clickEnabled: Boolean = true,
    val showDivider: Boolean = true,
) {
    fun toRowData(): RowData = RowData(
        title = title,
        leadingImage = leadingImage,
        scaleLeadingImageWithFontSize = scaleLeadingImageWithFontSize,
        subtitle = subtitle,
        trailingText = trailingText,
        trailingIcon = trailingIcon,
        clickEnabled = clickEnabled,
        onClick = {},
    )
}

internal class RowPreviewParametersProvider :
    PreviewParameterProvider<RowPreviewParameters> {
    override val values: Sequence<RowPreviewParameters> = sequenceOf(
        RowPreviewParameters(
            title = "Title 1",
            leadingImage = null,
            subtitle = SUBTITLE_PLACEHOLDER,
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
            subtitle = SUBTITLE_PLACEHOLDER,
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
            title = "Title 8 - $LONG_STRING",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            scaleLeadingImageWithFontSize = true,
            subtitle = LONG_STRING,
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.OpenInNew(Alignment.Top),
            showDivider = false,
        ),
        RowPreviewParameters(
            title = "Title 9 - $LONG_STRING",
            leadingImage = Image(
                R.drawable.placeholder_leading_image,
                "",
            ),
            scaleLeadingImageWithFontSize = true,
            subtitle = LONG_STRING,
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.OpenInNew(Alignment.Bottom),
            showDivider = false,
        ),
        RowPreviewParameters(
            title = "Title 10 - Portrait image",
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

private const val LONG_STRING = "Really long string that potentially spans multiple lines and " +
    "takes up a lot of space"
private const val SUBTITLE_PLACEHOLDER = "Supporting line text lorem ipsum " +
    "dolor sit amet, consectetur."

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

@Composable
@PreviewFontScale
internal fun RowPreviewFontScale() {
    GdsTheme {
        Row(
            title = "Title - Varying font scale",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image,
                contentDescription = "",
            ),
            subtitle = SUBTITLE_PLACEHOLDER,
            trailingText = "100+",
            trailingIcon = RowTrailingIcon.OpenInNew(),
            scaleLeadingImageWithFontSize = true,
            onClick = {},
        )
    }
}
