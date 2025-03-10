package uk.gov.android.ui.components.m3

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.mediumPadding

sealed class HeadingSize(
    val style: @Composable () -> TextStyle,
) {
    class DisplaySmall : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.displaySmall
        },
    )

    class HeadlineLarge : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.headlineLarge
        },
    )

    class HeadlineMedium : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.headlineMedium
        },
    )

    class HeadlineSmall : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.headlineSmall
        },
    )
}

data class Heading(
    var modifier: Modifier = Modifier,
    val size: HeadingSize,
    val color: Color? = null,
    val backgroundColor: Color? = null,
    @StringRes
    val text: Int,
    val textVar: String? = null,
    val textAlign: TextAlign? = TextAlign.Start,
    val padding: PaddingValues = PaddingValues(bottom = mediumPadding),
) {
    override fun toString(): String = "GDS Heading $size"
    val generate: @Composable () -> Unit
        get() = {
            textVar?.let {
                println(it)
                println(stringResource(id = text, it))
            }
            Text(
                color = color
                    ?: MaterialTheme.colorScheme.contentColorFor(
                        MaterialTheme.colorScheme.background,
                    ),
                modifier = modifier
                    .background(
                        color = backgroundColor
                            ?: MaterialTheme.colorScheme.background,
                    )
                    .semantics { heading() }
                    .padding(
                        padding,
                    ),
                style = size.style(),
                text = textVar?.let { stringResource(id = text, it) } ?: stringResource(id = text),
                textAlign = textAlign,
            )
        }
}

class HeadingsProvider : PreviewParameterProvider<Heading> {
    override val values: Sequence<Heading> = sequenceOf(
        Heading(
            text = R.string.preview__GdsHeading__h1,
            size = HeadingSize.DisplaySmall(),
        ),
        Heading(
            text = R.string.preview__GdsHeading__h2,
            size = HeadingSize.HeadlineLarge(),
        ),
        Heading(
            text = R.string.preview__GdsHeading__h3,
            size = HeadingSize.HeadlineMedium(),
        ),
        Heading(
            text = R.string.preview__GdsHeading__h4,
            size = HeadingSize.HeadlineSmall(),
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
    @PreviewParameter(HeadingsProvider::class)
    headingParameters: Heading,
) {
    GdsTheme {
        headingParameters.generate()
    }
}
