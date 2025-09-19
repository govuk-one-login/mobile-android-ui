package uk.gov.android.ui.testwrapper.componentsv2.list

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType

@Composable
fun GdsBulletedListDemo(
    onTapped: (String) -> Unit = {}
) {
    val contentDescription = "icon content description"
    val title = ListTitle(
        text = "Bulleted list",
        titleType = TitleType.Heading,
    )
    val bulletedListItems = persistentListOf(
        ListItem(
            spannableText = R.string.bulleted_list_link_example,
            icon = R.drawable.ic_external_site,
            iconContentDescription = contentDescription,
            onLinkTapped = {
                onTapped("https://www.android.com")
            }
        ),
        ListItem(
            spannableText = R.string.bulleted_list_link_example,
            onLinkTapped = {
                onTapped("https://www.android.com")
            }
        ),
    )
    GdsBulletedList(
        bulletListItems = bulletedListItems,
        title = title,
    )
}