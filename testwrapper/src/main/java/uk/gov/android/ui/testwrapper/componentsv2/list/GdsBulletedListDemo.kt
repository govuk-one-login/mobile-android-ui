package uk.gov.android.ui.testwrapper.componentsv2.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GdsBulletedListDemo() {
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val title =
        ListTitle(
            text = "Bulleted list",
            titleType = TitleType.BoldText,
        )
    val bulletedListItems =
        persistentListOf(
            ListItem(
                spannableText = R.string.bulleted_list_link_example,
                icon = R.drawable.ic_external_site,
                iconContentDescription = stringResource(R.string.opens_in_external_browser),
                onLinkTapped = {
                    scope.launch {
                        statusOverlayState.showSnackbar("First item. Link with icon clicked")
                    }
                },
            ),
            ListItem(
                spannableText = R.string.bulleted_list_link_example,
                icon = R.drawable.ic_external_site,
                iconContentDescription = stringResource(R.string.opens_in_external_browser),
                onLinkTapped = {
                    scope.launch {
                        statusOverlayState.showSnackbar("Second item. Link with icon clicked")
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
                text = "Line three bullet list content",
            ),
            ListItem(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
            ),
        )
    Scaffold(
        snackbarHost = {
            GdsStatusOverlay(
                hostState = statusOverlayState,
                modifier = Modifier.padding(horizontal = spacingDouble),
            )
        },
    ) {
        GdsBulletedList(
            bulletListItems = bulletedListItems,
            title = title,
            modifier = Modifier.padding(smallPadding),
        )
    }
}
