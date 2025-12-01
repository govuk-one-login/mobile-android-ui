package uk.gov.android.ui.componentsv2.row

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList

@Composable
fun RowList(
    rows: ImmutableList<RowData>,
    modifier: Modifier = Modifier,
) {
    if (rows.isEmpty()) return
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
                showDivider = index != (rows.size - 1),
                clickEnabled = row.clickEnabled,
                onClick = row.onClick,
            )
        }
    }
}
