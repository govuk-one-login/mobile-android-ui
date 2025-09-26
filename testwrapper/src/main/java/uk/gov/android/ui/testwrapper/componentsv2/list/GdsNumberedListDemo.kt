package uk.gov.android.ui.testwrapper.componentsv2.list

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType

@Composable
fun GdsNumberedListDemo(onLinkTapped: (String) -> Unit = {}) {
    val title = ListTitle(
        text = "Numbered list",
        titleType = TitleType.Heading,
    )
    val numberedListItems = persistentListOf(
        ListItem(
            text = "Line three bullet list content"
        ),
        ListItem(
            spannableText = R.string.bulleted_list_link_example,
            icon = R.drawable.ic_external_site,
            onLinkTapped = {
                onLinkTapped("https://www.android.com")
            }
        ),
        ListItem(
            spannableText = R.string.bulleted_list_link_example,
            onLinkTapped = {
                onLinkTapped("https://www.android.com")
            }
        ),
        ListItem(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"
        ),
    )
    GdsNumberedList(
        numberedListItems = numberedListItems,
        title = title
    )
}