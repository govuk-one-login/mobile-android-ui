package uk.gov.android.ui.patterns.lefalignedscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingTriple

internal class LeftAlignedContentProvider :
    PreviewParameterProvider<LeftAlignedContent> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val bodyTextShort = "All UK passports have a biometric chip"
    private val bodyTextLong =
        "If you have a non-UK passport, it must have a biometric chip symbol on the cover"
    private val supportingText =
        "Check if your passport has a biometric chip, look for the rectangular biometric chip symbol on the front cover"
    private val warning = "You cannot use your passport if it has expired"

    override val values = sequenceOf(
        LeftAlignedContent(
            title,
            body = listOf(
                Body.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                Body.Text(
                    bodyTextLong,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                Body.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                Body.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}

internal data class LeftAlignedContent(
    val title: String,
    val body: List<Body>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val secondaryButton: String? = null,
)

internal sealed class Body {
    data class Text(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : Body()

    data class Warning(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : Body()

    data class BulletList(val bullets: ImmutableList<String>) : Body()

    data class Image(
        val image: Int,
        val contentDescription: String,
        val modifier: Modifier = Modifier,
    ) : Body()
}
