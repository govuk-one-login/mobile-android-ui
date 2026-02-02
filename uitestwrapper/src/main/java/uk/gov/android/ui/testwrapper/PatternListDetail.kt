package uk.gov.android.ui.testwrapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.testwrapper.patterns.PatternDetail

@Composable
fun PatternListDetail(
    items: ImmutableList<DetailItem>,
    modifier: Modifier = Modifier,
) {
    ListDetail(
        items = items,
        detail = { detailItem ->
            PatternDetail(
                detailItem,
            )
        },
        modifier = modifier,
    )
}
