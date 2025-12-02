package uk.gov.android.ui.componentsv2.row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.theme.m3.GdsTheme

@Composable
fun RowList(
    rows: ImmutableList<RowData>,
    modifier: Modifier = Modifier,
) {
    val lastRowIndex = rows.size - 1
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
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
