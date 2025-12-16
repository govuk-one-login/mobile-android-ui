package uk.gov.android.ui.testwrapper

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle

@Composable
fun DetailItemContent(
    items: ImmutableList<DetailItem>,
    onItemClick: (DetailItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            items.forEach { detailItem ->
                item {
                    ListItem(
                        modifier =
                        Modifier
                            .clickable {
                                onItemClick(detailItem)
                            },
                        headlineContent = {
                            GdsHeading(
                                text = detailItem.name,
                                textAlign = GdsHeadingAlignment.LeftAligned,
                                style = GdsHeadingStyle.Body,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                    )
                    HorizontalDivider(color = Color.Black)
                }
            }
        }
    }
}
