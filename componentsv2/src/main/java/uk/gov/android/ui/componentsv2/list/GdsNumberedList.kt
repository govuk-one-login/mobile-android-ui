package uk.gov.android.ui.componentsv2.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.listItemLeftPadding
import uk.gov.android.ui.theme.listItemRightPadding
import uk.gov.android.ui.theme.listItemTitleBottomPadding
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingSingle


/**
 * A composable that displays a numbered list of items.
 *
 * @param numberedListItems The list of options to display.
 * @param modifier The modifier to apply to the layout.
 * @param title An optional title to display above the numbered list items.
 * @param accessibilityIndex previously used to index the talkback components
 * ```
 *  // The index required it would be 7
 *  GdsBulletedList(
 *      title = ListTitle(
 *          text = bulletListTitle,
 *          titleType = TitleType.Text
 *      ),
 *      bulletListItems = persistentListOf(
 *          ListItem(bullet1),
 *          ListItem(bullet2)
 *      ),
 *      accessibilityIndex = 0
 *  )
 * ```
 *
 */
@Deprecated("Use V2 GdsNumberedList with no accessibilityIndex parameter instead")
@Composable
fun GdsNumberedList(
    numberedListItems: ImmutableList<ListItem>,
    modifier: Modifier = Modifier,
    title: ListTitle? = null,
    accessibilityIndex: Float = 0f,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        title?.let {
            // Decrement the accessibilityIndex to ensure that all items are traversed by the
            // screen reader in the correct order
            NumberedListTitle(it)
        }
        GdsNumberedListLayout(numberedListItems)
    }
}
/**
 * A composable that displays a numbered list of items.
 *
 * @param numberedListItems The list of options to display.
 * @param modifier The modifier to apply to the layout.
 * @param title An optional title to display above the numbered list items.
 * @param accessibilityIndex previously used to index the talkback components
 * ```
 *  // The index required it would be 7
 *  GdsBulletedList(
 *      title = ListTitle(
 *          text = bulletListTitle,
 *          titleType = TitleType.Text
 *      ),
 *      bulletListItems = persistentListOf(
 *          ListItem(bullet1),
 *          ListItem(bullet2)
 *      ),
 *      accessibilityIndex = 0
 *  )
 * ```
 *
 */
@Composable
fun GdsNumberedList(
    numberedListItems: ImmutableList<ListItem>,
    modifier: Modifier = Modifier,
    title: ListTitle? = null,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        title?.let {
            // Decrement the accessibilityIndex to ensure that all items are traversed by the
            // screen reader in the correct order
            NumberedListTitle(it)
        }
        GdsNumberedListLayout(numberedListItems)
    }
}

/**
 * Measure the widths of the index element and display the numbered list
 *
 *  @param numberedListItems The list of items to be displayed
 */
@Composable
private fun GdsNumberedListLayout(
    numberedListItems: ImmutableList<ListItem>,
) {
    val context = LocalContext.current
    SubcomposeLayout { constraints ->
        val looseConstraints = constraints.copy(maxHeight = Constraints.Infinity)
        // Measure all IndexText elements
        val indexMeasurables = subcompose(slotId = "indices") {
            numberedListItems.forEachIndexed { index, _ ->
                IndexText("${index + 1}.", Modifier)
            }
        }.map { it.measure(looseConstraints) }
        // Calculate longest width representing the number in the list with the longest width
        val maxWidthIndex = indexMeasurables.maxBy { it.width }
        val placeables = subcompose("main") {
            // Create numbered list
            numberedListItems.forEachIndexed { index, item ->
                val displayIndex = index + 1
                val contentDescription = if (index == 0) {
                    pluralStringResource(
                        R.plurals.content_desc_numbered_list_item,
                        numberedListItems.size,
                        numberedListItems.size.convertToWord(context),
                        ONE.convertToWord(context),
                        item.toContentDescription(context),
                    )
                } else {
                    "${displayIndex.convertToWord(context)} ${item.toContentDescription(context)}"
                }
                NumberedListItem(
                    "$displayIndex.",
                    listItem = item,
                    itemContentDescription = contentDescription,
                    maxWidthIndex.width.pxToDp(),
                )
            }
        }.map { it.measure(looseConstraints) }
        val totalHeight = placeables.sumOf { it.height }

        // Render layout
        layout(constraints.maxWidth, totalHeight) {
            var yPosition = 0
            placeables.forEach {
                it.placeRelative(0, yPosition)
                yPosition += it.height
            }
        }
    }
}

/**
 * Render the numbered list title depending on the title type
 *
 * @param title The contents of the title
 */
@Composable
private fun NumberedListTitle(
    title: ListTitle,
) {
    when (title.titleType) {
        TitleType.BoldText -> {
            Text(
                text = title.text,
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(bottom = listItemTitleBottomPadding)
                    .semantics {
                        contentDescription = title.text
                    }
                    .testTag(TAG_TITLE_BOLD),
            )
        }

        TitleType.Heading -> {
            GdsHeading(
                text = title.text,
                modifier = Modifier
                    .padding(bottom = listItemTitleBottomPadding)
                    .semantics { contentDescription = title.text }
                    .testTag(TAG_TITLE_HEADING),
                style = GdsHeadingStyle.Title3,
                textAlign = GdsHeadingAlignment.LeftAligned,
                textColour = MaterialTheme.colorScheme.onBackground,
            )
        }

        TitleType.Text -> {
            Text(
                text = title.text,
                style = Typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(bottom = listItemTitleBottomPadding)
                    .semantics { contentDescription = title.text }
                    .testTag(TAG_TITLE_REGULAR),
            )
        }
    }
}

/**
 * Display the list item index and contents
 *
 * @param index The index number of the numbered list item
 * @param listItem The text content of the numbered list item
 * @param itemContentDescription The content description for the entire numbered list item
 * @param minIndexWidth The minimum width of the IndexText composable to ensure all index numbers
 * are rendered correctly. It follows the number in the list with the largest width
 */
@Composable
private fun NumberedListItem(
    index: String,
    listItem: ListItem,
    itemContentDescription: String,
    minIndexWidth: Dp,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = listItemLeftPadding,
                top = spacingSingle,
            )
            .semantics(true) { },
    ) {
        IndexText(
            index,
            modifier = Modifier
                .semantics { invisibleToUser() }
                .defaultMinSize(minWidth = minIndexWidth),
        )
        ListText(listItem, itemContentDescription)
    }
}

/**
 * Display the list item index
 *
 * @param index The index number of the numbered list item
 * @param modifier Used to set the index minimum size and tell the screen reader not to announce
 * the index
 */
@Composable
private fun IndexText(index: String, modifier: Modifier = Modifier) {
    Text(
        text = index,
        color = MaterialTheme.colorScheme.onBackground,
        style = Typography.bodyLarge,
        modifier = modifier,
        textAlign = TextAlign.Right,
    )
}

@Composable
private fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

/**
 * Render the numbered list item contents
 *
 * @param listItem The text content of the numbered list item
 * @param itemContentDescription The content description for the entire numbered list item
 */
@Composable
private fun ListText(
    listItem: ListItem,
    itemContentDescription: String,
) {
    val listContent = listItem.createDisplayText(LocalContext.current)
    val modifier = Modifier
        .semantics {
            this.contentDescription = itemContentDescription
        }
        .padding(start = listItemRightPadding)
    if (listContent.text.isNotEmpty()) {
        Text(
            text = listContent.text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = modifier,
        )
    } else {
        Text(
            text = listContent.annotatedString,
            inlineContent = listContent.inlineTextContent,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = modifier,
        )
    }
}

internal const val TAG_TITLE_HEADING = "titleHeading"
internal const val TAG_TITLE_BOLD = "titleBold"
internal const val TAG_TITLE_REGULAR = "titleRegular"

@Suppress("MaxLineLength")
internal class NumberedListProvider : PreviewParameterProvider<ListWrapper> {
    override val values: Sequence<ListWrapper> = sequenceOf(
        ListWrapper(
            listItems = persistentListOf(
                ListItem("Single line"),
            ),
            title = ListTitle("One item GDS heading", TitleType.Heading),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(FIRST_LINE),
                ListItem(SECOND_LINE),
            ),
            title = ListTitle("Multiple items plain text title", TitleType.Text),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(FIRST_LINE),
                ListItem(SECOND_LINE),
                ListItem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"),
            ),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(FIRST_LINE),
                ListItem(SECOND_LINE),
            ),
            title = ListTitle("Multiple items bold title", TitleType.BoldText),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(LINE2),
                ListItem(LINE3),
                ListItem(LINE4),
                ListItem(LINE5),
                ListItem(LINE6),
                ListItem(LINE7),
                ListItem(LINE8),
                ListItem(LINE9),
                ListItem("Line ten"),
            ),
            title = ListTitle("Double digit index", TitleType.Heading),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(LINE2),
                ListItem(LINE3),
                ListItem(LINE4),
                ListItem(LINE5),
                ListItem(LINE6),
                ListItem(LINE7),
                ListItem(LINE8),
                ListItem(LINE9),
            ),
            title = ListTitle("Single digit index", TitleType.Heading),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(LINE2),
                ListItem(LINE3),
                ListItem(LINE4),
                ListItem(LINE5),
                ListItem(LINE6),
                ListItem(LINE7),
                ListItem(LINE8),
                ListItem(LINE9),
                ListItem("Line ten"),
                ListItem("Line eleven"),
                ListItem("Line twelve"),
                ListItem("Line thirteen"),
                ListItem("Line fourteen"),
                ListItem("Line fifteen"),
                ListItem("Line sixteen"),
                ListItem("Line seventeen"),
                ListItem("Line eighteen"),
                ListItem("Line nineteen"),
                ListItem("Line twenty"),
                ListItem("Line twenty one"),
                ListItem("Line twenty two"),
            ),
            title = ListTitle("20+ digit index", TitleType.Heading),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem("Llinell un"),
                ListItem("Llinell dau"),
                ListItem("Llinell tri"),
                ListItem("Llinell pedwar"),
                ListItem("Llinell pump"),
                ListItem("Llinell chwech"),
                ListItem("Llinell saith"),
                ListItem("Llinell wyth"),
                ListItem("Llinell naw"),
                ListItem("Llinell deg"),
            ),
            title = ListTitle("Welsh example", TitleType.Heading),
        ),
        ListWrapper(
            listItems = persistentListOf(
                ListItem(LINE1),
                ListItem(
                    spannableText = R.string.bulleted_list_link_example,
                ),
                ListItem(
                    spannableText = R.string.bulleted_list_link_example,
                    icon = R.drawable.ic_external_site,
                ),
            ),
            title = ListTitle("Linked text", TitleType.Heading),
        ),
    )
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(
    name = "small font",
    fontScale = 0.5f,
)
@Preview(
    name = "normal font",
    fontScale = 1f,
)
@Preview(
    name = "large font",
    fontScale = 1.5f,
)
@Preview(showBackground = true)
@Composable
internal fun GdsNumberedListPreview(
    @PreviewParameter(NumberedListProvider::class)
    numberedListWrapper: ListWrapper,
) {
    GdsTheme {
        GdsNumberedList(
            numberedListItems = numberedListWrapper.listItems,
            title = numberedListWrapper.title,
        )
    }
}

private const val FIRST_LINE = "First line"
private const val SECOND_LINE = "Second line"
private const val LINE1 = "Line one"
private const val LINE2 = "Line two"
private const val LINE3 = "Line three"
private const val LINE4 = "Line four"
private const val LINE5 = "Line five"
private const val LINE6 = "Line six"
private const val LINE7 = "Line seven"
private const val LINE8 = "Line eight"
private const val LINE9 = "Line nine"
private const val ONE = 1
