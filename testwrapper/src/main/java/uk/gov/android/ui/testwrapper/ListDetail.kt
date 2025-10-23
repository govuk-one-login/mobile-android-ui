package uk.gov.android.ui.testwrapper

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@Parcelize
class DetailItem(val label: String, val name: String) : Parcelable

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun List(
    items: List<DetailItem>,
    onItemClick: (DetailItem) -> Unit,
) {
    Card {
        LazyColumn(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
            items.forEach { detailItem ->
                item {
                    ListItem(
                        modifier = Modifier
                            .clickable {
                                onItemClick(detailItem)
                            },
                        headlineContent = {
                            GdsHeading(
                                text = detailItem.name,
                                textAlign = GdsHeadingAlignment.LeftAligned,
                                style = GdsHeadingStyle.Body,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                    )
                    HorizontalDivider(color = Color.Black)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetail(
    items: List<DetailItem>,
    modifier: Modifier = Modifier,
    detail: @Composable ((DetailItem) -> Unit) = {},
) {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<DetailItem>()
    val scope = rememberCoroutineScope()

    NavigableListDetailPaneScaffold(
        navigator = scaffoldNavigator,
        listPane = {
            AnimatedPane {
                List(
                    items = items,
                    onItemClick = { item ->
                        scope.launch {
                            scaffoldNavigator.navigateTo(
                                ListDetailPaneScaffoldRole.Detail,
                                item
                            )
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                scaffoldNavigator.currentDestination?.contentKey?.let {
                    detail(it)
                }
            }
        },
        modifier = modifier
    )
}
