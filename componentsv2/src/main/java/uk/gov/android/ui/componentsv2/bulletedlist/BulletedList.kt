package uk.gov.android.ui.componentsv2.bulletedlist

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

@Composable
fun GdsBulletedList(
    bulletListItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    title: BulletedListTitle? = null,
) {
    Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        title?.let {
            BulletedListTitle(it)
        }

        bulletListItems.forEach {
            BulletListItem(it)
        }
    }
}

@Composable
fun BulletedListTitle(
    title: BulletedListTitle,
    modifier: Modifier = Modifier,
) {
    var spacingAfterTitle = 0.dp

    val textStyle = when (title.fontWeight) {
        TitleFontStyle.BoldText -> {
            Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        }

        TitleFontStyle.Heading -> {
            spacingAfterTitle = 4.dp
            Typography.headlineSmall
        }

        TitleFontStyle.Text -> {
            Typography.bodyLarge
        }
    }

    Text(
        text = title.text,
        style = textStyle,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.padding(
            start = 16.dp,
            end = 16.dp,
            bottom = spacingAfterTitle,
        ),
    )
}

@Composable
fun BulletListItem(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
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
        )
    }
}

enum class TitleFontStyle {
    BoldText, Heading, Text
}

data class BulletedListTitle(
    val text: String,
    val fontWeight: TitleFontStyle,
)

internal data class BulletedListItem(
    val items: ImmutableList<String>,
    val title: BulletedListTitle? = null,
)

@Suppress("MaxLineLength")
internal class BulletedListProvider : PreviewParameterProvider<BulletedListItem> {
    override val values: Sequence<BulletedListItem> = sequenceOf(
        BulletedListItem(
            persistentListOf(
                "Line one bullet list content",
            ),
            BulletedListTitle("Example Title", TitleFontStyle.Heading),
        ),
        BulletedListItem(
            persistentListOf(
                "Line one bullet list content",
                "Line two bullet list content",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
            ),
            BulletedListTitle("Example Title", TitleFontStyle.Text),
        ),
        BulletedListItem(
            persistentListOf(
                "Line one bullet list content",
                "Line two bullet list content",
            ),
            BulletedListTitle("Example Title", TitleFontStyle.BoldText),
        ),
        BulletedListItem(
            persistentListOf(
                "Line one bullet list content",
                "Line two bullet list content",
                "Line three bullet list content",
            ),
        ),
    )
}

@PreviewLightDark
@Composable
@Preview
private fun GdsBulletedListPreview(
    @PreviewParameter(BulletedListProvider::class)
    bulletListItems: BulletedListItem,
) {
    GdsTheme {
        GdsBulletedList(
            bulletListItems = bulletListItems.items,
            title = bulletListItems.title,
        )
    }
}
