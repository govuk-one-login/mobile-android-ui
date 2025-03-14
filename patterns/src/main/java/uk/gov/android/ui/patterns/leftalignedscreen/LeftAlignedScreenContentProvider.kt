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
    private val textShort = LoremIpsum(5).values.first()
    private val textLong = LoremIpsum(25).values.first()
    private val textExtraLong = LoremIpsum(100).values.first()
    private val supportingText =
        "Check if your passport has a biometric chip, look for the rectangular biometric chip symbol on the front cover"
    private val warning = "You cannot use your passport if it has expired"

    override val values = sequenceOf(
        LeftAlignedScreenContent(
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                    modifier = Modifier.padding(
                        start = spacingDouble,
                        end = spacingDouble,
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBody.Text(
                    textLong,
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
                        textShort,
                    ),
                ),
                LeftAlignedScreenBody.SecondaryButton(
                    "Secondary Button",
                    {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
        LeftAlignedScreenContent(
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                ),
                LeftAlignedScreenBody.Text(
                    textLong,
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
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                ),
                LeftAlignedScreenBody.Text(
                    textLong,
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(top = spacingTriple),
                ),
                LeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBody.SecondaryButton(
                    "Secondary Button",
                    {},
                ),
            ),
            supportingText = supportingText,
        ),
        LeftAlignedScreenContent(
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                ),
                LeftAlignedScreenBody.Text(
                    textLong,
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
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                ),
                LeftAlignedScreenBody.Text(
                    textLong,
                    modifier = Modifier.padding(
                        start = spacingDouble,
                        end = spacingDouble,
                        bottom = spacingTriple,
                    ),
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
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                ),
                LeftAlignedScreenBody.Text(
                    textExtraLong,
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
            title = title,
            primaryButton = "Primary Button",
        ),
        LeftAlignedScreenContent(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}

internal class LeftAlignedScreenContentAccessibilityProvider :
    PreviewParameterProvider<LeftAlignedScreenContent> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val textExtraLong = LoremIpsum(50).values.first()

    override val values: Sequence<LeftAlignedScreenContent> = sequenceOf(
        LeftAlignedScreenContent(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}
