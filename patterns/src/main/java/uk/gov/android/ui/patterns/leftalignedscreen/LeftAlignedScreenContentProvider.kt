package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.patterns.R
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
    private val selectionItems = persistentListOf("Yes", "No")

    override val values = sequenceOf(
        LeftAlignedScreenContent(
            title = title,
            body = listOf(
                LeftAlignedScreenBody.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBody.AnnotatedText(
                    buildAnnotatedString {
                        append(textShort)
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" 26 June 2024 at 2:56pm (UK time)")
                        }
                        append(". $textShort")
                    },
                ),
                LeftAlignedScreenBody.Title(
                    text = "Title2 - Left Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.LeftAligned,
                ),
                LeftAlignedScreenBody.Title(
                    text = "Title2 - Center Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.CenterAligned,
                ),
                LeftAlignedScreenBody.Title(
                    text = "Title2 - Right Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.RightAligned,
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
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBody.Divider(),
                LeftAlignedScreenBody.NumberedList(
                    persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
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
                LeftAlignedScreenBody.Selection(
                    items = selectionItems,
                    selectedItem = null,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            primaryButtonIsEnabled = false,
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
                    "Read more about the types of photo ID you can use",
                    {},
                ),
                LeftAlignedScreenBody.Selection(
                    items = selectionItems,
                    selectedItem = 1,
                    onItemSelected = {},
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
