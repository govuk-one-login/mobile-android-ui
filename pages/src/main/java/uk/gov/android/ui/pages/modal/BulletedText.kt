package uk.gov.android.ui.pages.modal

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

@Composable
fun BulletedText(
    parameters: BulletedTextParameters
) {
    with(parameters) {
        val textColor: Color = colorScheme.contentColorFor(colorScheme.background)
        val textStyle: TextStyle = MaterialTheme.typography.bodySmall
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top
        ) {
            if (header.text.isNotEmpty()) {
                Text(
                    color = textColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = textStyle,
                    text = header
                )
            }
            bullets.forEach { bulletText -> Bullet(bulletText) }
            if (footer.text.isNotEmpty()) {
                Text(
                    color = textColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    style = textStyle,
                    text = footer
                )
            }
        }
    }
}

@Composable
private fun Bullet(text: String) {
    val color: Color = colorScheme.contentColorFor(colorScheme.background)
    val style: TextStyle = MaterialTheme.typography.bodySmall
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(20.dp))
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = style,
            color = color
        )
    }
}

internal val bulletedTextPreviewParams = BulletedTextParameters(
    header = buildAnnotatedString {
        append("Signing out will switch off your preferences for:")
    },
    bullets = listOf(
        "using biometrics or your phone's pin or pattern to unlock the app",
        "sharing analytics about how you use the app",
        "Third extremely long bullet line that is going to show how this wraps"
    ),
    footer = buildAnnotatedString {
        append("You'll be asked to set these preferences again next time you sign in.")
    }
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun BulletedTextPreview() {
    GdsTheme {
        Column {
            BulletedText(
                parameters = bulletedTextPreviewParams.copy(
                    modifier = Modifier.fillMaxWidth().padding(smallPadding)
                )
            )
            Spacer(Modifier.fillMaxWidth().height(1.dp).background(color = colorScheme.primary))
            BulletedText(
                parameters = bulletedTextPreviewParams.copy(
                    modifier = Modifier.fillMaxWidth().padding(smallPadding),
                    header = buildAnnotatedString {},
                    footer = buildAnnotatedString {}
                )
            )
        }
    }
}
