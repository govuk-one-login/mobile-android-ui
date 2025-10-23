package uk.gov.android.ui.testwrapper.componentsv2

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
import androidx.navigation.NavHostController
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.componentsv2.dialogue.GdsDialogueDemo
import uk.gov.android.ui.testwrapper.componentsv2.inputs.radio.GdsRadiosDemo
import uk.gov.android.ui.testwrapper.componentsv2.list.GdsBulletedListDemo
import uk.gov.android.ui.testwrapper.componentsv2.list.GdsNumberedListDemo
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.testwrapper.componentsv2.topappbar.GdsTopAppBarDemo
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

enum class ComponentsDestination(
    val route: String,
    val label: String
) {
    BUTTON("button", "Button"),
    DIALOGUE("dialogue", "Dialogue"),
    HEADING("heading", "Heading"),
    IMAGES("images", "Images"),
    INPUTS("inputs", "Inputs"),
    LIST("list", "List"),
    MENU("menu", "Menu"),
    STATUS("status", "Status"),
    SUPPORTING_TEXT("supportingtext", "Supporting Text"),
    TEXT("text", "Text"),
    TOPAPPBAR("topappbar", "Top App Bar"),
    WARNING("warning", "Warning"),
    CARD("card", "Card")
}
@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun Components(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(ComponentsDestination.entries) { destination ->
            GdsHeading(
                text = destination.label,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        navHostController.navigate(destination.route)
                    })
                    .padding(smallPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
                style = GdsHeadingStyle.Title3
            )
            HorizontalDivider(color = Color.Black)
        }
    }
}

// Add new demo items here
val listItems = listOf(
    DetailItem(label = NUMBERED_LIST, name = "Numbered List"),
    DetailItem(label = BULLETED_LIST, name = "Bulleted List"),
)
val radioItems = listOf(
    DetailItem(label = RADIO, name = "Radio")
)
val topAppBarItems = listOf(
    DetailItem(label = TOP_APP_BAR, name = "Top App Bar")
)
val dialogueItems = listOf(
    DetailItem(label = DIALOGUE, name = "Dialogue")
)
val statusItems = listOf(
    DetailItem(label = STATUS_OVERLAY, name = "Status Overlay")
)

// Add new demo composables here
@Composable
fun ComponentDetail(detailItem: DetailItem) {
    when (detailItem.label) {
        BULLETED_LIST -> GdsBulletedListDemo()
        NUMBERED_LIST -> GdsNumberedListDemo()
        RADIO -> GdsRadiosDemo()
        TOP_APP_BAR -> GdsTopAppBarDemo()
        DIALOGUE -> GdsDialogueDemo()
        STATUS_OVERLAY -> StatusOverlayDemo()
    }
}

private const val NUMBERED_LIST = "numList"
private const val BULLETED_LIST = "bulList"
private const val RADIO = "radio"
private const val TOP_APP_BAR = "topAppBar"
private const val DIALOGUE = "dialogue"
private const val STATUS_OVERLAY = "StatusOverlay"
