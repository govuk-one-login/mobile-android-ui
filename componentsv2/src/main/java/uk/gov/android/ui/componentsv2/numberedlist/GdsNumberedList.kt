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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
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
import androidx.compose.ui.unit.dp
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
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingDoubleAndAHalf
import uk.gov.android.ui.theme.spacingHalf
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.spacingSingleAndAQuarter
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@Composable
fun GdsNumberedList(
    numberedListItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    title: BulletedListTitle? = null
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = spacingDouble)
    ) {
        title?.let {
            NumberedListTitle(it)
        }
        numberedListItems.forEachIndexed { index, item ->
            val contentDescription = if (index == 0) {
                pluralStringResource(
                    R.plurals.content_desc_numbered_list_item,
                    numberedListItems.size,
                    numberedListItems.size,
                    1,
                    item
                )
            } else {
                "${index + 1} $item"
            }
            NumberedListItem(
                "${index+1}.",
                text = item,
                bulletContentDescription = contentDescription,
                if (numberedListItems.size < DOUBLE_DIGITS) 36.dp else 40.dp
            )
        }
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@Composable
private fun NumberedListTitle(
    title: BulletedListTitle
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
            val headingContentDesc = stringResource(R.string.heading, title.text)
            GdsHeading(
                text = title.text,
                modifier = Modifier
                    .padding(bottom = spacingHalf)
                    .semantics { contentDescription = headingContentDesc }
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
    minIndexWidth: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = spacingSingleAndAQuarter, top = spacingSingle)
    ) {
        Text(
            text = index,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier
                .semantics {
                    invisibleToUser()
                }
                .defaultMinSize(minWidth = minIndexWidth)
                .padding(end = spacingDoubleAndAHalf),
            textAlign = TextAlign.Right
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.semantics { contentDescription = bulletContentDescription },
        )
    }
}

internal const val TAG_TITLE_HEADING = "titleHeading"
internal const val TAG_TITLE_BOLD = "titleBold"
internal const val TAG_TITLE_REGULAR = "titleRegular"
internal const val DOUBLE_DIGITS = 10

internal class NumberedListProvider : PreviewParameterProvider<BulletedListItem> {
    override val values: Sequence<BulletedListItem> = sequenceOf(
        BulletedListItem(
            items = persistentListOf(
                "Single line"
            ),
            BulletedListTitle("One item GDS heading", TitleType.Heading)
        ),
        BulletedListItem(
            items = persistentListOf(
                "First line",
                "Second line"
            ),
            BulletedListTitle("Multiple items plain text title", TitleType.Text)
        ),
        BulletedListItem(
            items = persistentListOf(
                "First line",
                "Second line",
                "Third line"
            )
        ),
        BulletedListItem(
            items = persistentListOf(
                "First line",
                "Second line"
            ),
            BulletedListTitle("Multiple items bold title", TitleType.BoldText)
        ),
        BulletedListItem(
            items = persistentListOf(
                "Line one",
                "Line two",
                "Line three",
                "Line four",
                "Line five",
                "Line six",
                "Line seven",
                "Line eight",
                "Line nine",
                "Line ten",
            ),
            BulletedListTitle("Double digit index", TitleType.Heading)
        ),
        BulletedListItem(
            items = persistentListOf(
                "Line one",
                "Line two",
                "Line three",
                "Line four",
                "Line five",
                "Line six",
                "Line seven",
                "Line eight",
                "Line nine",
            ),
            BulletedListTitle("Single digit index", TitleType.Heading)
        ),
    )
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun GdsNumberedListPreview(
    @PreviewParameter(NumberedListProvider::class)
    numberedList: BulletedListItem
) {
    GdsTheme {
        GdsNumberedList(
            numberedListItems = numberedList.items,
            title = numberedList.title
        )
    }
}