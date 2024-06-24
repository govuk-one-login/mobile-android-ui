package uk.gov.android.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonType
import uk.gov.android.ui.components.buttons.GdsButton
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding

@Composable
fun AlertPage(
    alertPageParameters: AlertPageParameters
) {
    alertPageParameters.apply {
        GdsTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(modifier = Modifier.padding(bottom = 12.dp)) {
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(
                                    R.string.preview__alertPage__close
                                ),
                                tint = colors.primary
                            )
                        }
                    }
                    Row {
                        GdsHeading(
                            headingParameters = HeadingParameters(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = smallPadding)
                                    .padding(top = smallPadding),
                                size = HeadingSize.H1(),
                                text = title
                            )
                        )
                    }
                    Row {
                        Text(
                            color = colors.contentColorFor(colors.background),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = smallPadding),
                            style = MaterialTheme.typography.subtitle1,
                            text = annotatedContent
                        )
                    }
                }
                Row {
                    GdsButton(
                        buttonParameters = ButtonParameters(
                            buttonType = ButtonType.DANGER(),
                            text = ctaText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = smallPadding,
                                    vertical = mediumPadding
                                ),
                            onClick = onPrimary
                        )
                    )
                }
            }
        }
    }
}

data class AlertPageParameters(
    val title: Int,
    val annotatedContent: AnnotatedString,
    val ctaText: Int,
    val onClose: () -> Unit = {},
    val onPrimary: () -> Unit = {}
)

class AlertPageProvider : PreviewParameterProvider<AlertPageParameters> {
    override val values: Sequence<AlertPageParameters> = sequenceOf(
        AlertPageParameters(
            title = R.string.preview__alertPage__title,
            annotatedContent = buildAnnotatedString {
                append("One big summary line that can take multiple lines")
                appendBulletLine("First bullet line")
                appendBulletLine("Second bullet line")
                appendLine()
                appendBoldLine("Bold line that you don't want to miss")
            },
            ctaText = R.string.preview__alertPage__button
        )
    )

    private fun AnnotatedString.Builder.appendBulletLine(string: String) {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            appendLine()
            append("\t\t•\t\t")
        }
        append(string)
    }

    private fun AnnotatedString.Builder.appendBoldLine(string: String) {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            appendLine()
            append(string)
        }
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    backgroundColor = 0xFF000000,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Preview(
    @PreviewParameter(AlertPageProvider::class)
    parameters: AlertPageParameters
) {
    AlertPage(
        alertPageParameters = parameters
    )
}
