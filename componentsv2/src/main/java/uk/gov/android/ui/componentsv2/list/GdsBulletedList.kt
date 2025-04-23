package uk.gov.android.ui.componentsv2.list

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
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
import uk.gov.android.ui.theme.spacingSingle

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

@Composable
private fun BulletedListTitle(
    title: ListTitle,
    modifier: Modifier = Modifier,
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
            .semantics { contentDescription = titleContentDescription },
    )
}

@Composable
private fun BulletListItem(
    text: String,
    bulletContentDescription: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
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

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.semantics { contentDescription = bulletContentDescription },
        )
    }
}

@Suppress("MaxLineLength")
internal class BulletedListProvider : PreviewParameterProvider<ListWrapper> {
    override val values: Sequence<ListWrapper> = sequenceOf(
        ListWrapper(
            persistentListOf(
                "Line one bullet list content",
            ),
            ListTitle("Example Heading", TitleType.Heading),
        ),
        ListWrapper(
            persistentListOf(
                "Line one bullet list content",
                "Line two bullet list content",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
            ),
            ListTitle("Example Title", TitleType.Text),
        ),
        ListWrapper(
            persistentListOf(
                "Line one bullet list content",
                "Line two bullet list content",
            ),
            ListTitle("Example Title", TitleType.BoldText),
        ),
        ListWrapper(
            persistentListOf(
                "Line one bullet list content",
                "Line two bullet list content",
                "Line three bullet list content",
                "Line four bullet list content",
            ),
        ),
    )
}

@PreviewLightDark
@Composable
@Preview
internal fun GdsBulletedListPreview(
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
