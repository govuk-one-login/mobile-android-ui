package uk.gov.android.ui.componentsv2.list

import android.annotation.SuppressLint
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.bulletPointWidthIncPadding
import uk.gov.android.ui.theme.listItemLeftPadding
import uk.gov.android.ui.theme.listItemRightPadding
import uk.gov.android.ui.theme.listItemTitleBottomPadding
import uk.gov.android.ui.theme.listItemTopPadding
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport

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

/**
 * Display the bulleted list title
 *
 * @param title The contents of the title
 * @param modifier Set the content description and traversal index
 * @param accessibilityIndex Defines the order that focused elements will be moved to in a screen
 * reader
 */
@Composable
private fun BulletedListTitle(
    title: ListTitle,
    modifier: Modifier = Modifier,
    accessibilityIndex: Float = LOW_PRIORITY_INDEX,
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
            .padding(bottom = listItemTitleBottomPadding)
            .semantics {
                contentDescription = titleContentDescription
                this.traversalIndex = accessibilityIndex
            },
    )
}

@Composable
@Deprecated("Use V2 BulletListItem with alternative ListItem parameter instead")
private fun BulletListItem(
    text: String,
    bulletContentDescription: String,
    modifier: Modifier = Modifier,
    accessibilityIndex: Float = 0f,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = listItemTopPadding)
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
                .padding(
                    start = listItemLeftPadding,
                    end = listItemRightPadding,
                    top = listItemTopPadding,
                )
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

/**
 * Set the background and accessibility traversalIndex for the bulleted list item
 *
 * @param text The text content of the bulleted list item
 * @param bulletContentDescription The content description for the entire bulleted list item
 * @param modifier Set the background and accessibility traversalIndex
 * @param accessibilityIndex Defines the order that focused elements will be moved to in a screen
 * reader
 */
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
                this.traversalIndex = accessibilityIndex
            }
            .padding(top = listItemTopPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ListTextIcon(
            listItem = text,
            contentDescription = bulletContentDescription,
        )
    }
}

/**
 * Arrange the bullet and accompanying text so that the bullet is vertically centered relative
 * to the first line of the text
 *
 * @param listContent The text to be displayed which could be plain text, annotated or annotated
 * with icon
 * @param bulletContentDescription The content description for the entire bulleted list item
 */
@SuppressLint("ComposeModifierMissing")
@Suppress("LongMethod")
@Composable
fun BulletedLine(
    listContent: ListContent,
    bulletContentDescription: String,
) {
    val bullet = ImageVector.vectorResource(R.drawable.ic_dot)
    val painter = rememberVectorPainter(image = bullet)
    // The line number of a single or multi line text element of the bulleted list that the bullet
    // point will vertically center itself against
    val textLineIndex = TEXT_LINE_NUMBER
    val bulletTint = MaterialTheme.colorScheme.onBackground
    // Initialise text element constraints
    var textLineTop = 0f
    var textLineBottom = 0f
    var textLineLeft = 0f
    val contentDescription = if (listContent.iconContentDescription.isNotEmpty()) {
        "$bulletContentDescription\n${listContent.iconContentDescription}"
    } else {
        bulletContentDescription
    }
    with(LocalDensity.current) {
        val imageSize = Size(bullet.defaultWidth.toPx(), bullet.defaultHeight.toPx())
        val bulletRightPadding = listItemRightPadding.toPx()
        val bulletModifier = Modifier
            .padding(start = bulletPointWidthIncPadding)
            .drawBehind {
                with(painter) {
                    // Position the bullet center vertically relative to the text line and
                    // between the start of the parent and the text line according to the design
                    translate(
                        left = textLineLeft - bulletRightPadding,
                        top = textLineTop +
                            (textLineBottom - textLineTop) / TEXT_LINE_POSITION_DIVIDER -
                            imageSize.height / TEXT_LINE_POSITION_DIVIDER,
                    ) {
                        draw(painter.intrinsicSize, colorFilter = ColorFilter.tint(bulletTint))
                    }
                }
            }
            .semantics {
                this.contentDescription = contentDescription
            }
        // Call different versions of Text Composable depending on whether we have annotated
        // text or not
        if (listContent.text.isNotEmpty()) {
            Text(
                text = listContent.text,
                color = MaterialTheme.colorScheme.onBackground,
                style = Typography.bodyLarge,
                onTextLayout = { layoutResult: TextLayoutResult ->
                    val nbLines = layoutResult.lineCount
                    if (nbLines > textLineIndex) {
                        textLineTop = layoutResult.getLineTop(textLineIndex)
                        textLineBottom = layoutResult.getLineBottom(textLineIndex)
                        textLineLeft = layoutResult.getLineLeft(textLineIndex)
                    }
                },
                modifier = bulletModifier,
            )
        } else {
            Text(
                text = listContent.annotatedString,
                color = MaterialTheme.colorScheme.onBackground,
                style = Typography.bodyLarge,
                onTextLayout = { layoutResult: TextLayoutResult ->
                    val nbLines = layoutResult.lineCount
                    if (nbLines > textLineIndex) {
                        textLineTop = layoutResult.getLineTop(textLineIndex)
                        textLineBottom = layoutResult.getLineBottom(textLineIndex)
                        textLineLeft = layoutResult.getLineLeft(textLineIndex)
                    }
                },
                inlineContent = listContent.inlineTextContent,
                modifier = bulletModifier,
            )
        }
    }
}

/**
 * Assemble any annotated strings before rendering the bulleted list item
 *
 * @param listItem The text content of the bulleted list item
 * @param contentDescription The content description for the entire bulleted list item
 */
@Composable
private fun ListTextIcon(
    listItem: ListItem,
    contentDescription: String,
) {
    val bulletListContent = listItem.createDisplayText(LocalContext.current)
    BulletedLine(
        listContent = bulletListContent,
        bulletContentDescription = contentDescription,
    )
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
        ListWrapper(
            items = persistentListOf(
                LINE1,
                LINE2,
                LINE3,
                LINE4,
            ),
            listItems = persistentListOf(
                ListItem(
                    spannableText = R.string.bulleted_list_link_example,
                ),
                ListItem(
                    spannableText = R.string.bulleted_list_link_example,
                    icon = R.drawable.ic_external_site,
                ),
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

internal const val ICON_TAG = "linkIcon"
private const val LINE1 = "Line one bullet list content"
private const val LINE2 = "Line two bullet list content"
private const val LINE3 = "Line three bullet list content"
private const val LINE4 = "Line four bullet list content"
private const val TEXT_LINE_NUMBER = 0
private const val TEXT_LINE_POSITION_DIVIDER = 2
internal val iconPlaceholderWidth = 20.sp
internal val iconPlaceholdHeight = 1.em
internal const val NO_ICON_REFERENCE = 0
private const val LOW_PRIORITY_INDEX = -1f
