package uk.gov.android.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.theme.GdsTheme

@Composable
fun GdsHeadingM3(
    headingParameters: HeadingParameters
) {
    headingParameters.apply {
        textVar?.let {
            println(it)
            println(stringResource(id = text, it))
        }
        Text(
            color = color
                ?: MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background),
            modifier = modifier
                .background(
                    color = headingParameters.backgroundColor
                        ?: MaterialTheme.colorScheme.background
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

sealed class HeadingSizeM3(
    val style: @Composable () -> TextStyle
) {
    class H1 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.headlineLarge
        }
    )

    class H2 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.headlineMedium
        }
    )

    class H3 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.headlineSmall
        }
    )

    class H4 : HeadingSize(
        style = @Composable {
            MaterialTheme.typography.titleMedium
        }
    )
}

class HeadingsM3Provider : PreviewParameterProvider<HeadingParameters> {
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
    @PreviewParameter(HeadingsM3Provider::class)
    headingParameters: HeadingParameters
) {
    GdsTheme {
        GdsHeading(
            headingParameters
        )
    }
}
