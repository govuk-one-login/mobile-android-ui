package uk.gov.android.ui.components.m3

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.R.drawable
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.images.icon.GdsIconM3
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

data class HelpTextM3(
    @StringRes
    val text: Int,
    val rowModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
    val rowAlignment: Alignment.Vertical = Alignment.CenterVertically,
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val textAlign: TextAlign = TextAlign.Start,
    val iconParameters: IconParameters
) {
    override fun toString(): String = this::class.java.simpleName

    val generate: @Composable () -> Unit
        get() = {
            Row(
                modifier = Modifier
                    .semantics(mergeDescendants = true) {}
                    .background(
                        MaterialTheme.colorScheme.background
                    )
                    .then(
                        rowModifier
                    ),
                verticalAlignment = rowAlignment
            ) {
                key(iconParameters) {
                    GdsIconM3(iconParameters)
                }

                HeadingM3(
                    modifier = textModifier,
                    size = HeadingSizeM3.H4(),
                    text = text,
                    padding = PaddingValues(
                        start = xsmallPadding
                    ),
                    textAlign = textAlign
                ).generate()
            }
        }
}

class HelpTextProvider : PreviewParameterProvider<HelpTextM3> {
    override val values: Sequence<HelpTextM3> = sequenceOf(
        HelpTextM3(
            text = R.string.preview__GdsContent__oneLine_0,
            iconParameters = IconParameters(
                image = drawable.ic_warning_icon
            )
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
    @PreviewParameter(HelpTextProvider::class)
    helpTextParameters: HelpTextM3
) {
    GdsTheme {
        helpTextParameters.generate()
    }
}
