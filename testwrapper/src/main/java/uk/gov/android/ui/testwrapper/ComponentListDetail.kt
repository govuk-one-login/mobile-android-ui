package uk.gov.android.ui.testwrapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.android.ui.testwrapper.componentsv2.ComponentDetail

@Composable
fun ComponentListDetail(
    items: List<DetailItem>,
    modifier: Modifier
) {
    ListDetail(
        items = items,
        detail = { detailItem ->
            ComponentDetail(detailItem)
        },
        modifier = modifier
    )
}