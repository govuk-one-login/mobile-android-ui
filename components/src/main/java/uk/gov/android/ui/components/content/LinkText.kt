package uk.gov.ui.components.content

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.ui.components.R
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.smallPadding

@Composable
fun GdsLinkText(
    params: LinkTextParameters
) {
    val uriHandler = LocalUriHandler.current
    params.apply {
        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .background(
                    colors.background
                )
                .then(
                    colModifier
                ),
            horizontalAlignment = colAlignment
        ) {
            Text(
                color = color ?: colors.primary,
                modifier = textModifier
                    .clickable {
                        uriHandler.openUri(uri)
                    },
                style = textStyle ?: MaterialTheme.typography.body1,
                text = stringResource(id = contentText),
                textAlign = textAlign
            )
        }
    }
}

data class LinkTextParameters(
    @StringRes
    val contentText: Int,
    val uri: String,
    val color: Color? = null,
    val textStyle: TextStyle? = null,
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
    val colAlignment: Alignment.Horizontal = Alignment.Start,
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val textAlign: TextAlign = TextAlign.Start
) {
    override fun toString(): String = this::class.java.simpleName
}

class LinkTextProvider : PreviewParameterProvider<LinkTextParameters> {
    override val values: Sequence<LinkTextParameters> = sequenceOf(
        LinkTextParameters(
            contentText = R.string.preview__GdsContent__oneLine_0,
            uri = ""
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
    @PreviewParameter(LinkTextProvider::class)
    params: LinkTextParameters
) {
    GdsTheme {
        GdsLinkText(
            params
        )
    }
}
