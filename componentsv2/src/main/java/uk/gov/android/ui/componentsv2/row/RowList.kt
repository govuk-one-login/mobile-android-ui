package uk.gov.android.ui.componentsv2.row

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun RowList(
    rows: List<RowData>
) {
    if (rows.isEmpty()) return
    Column {
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