package uk.gov.android.ui.componentsv2.bulletedlist

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography

@SuppressLint("ComposeModifierMissing")
@Suppress("LongMethod")
@Composable
fun GdsBulletedList(content: BulletedListItem) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        content.title?.let {
            val textStyle: TextStyle
            val title: String
            var spacingAfterTitle = 0.dp

            when (it) {
                is BulletedListTitle.Bold -> {
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                    )
                    title = it.title
                    spacingAfterTitle = 4.dp
                }

                is BulletedListTitle.Heading -> {
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    title = it.title
                }

                is BulletedListTitle.Normal -> {
                    textStyle = Typography.bodyLarge
                    title = it.title
                }
            }

            Text(
                text = title,
                style = textStyle,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = spacingAfterTitle,
                ),
            )
        }

        content.items.forEach {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_dot),
                    contentDescription = "dot",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(start = 10.dp, end = 20.dp, top = 8.dp)
                        .align(Alignment.Top),
                )

                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

data class BulletedListItem(
    val items: List<String>,
    val title: BulletedListTitle? = null,
)

sealed class BulletedListTitle {
    data class Bold(val title: String) : BulletedListTitle()
    data class Heading(val title: String) : BulletedListTitle()
    data class Normal(val title: String) : BulletedListTitle()
}

@Suppress("MaxLineLength")
class BulletedListProvider : PreviewParameterProvider<BulletedListItem> {
    override val values: Sequence<BulletedListItem> = sequenceOf(
        BulletedListItem(
            listOf(
                "One line bullet list content",
            ),
            BulletedListTitle.Heading("Example Title"),
        ),
        BulletedListItem(
            listOf(
                "One line bullet list content",
                "One line bullet list content",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
            ),
            BulletedListTitle.Heading("Example Title"),
        ),
        BulletedListItem(
            listOf(
                "One line bullet list item",
                "One line bullet list item",
            ),
            BulletedListTitle.Bold("Example Title"),
        ),
        BulletedListItem(
            listOf(
                "One line bullet list item",
                "One line bullet list item",
                "One line bullet list item",
            ),
        ),
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(BulletedListProvider::class)
    bulletListItems: BulletedListItem,
) {
    GdsTheme {
        GdsBulletedList(
            bulletListItems,
        )
    }
}
