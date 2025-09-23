package uk.gov.android.ui.componentsv2.list

import android.text.SpannedString
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Links
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.xsmallPadding

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
                this.traversalIndex = accessibilityIndex
            }
            .padding(top = spacingSingle),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ListTextIcon(
            listItem = text,
            contentDescription = bulletContentDescription,
        )
    }
}

@Composable
fun TextIconCentered(
    text: String,
    modifier: Modifier = Modifier,
) {
    val icon = ImageVector.vectorResource(R.drawable.ic_dot)
    val painter = rememberVectorPainter(image = icon)
    val iconLine = 0
    val iconTint = MaterialTheme.colorScheme.onBackground
    var lineTop = 0f
    var lineBottom = 0f
    var lineLeft = 0f
    with(LocalDensity.current) {
        val imageSize = Size(icon.defaultWidth.toPx(), icon.defaultHeight.toPx())
        val iconRightPadding = 20.dp
        val iconLeftPadding = 36.dp
        val rightPadding = iconRightPadding.toPx()
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            onTextLayout = { layoutResult: TextLayoutResult ->
                val nbLines = layoutResult.lineCount
                if (nbLines > iconLine) {
                    lineTop = layoutResult.getLineTop(iconLine)
                    lineBottom = layoutResult.getLineBottom(iconLine)
                    lineLeft = layoutResult.getLineLeft(iconLine)
                }
            },
            modifier = modifier
                .padding(start = iconLeftPadding)
                .drawBehind {
                    with(painter) {
                        translate(
                            left = lineLeft - rightPadding,
                            top = lineTop + (lineBottom - lineTop) / 2 - imageSize.height / 2,
                        ) {
                            draw(painter.intrinsicSize, colorFilter = ColorFilter.tint(iconTint))
                        }
                    }
                },
        )
    }
}

@Composable
fun AnnotatedTextIconCentered(
    text: AnnotatedString,
    inlineIconContent: ImmutableMap<String, InlineTextContent>,
    modifier: Modifier = Modifier,
) {
    val icon = ImageVector.vectorResource(R.drawable.ic_dot)
    val painter = rememberVectorPainter(image = icon)
    val iconLine = 0
    val iconTint = MaterialTheme.colorScheme.onBackground
    var lineTop = 0f
    var lineBottom = 0f
    var lineLeft = 0f
    with(LocalDensity.current) {
        val imageSize = Size(icon.defaultWidth.toPx(), icon.defaultHeight.toPx())
        val iconRightPadding = 20.dp
        val iconLeftPadding = 36.dp
        val rightPadding = iconRightPadding.toPx()
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            onTextLayout = { layoutResult: TextLayoutResult ->
                val nbLines = layoutResult.lineCount
                if (nbLines > iconLine) {
                    lineTop = layoutResult.getLineTop(iconLine)
                    lineBottom = layoutResult.getLineBottom(iconLine)
                    lineLeft = layoutResult.getLineLeft(iconLine)
                }
            },
            inlineContent = inlineIconContent,
            modifier = modifier
                .padding(start = iconLeftPadding)
                .drawBehind {
                    with(painter) {
                        translate(
                            left = lineLeft - rightPadding,
                            top = lineTop + (lineBottom - lineTop) / 2 - imageSize.height / 2,
                        ) {
                            draw(painter.intrinsicSize, colorFilter = ColorFilter.tint(iconTint))
                        }
                    }
                },
        )
    }
}

@Composable
@Suppress("LongMethod")
private fun ListTextIcon(
    listItem: ListItem,
    contentDescription: String,
) {
    when {
        listItem.text.isNotEmpty() -> {
            TextIconCentered(
                text = listItem.text,
                modifier = Modifier.semantics { this.contentDescription = contentDescription },
            )
        }

        listItem.icon == 0 -> {
            val context = LocalContext.current
            val spanned = SpannedString(context.getText(listItem.spannableText))
            val annotatedString = spanned.toAnnotatedString(listItem.onLinkTapped)
            AnnotatedTextIconCentered(
                text = annotatedString,
                inlineIconContent = persistentMapOf(),
                modifier = Modifier.semantics {
                    this.contentDescription = contentDescription
                },
            )
        }

        else -> {
            val context = LocalContext.current
            val spanned = SpannedString(context.getText(listItem.spannableText))
            val annotatedString = spanned.toAnnotatedString(
                listItem.onLinkTapped,
                isIcon = true,
            )
            val inlineIconContent = persistentMapOf(
                Pair(
                    ICON_ID,
                    InlineTextContent(
                        Placeholder(
                            width = 20.sp,
                            height = 1.em,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextBottom,
                        ),
                    ) {
                        GdsIcon(
                            image = ImageVector.vectorResource(listItem.icon),
                            contentDescription = null,
                            color = Links.default.toMappedColors(),
                            backgroundColor = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .padding(start = xsmallPadding)
                                .testTag(ICON_TAG),
                        )
                    },
                ),
            )
            AnnotatedTextIconCentered(
                text = annotatedString,
                inlineIconContent = inlineIconContent,
                modifier = Modifier.semantics {
                    this.contentDescription = contentDescription
                },
            )
        }
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
