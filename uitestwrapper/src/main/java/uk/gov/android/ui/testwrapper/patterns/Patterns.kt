package uk.gov.android.ui.testwrapper.patterns

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.patterns.loadingscreen.LoadingScreen
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.patterns.centrealignedscreen.CentreAlignedScreenDemo
import uk.gov.android.ui.testwrapper.patterns.centrealignedscreen.CentreAlignedScrollableScreenDemo
import uk.gov.android.ui.testwrapper.patterns.leftalignedscreen.LeftAlignedScreenDemo
import uk.gov.android.ui.testwrapper.patterns.leftalignedscreen.LeftAlignedScreenNoTitleDemo
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
) {
    when (detailItem.label) {
        LOADING_SCREEN -> LoadingScreen()
        LEFT_ALIGNED_SCREEN -> LeftAlignedScreenDemo()
        LEFT_ALIGNED_SCREEN_NO_TITLE -> LeftAlignedScreenNoTitleDemo()
        CENTRED_ALIGNED_SCREEN -> CentreAlignedScreenDemo()
        CENTRED_ALIGNED_SCROLLABLE_SCREEN -> CentreAlignedScrollableScreenDemo()
    }
}

const val LOADING_SCREEN = "loadingScreen"
const val LEFT_ALIGNED_SCREEN = "leftAlignedScreen"
const val LEFT_ALIGNED_SCREEN_NO_TITLE = "leftAlignedScreenNoTitle"
const val CENTRED_ALIGNED_SCREEN = "centreAlignedScreen"
const val CENTRED_ALIGNED_SCROLLABLE_SCREEN = "centreAlignedScrollableScreen"
