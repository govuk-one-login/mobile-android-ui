package uk.gov.android.ui.componentsv2.row

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

/**
 * A vertical list of multiple [Row] components.
 *
 * Displays a collection of [RowData] items in a column layout that automatically handles
 * dividers between rows where needed.
 *
 * @param rows The list of row data to display
 * @param modifier Modifier to be applied to the root Column
 * @param leftContentPadding Padding applied to the left side of [Row] content. Pass parent
 * pattern / screen padding to align content with parent content
 */
@Composable
fun RowList(
    rows: ImmutableList<RowData>,
    modifier: Modifier = Modifier,
    leftContentPadding: Dp = smallPadding,
) {
    val lastRowIndex = rows.size - 1
    Column(modifier = modifier) {
        rows.forEachIndexed { index, row ->
            Row(
                title = row.title,
                modifier = row.modifier,
                scaleLeadingImageWithFontSize = row.scaleLeadingImageWithFontSize,
                leadingImage = row.leadingImage,
                subtitle = row.subtitle,
                trailingText = row.trailingText,
                trailingIcon = row.trailingIcon,
                showDivider = index != lastRowIndex,
                leftContentPadding = leftContentPadding,
                clickEnabled = row.clickEnabled,
                onClick = row.onClick,
            )
        }
    }
}

@PreviewLightDark
@Composable
internal fun RowListPreview() {
    GdsTheme {
        RowList(
            rows = RowPreviewParametersProvider().values.map { it.toRowData() }.toImmutableList(),
        )
    }
}
