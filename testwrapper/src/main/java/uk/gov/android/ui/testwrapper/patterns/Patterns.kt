package uk.gov.android.ui.testwrapper.patterns

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.patterns.loadingscreen.LoadingScreen
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.patterns.error.ErrorScreenDemo
import uk.gov.android.ui.testwrapper.patterns.error.ErrorScreenDeprecatedDemo
import uk.gov.android.ui.testwrapper.patterns.loading.LoadingFullScreenDemo
import uk.gov.android.ui.testwrapper.patterns.loading.LoadingScreenMultipleValuesDemo
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@Composable
fun Patterns(
    modifier: Modifier = Modifier,
    onNavigate: (Any) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(PatternsDestination.entries()) { destination ->
            GdsHeading(
                text = destination.label,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onNavigate(destination)
                    })
                    .padding(smallPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
                style = GdsHeadingStyle.Title3,
            )
            HorizontalDivider(color = Color.Black)
        }
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun PatternDetail(
    detailItem: DetailItem,
    displayTabRow: (Boolean) -> Unit,
) {
    when (detailItem.label) {
        LOADING_SCREEN -> LoadingScreen()
        LOADING_FULL_SCREEN -> {
            LaunchedEffect(Unit) {
                displayTabRow(false)
            }
            LoadingFullScreenDemo(displayTabRow)
        }
        ERROR_SCREEN -> ErrorScreenDemo(isFullScreen = false)
        ERROR_FULL_SCREEN -> {
            LaunchedEffect(Unit) {
                displayTabRow(false)
            }
            ErrorScreenDemo(isFullScreen = true, displayTabRow = displayTabRow)
        }
        ERROR_DEPRECATED_SCREEN -> ErrorScreenDeprecatedDemo(isFullScreen = false)
        ERROR_DEPRECATED_FULL_SCREEN -> {
            LaunchedEffect(Unit) {
                displayTabRow(false)
            }
            ErrorScreenDeprecatedDemo(isFullScreen = true, displayTabRow = displayTabRow)
        }
        LOADING_DYNAMIC_SCREEN -> LoadingScreenMultipleValuesDemo(displayTabRow)
        LOADING_DYNAMIC_FULL_SCREEN -> {
            LaunchedEffect(Unit) {
                displayTabRow(false)
            }
            LoadingScreenMultipleValuesDemo(displayTabRow)
        }
    }
}

const val LOADING_SCREEN = "loadingScreen"
const val LOADING_FULL_SCREEN = "fullLoadingScreen"
const val ERROR_SCREEN = "errorScreen"
const val ERROR_FULL_SCREEN = "fullErrorScreen"
const val ERROR_DEPRECATED_SCREEN = "errorDeprecatedScreen"
const val ERROR_DEPRECATED_FULL_SCREEN = "fullErrorDeprecatedScreen"
const val LOADING_DYNAMIC_SCREEN = "loadingDynamicScreen"
const val LOADING_DYNAMIC_FULL_SCREEN = "fullLoadingDynamicScreen"
