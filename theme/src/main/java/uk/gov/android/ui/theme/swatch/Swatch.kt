package uk.gov.android.ui.theme.swatch

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.android.ui.theme.ext.fallbackTo
import uk.gov.android.ui.theme.ext.isDark
import uk.gov.android.ui.theme.ext.toHexString
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.swatchSize

internal const val LABEL_TEST_TAG = "Label"
internal const val HEX_TEST_TAG = "ColorHex"

@Composable
fun Swatch(modifier: Modifier = Modifier, data: SwatchColor) {
    with(data) {
        val textColor = color.fallbackTo(backgroundColor.textColor())
        Column(
            modifier = modifier.then(
                Modifier
                    .background(backgroundColor)
                    .width(swatchSize)
                    .aspectRatio(2.0f)
                    .padding(spacingDouble),
            ),
        ) {
            Text(
                modifier = Modifier.testTag(LABEL_TEST_TAG),
                text = label,
                color = textColor,
            )
            Text(
                modifier = Modifier.testTag(HEX_TEST_TAG),
                text = backgroundColor.toHexString(),
                color = textColor,
            )
        }
    }
}

@Composable
private fun Color.textColor() =
    MaterialTheme.colorScheme.contentColorFor(this).fallbackTo(toBlackOrWhite())

private fun Color.toBlackOrWhite() = if (isDark()) Color.White else Color.Black

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun SwatchPreview() {
    GdsTheme {
        Swatch(
            modifier = Modifier.padding(spacingDouble),
            data = SwatchColor(
                MaterialTheme.colorScheme.primary,
                "Primary",
            )
        )
    }
}
