package uk.gov.android.ui.componentsv2.numberedlist

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
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListItem
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDoubleAndAHalf
import uk.gov.android.ui.theme.spacingHalf
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.spacingSingleAndAQuarter
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

/**
 * A composable that displays a numbered list of items.
 *
 * @param numberedListItems The list of options to display.
 * @param modifier The modifier to apply to the layout.
 * @param title An optional title to display above the numbered list items.
 */
@Composable
fun GdsNumberedList(
    numberedListItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    title: BulletedListTitle? = null,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        title?.let {
            NumberedListTitle(it)
        }
        GdsNumberedListLayout(numberedListItems)
    }
}

@Composable
private fun GdsNumberedListLayout(numberedListItems: ImmutableList<String>) {
    SubcomposeLayout { constraints ->
        val indexMeasurables = subcompose(slotId = "indices") {
            numberedListItems.forEachIndexed { index, _ ->
                IndexText("${index + 1}.", Modifier)
            }
        }.map { it.measure(constraints) }

        val maxWidthIndex = indexMeasurables.maxBy { it.width }

        val placeables = subcompose("main") {
            numberedListItems.forEachIndexed { index, item ->
                val contentDescription = if (index == 0) {
                    pluralStringResource(
                        R.plurals.content_desc_numbered_list_item,
                        numberedListItems.size,
                        numberedListItems.size,
                        1,
                        item,
                    )
                } else {
                    "${index + 1} $item"
                }
                NumberedListItem(
                    "${index + 1}.",
                    text = item,
                    bulletContentDescription = contentDescription,
                    maxWidthIndex.width.pxToDp(),
                )
            }
        }.map { it.measure(constraints) }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0

            placeables.forEach {
                it.placeRelative(0, yPosition)
                yPosition += it.height
            }
        }
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@Composable
private fun NumberedListTitle(
    title: BulletedListTitle,
) {
    when (title.titleType) {
        TitleType.BoldText -> {
            Text(
                text = title.text,
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(bottom = spacingHalf)
                    .semantics { contentDescription = title.text }
                    .testTag(TAG_TITLE_BOLD),
            )
        }

        TitleType.Heading -> {
            GdsHeading(
                text = title.text,
                modifier = Modifier
                    .padding(bottom = spacingHalf)
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
                    .padding(bottom = spacingHalf)
                    .semantics { contentDescription = title.text }
                    .testTag(TAG_TITLE_REGULAR),
            )
        }
    }
}

@Composable
private fun NumberedListItem(
    index: String,
    text: String,
    bulletContentDescription: String,
    minIndexWidth: Dp,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = spacingSingleAndAQuarter,
                top = spacingSingle,
            ),
    ) {
        IndexText(
            index,
            modifier = Modifier
                .semantics {
                    invisibleToUser()
                }
                .defaultMinSize(minWidth = minIndexWidth),
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier
                .semantics { contentDescription = bulletContentDescription }
                .padding(start = spacingDoubleAndAHalf),
        )
    }
}

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

internal const val TAG_TITLE_HEADING = "titleHeading"
internal const val TAG_TITLE_BOLD = "titleBold"
internal const val TAG_TITLE_REGULAR = "titleRegular"

@Suppress("MaxLineLength")
internal class NumberedListProvider : PreviewParameterProvider<BulletedListItem> {
    override val values: Sequence<BulletedListItem> = sequenceOf(
        BulletedListItem(
            items = persistentListOf(
                "Single line",
            ),
            BulletedListTitle("One item GDS heading", TitleType.Heading),
        ),
        BulletedListItem(
            items = persistentListOf(
                FIRST_LINE,
                SECOND_LINE,
            ),
            BulletedListTitle("Multiple items plain text title", TitleType.Text),
        ),
        BulletedListItem(
            items = persistentListOf(
                FIRST_LINE,
                SECOND_LINE,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
            ),
        ),
        BulletedListItem(
            items = persistentListOf(
                FIRST_LINE,
                SECOND_LINE,
            ),
            BulletedListTitle("Multiple items bold title", TitleType.BoldText),
        ),
        BulletedListItem(
            items = persistentListOf(
                LINE1,
                LINE2,
                LINE3,
                LINE4,
                LINE5,
                LINE6,
                LINE7,
                LINE8,
                LINE9,
                "Line ten",
            ),
            BulletedListTitle("Double digit index", TitleType.Heading),
        ),
        BulletedListItem(
            items = persistentListOf(
                LINE1,
                LINE2,
                LINE3,
                LINE4,
                LINE5,
                LINE6,
                LINE7,
                LINE8,
                LINE9,
            ),
            BulletedListTitle("Single digit index", TitleType.Heading),
        ),
        BulletedListItem(
            items = persistentListOf(
                LINE1,
                LINE2,
                LINE3,
                LINE4,
                LINE5,
                LINE6,
                LINE7,
                LINE8,
                LINE9,
                "Line ten",
                "Line eleven",
                "Line twelve",
                "Line thirteen",
                "Line fourteen",
                "Line fifteen",
                "Line sixteen",
                "Line seventeen",
                "Line eighteen",
                "Line nineteen",
                "Line twenty",
                "Line twenty one",
                "Line twenty two",
            ),
            BulletedListTitle("20+ digit index", TitleType.Heading),
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
    numberedList: BulletedListItem,
) {
    GdsTheme {
        GdsNumberedList(
            numberedListItems = numberedList.items,
            title = numberedList.title,
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
