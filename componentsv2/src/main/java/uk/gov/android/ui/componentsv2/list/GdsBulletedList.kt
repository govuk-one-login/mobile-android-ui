package uk.gov.android.ui.componentsv2.list

import android.text.Spanned
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingSingle

@Deprecated("Use GdsBulletedList with alternative bulletListItems parameter instead")
@Composable
fun GdsBulletedList(
    bulletListItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    title: ListTitle? = null,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        title?.let {
            BulletedListTitle(it)
        }

        bulletListItems.forEachIndexed { i, item ->
            val bulletContentDescription = if (i == 0) {
                pluralStringResource(
                    R.plurals.bullet_list_items,
                    bulletListItems.size,
                    bulletListItems.size,
                    item,
                )
            } else {
                stringResource(
                    R.string.bullet,
                    item,
                )
            }
            BulletListItem(item, bulletContentDescription)
        }
    }
}

/**
 * Provides a custom bullet list in Compose
 *
 * @param bulletListItems list of items to be displayed - see [ListItem]
 * @param modifier [Modifier] that can be applied to the entire bullet list
 * @param title (nullable) optional title for the list - see [ListTitle]
 * @param accessibilityIndex sets the [traversalIndex] in semantics for the list items and the title is set - only used when required and if the accessibility is not already met without using this.
 *
 * If the accessibility (TalkBack) focus/ reading order is affected by this component, you might need to set the [traversalIndex] for all elements including this list.
 *
 * **Please ensure that the [accessibilityIndex] is the index required in your layout plus 1:**
 * ```
 *  // The index required it would be 7
 *  GdsBulletedList(
 *      title = ListTitle(
 *          text = bulletListTitle,
 *          titleType = TitleType.Text
 *       ),
 *       bulletListItems = persistentListOf(
 *           ListItem(bullet1),
 *           ListItem(bullet2)
 *       ),
 *       accessibilityIndex = 8
 *  )
 *
 * ```
 */
@Composable
@JvmName("GdsBulletedListV2")
fun GdsBulletedList(
    bulletListItems: ImmutableList<ListItem>,
    modifier: Modifier = Modifier,
    title: ListTitle? = null,
    accessibilityIndex: Float = 0f,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        title?.let {
            BulletedListTitle(it, accessibilityIndex = accessibilityIndex - 1f)
        }

        bulletListItems.forEachIndexed { i, item ->
            val bulletContentDescription = if (i == 0) {
                pluralStringResource(
                    R.plurals.bullet_list_items,
                    bulletListItems.size,
                    bulletListItems.size,
                    item.toContentDescription(context),
                )
            } else {
                stringResource(
                    R.string.bullet,
                    item.toContentDescription(context),
                )
            }
            BulletListItem(
                text = item,
                bulletContentDescription = bulletContentDescription,
                accessibilityIndex = accessibilityIndex,
            )
        }
    }
}

@Composable
private fun BulletedListTitle(
    title: ListTitle,
    modifier: Modifier = Modifier,
    accessibilityIndex: Float = -1f,
) {
    val titleContentDescription: String

    val textStyle = when (title.titleType) {
        TitleType.BoldText -> {
            titleContentDescription = title.text
            Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        }

        TitleType.Heading -> {
            titleContentDescription = stringResource(R.string.heading, title.text)
            Typography.headlineSmall
        }

        TitleType.Text -> {
            titleContentDescription = title.text
            Typography.bodyLarge
        }
    }

    Text(
        text = title.text,
        style = textStyle,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .padding(bottom = 4.dp)
            .semantics {
                contentDescription = titleContentDescription
                this.traversalIndex = accessibilityIndex
            },
    )
}

@Composable
private fun BulletListItem(
    text: String,
    bulletContentDescription: String,
    modifier: Modifier = Modifier,
    accessibilityIndex: Float = 0f,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = spacingSingle)
            .semantics {
                contentDescription = bulletContentDescription
                this.traversalIndex = accessibilityIndex
            },
    ) {
        Image(
            painter = painterResource(R.drawable.ic_dot),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp, top = 8.dp)
                .align(Alignment.Top)
                .semantics { invisibleToUser() },
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.semantics { invisibleToUser() },
        )
    }
}

@Composable
private fun BulletListItem(
    text: ListItem,
    bulletContentDescription: String,
    modifier: Modifier = Modifier,
    accessibilityIndex: Float = 0f,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .semantics(true) {
                contentDescription = bulletContentDescription
                this.traversalIndex = accessibilityIndex
            }
            .padding(top = spacingSingle),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_dot),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp, top = 8.dp)
                .align(Alignment.Top)
                .semantics { invisibleToUser() },
        )

        ListText(
            listItem = text,
        )
    }
}

@Composable
private fun ListText(
    listItem: ListItem,
) {
    if (listItem.text.isNotEmpty()) {
        Text(
            text = listItem.text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.semantics { invisibleToUser() },
        )
    } else {
        val context = LocalContext.current
        val spanned = context.getText(listItem.spannableText) as Spanned
        val annotatedString = spanned.toAnnotatedString()
        Text(
            text = annotatedString,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.semantics { invisibleToUser() },
        )
    }
}

@Suppress("MaxLineLength")
internal class BulletedListProvider : PreviewParameterProvider<ListWrapper> {
    override val values: Sequence<ListWrapper> = sequenceOf(
        ListWrapper(
            items = persistentListOf(
                LINE1,
            ),
            listItems = persistentListOf(
                ListItem(LINE1),
            ),
            title = ListTitle("Example Heading", TitleType.Heading),
        ),
        ListWrapper(
            items = persistentListOf(
                LINE1,
                LINE2,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
            ),
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(LINE2),
                ListItem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"),
            ),
            title = ListTitle("Example Title", TitleType.Text),
        ),
        ListWrapper(
            items = persistentListOf(
                LINE1,
                LINE2,
            ),
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(LINE2),
            ),
            title = ListTitle("Example Title", TitleType.BoldText),
        ),
        ListWrapper(
            items = persistentListOf(
                LINE1,
                LINE2,
                LINE3,
                LINE4,
            ),
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(LINE2),
                ListItem(LINE3),
                ListItem(LINE4),
            ),
        ),
    )
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
@Preview
internal fun GdsBulletedListDeprecatedPreview(
    @PreviewParameter(BulletedListProvider::class)
    bulletListWrapper: ListWrapper,
) {
    GdsTheme {
        GdsBulletedList(
            bulletListItems = bulletListWrapper.items,
            title = bulletListWrapper.title,
        )
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
@Preview
internal fun GdsBulletedListPreview(
    @PreviewParameter(BulletedListProvider::class)
    bulletListWrapper: ListWrapper,
) {
    GdsTheme {
        GdsBulletedList(
            bulletListItems = bulletListWrapper.listItems,
            title = bulletListWrapper.title,
        )
    }
}

private const val LINE1 = "Line one bullet list content"
private const val LINE2 = "Line two bullet list content"
private const val LINE3 = "Line three bullet list content"
private const val LINE4 = "Line four bullet list content"
