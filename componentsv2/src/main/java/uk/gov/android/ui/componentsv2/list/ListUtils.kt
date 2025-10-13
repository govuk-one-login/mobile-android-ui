package uk.gov.android.ui.componentsv2.list

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.Spanned
import android.text.SpannedString
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.theme.m3.Links
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.xsmallPadding

enum class TitleType {
    BoldText, Heading, Text
}

data class ListTitle(
    val text: String,
    val titleType: TitleType,
)

data class ListItem(
    val text: String = "",
    @StringRes val spannableText: Int = 0,
    @DrawableRes val icon: Int = 0,
    val iconContentDescription: String = "",
    val onLinkTapped: (String) -> Unit = {},
)

data class ListContent(
    val text: String = "",
    val annotatedString: AnnotatedString = AnnotatedString(""),
    val inlineTextContent: ImmutableMap<String, InlineTextContent> = persistentMapOf(),
    val iconContentDescription: String = "",
)

internal data class ListWrapper(
    val items: ImmutableList<String> = persistentListOf(),
    val title: ListTitle? = null,
    val listItems: ImmutableList<ListItem> = persistentListOf(),
)

@SuppressLint("ComposeUnstableReceiver")
@Composable
fun ListItem.createDisplayText(context: Context): ListContent {
    return when {
        this.text.isNotEmpty() -> {
            ListContent(text = this.text)
        }

        this.icon == NO_ICON_REFERENCE -> {
            val spanned = SpannedString(context.getText(this.spannableText))
            val annotatedString = spanned.toAnnotatedString(this.onLinkTapped)
            ListContent(annotatedString = annotatedString)
        }

        else -> {
            val spanned = SpannedString(context.getText(this.spannableText))
            val annotatedString = spanned.toAnnotatedString(
                this.onLinkTapped,
                isIcon = true,
            )
            val inlineIconContent = persistentMapOf(
                Pair(
                    ICON_ID,
                    InlineTextContent(
                        Placeholder(
                            width = iconPlaceholderWidth,
                            height = iconPlaceholdHeight,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextBottom,
                        ),
                    ) {
                        GdsIcon(
                            image = ImageVector.vectorResource(this.icon),
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
            ListContent(
                annotatedString = annotatedString,
                inlineTextContent = inlineIconContent,
                iconContentDescription = this.iconContentDescription,
            )
        }
    }
}

@SuppressLint("ComposeUnstableReceiver")
@Composable
fun Spanned.toAnnotatedString(
    linkTapListener: (String) -> Unit = {},
    isIcon: Boolean = false,
): AnnotatedString = buildAnnotatedString {
    append(this@toAnnotatedString.toString())

    getSpans(0, length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> {
                when (span.style) {
                    Typeface.BOLD -> addStyle(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start,
                        end,
                    )

                    Typeface.ITALIC -> addStyle(
                        SpanStyle(fontStyle = FontStyle.Italic),
                        start,
                        end,
                    )

                    Typeface.BOLD_ITALIC -> addStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                        ),
                        start,
                        end,
                    )
                }
            }

            is UnderlineSpan -> {
                addStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline),
                    start,
                    end,
                )
            }

            is URLSpan -> {
                val url = span.url
                val clickable = LinkAnnotation.Clickable(
                    "URL",
                    styles = TextLinkStyles(
                        style = SpanStyle(color = Links.default.toMappedColors()),
                    ),
                    linkInteractionListener = { linkTapListener(url) },
                )
                addLink(clickable, start, end)
                if (isIcon) {
                    appendInlineContent(ICON_ID)
                }
            }
        }
    }
}

fun ListItem.toContentDescription(context: Context): String {
    return this.text.ifEmpty {
        context.getText(this.spannableText).toString()
    }
}

@Suppress("MagicNumber")
fun Int.convertToWord(context: Context): String {
    return when (this) {
        1 -> context.getString(R.string.number1)
        2 -> context.getString(R.string.number2)
        3 -> context.getString(R.string.number3)
        4 -> context.getString(R.string.number4)
        5 -> context.getString(R.string.number5)
        6 -> context.getString(R.string.number6)
        7 -> context.getString(R.string.number7)
        8 -> context.getString(R.string.number8)
        9 -> context.getString(R.string.number9)
        else -> this.toString()
    }
}

const val ICON_ID = "iconId"
