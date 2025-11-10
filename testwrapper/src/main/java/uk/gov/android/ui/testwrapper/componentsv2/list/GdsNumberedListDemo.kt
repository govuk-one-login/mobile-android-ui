package uk.gov.android.ui.testwrapper.componentsv2.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GdsNumberedListDemo(
    modifier: Modifier = Modifier,
) {
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val title =
        ListTitle(
            text = "Numbered list",
            titleType = TitleType.Heading,
        )
    val numberedListItems =
        persistentListOf(
            ListItem(
                text = "Line three bullet list content",
            ),
            ListItem(
                spannableText = R.string.bulleted_list_link_example,
                icon = R.drawable.ic_external_site,
                onLinkTapped = {
                    scope.launch {
                        statusOverlayState.showSnackbar("Link with icon clicked")
                    }
                },
            ),
            ListItem(
                spannableText = R.string.bulleted_list_link_example,
                onLinkTapped = {
                    scope.launch {
                        statusOverlayState.showSnackbar("Link clicked")
                    }
                },
            ),
            ListItem(
                text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                    "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
                    "ea commodo consequat",
            ),
        )
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            GdsStatusOverlay(
                hostState = statusOverlayState,
                modifier = Modifier.padding(horizontal = spacingDouble),
            )
        },
    ) {
        GdsNumberedList(
            numberedListItems = numberedListItems,
            title = title,
            modifier = Modifier.padding(smallPadding),
        )
    }
}
