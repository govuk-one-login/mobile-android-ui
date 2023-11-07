package uk.gov.android.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
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
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.mediumPadding

@Composable
fun GdsHeading(
    headingParameters: HeadingParameters
) {
    headingParameters.apply {
        textVar?.let {
            println(it)
            println(stringResource(id = text, it))
        }
        Text(
            color = color ?: MaterialTheme.colors.contentColorFor(
                backgroundColor = MaterialTheme.colors.background
            ),
            modifier = modifier
                .background(
                    color = headingParameters.backgroundColor ?: MaterialTheme.colors.background
                )
                .semantics { heading() }
                .padding(
                    padding
                ),
            style = size.style(),
            text = textVar?.let { stringResource(id = text, it) } ?: stringResource(id = text),
            textAlign = textAlign
        )
    }
}

data class HeadingParameters(
    var modifier: Modifier = Modifier,
    val size: HeadingSize,
    val color: Color? = null,
    val backgroundColor: Color? = null,
    @StringRes
    val text: Int,
    val textVar: String? = null,
    val textAlign: TextAlign? = TextAlign.Start,
    val padding: PaddingValues = PaddingValues(bottom = mediumPadding)
) {
    override fun toString(): String = "GDS Heading $size"
}

sealed class HeadingSize(
    val style: @Composable () -> TextStyle
) {
    class H1 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.h1
        }
    )

    class H2 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.h2
        }
    )

    class H3 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.h3
        }
    )

    class H4 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.h4
        }
    )
}

class HeadingsProvider : PreviewParameterProvider<HeadingParameters> {
    override val values: Sequence<HeadingParameters> = sequenceOf(
        HeadingParameters(
            text = R.string.preview__GdsHeading__h1,
            size = HeadingSize.H1()
        ),
        HeadingParameters(
            text = R.string.preview__GdsHeading__h2,
            size = HeadingSize.H2()
        ),
        HeadingParameters(
            text = R.string.preview__GdsHeading__h3,
            size = HeadingSize.H3()
        ),
        HeadingParameters(
            text = R.string.preview__GdsHeading__h4,
            size = HeadingSize.H4()
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview(
    @PreviewParameter(HeadingsProvider::class)
    headingParameters: HeadingParameters
) {
    GdsTheme {
        GdsHeading(
            headingParameters
        )
    }
}
