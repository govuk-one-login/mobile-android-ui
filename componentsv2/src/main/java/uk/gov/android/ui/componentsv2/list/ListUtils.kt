package uk.gov.android.ui.componentsv2.list

import android.content.Context
import android.graphics.Typeface
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.annotation.StringRes
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R

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
)

internal data class ListWrapper(
    val items: ImmutableList<String> = persistentListOf(),
    val title: ListTitle? = null,
    val listItems: ImmutableList<ListItem> = persistentListOf(),
)

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
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
        }
    }
}

fun ListItem.toContentDescription(context: Context): String {
    return this.text.ifEmpty {
        context.getText(this.spannableText).toString()
    }
}

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
