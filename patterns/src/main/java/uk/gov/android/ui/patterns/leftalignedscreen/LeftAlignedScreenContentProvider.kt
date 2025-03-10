package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingTriple

internal class LeftAlignedScreenContentProvider :
    PreviewParameterProvider<LeftAlignedScreenContent> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val bodyTextShort = "All UK passports have a biometric chip"
    private val bodyTextLong =
        "If you have a non-UK passport, it must have a biometric chip symbol on the cover"
    private val supportingText =
        "Check if your passport has a biometric chip, look for the rectangular biometric chip symbol on the front cover"
    private val warning = "You cannot use your passport if it has expired"

    override val values = sequenceOf(
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
                LeftAlignedScreenBody.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Text(
                    bodyTextLong,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBody.BulletList(
                    persistentListOf(
                        "Bullet 1",
                        "Bullet 2",
                        LoremIpsum(5).values.first(),
                    ),
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
                LeftAlignedScreenBody.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Text(
                    bodyTextLong,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
                LeftAlignedScreenBody.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Text(
                    bodyTextLong,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            supportingText = supportingText,
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
                LeftAlignedScreenBody.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Text(
                    bodyTextLong,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
                LeftAlignedScreenBody.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Text(
                    bodyTextLong,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = "Primary Button",
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
                LeftAlignedScreenBody.Text(
                    bodyTextShort,
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Text(
                    LoremIpsum(100).values.first(),
                    modifier = Modifier.padding(horizontal = spacingDouble),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = "Primary Button",
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
            ),
            primaryButton = "Primary Button",
        ),
        LeftAlignedScreenContent(
            body = listOf(
                LeftAlignedScreenBody.Title(title),
            ),
            supportingText = LoremIpsum(20).values.first(),
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}
