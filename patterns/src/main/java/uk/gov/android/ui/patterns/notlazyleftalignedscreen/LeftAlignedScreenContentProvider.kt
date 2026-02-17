package uk.gov.android.ui.patterns.notlazyleftalignedscreen

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

internal class NotLazyLeftAlignedScreenContentProvider :
    PreviewParameterProvider<NotLazyLeftAlignedScreenContent> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val textShort = LoremIpsum(5).values.first()
    private val textLong = LoremIpsum(25).values.first()
    private val textExtraLong = LoremIpsum(100).values.first()
    private val supportingText =
        "Check if your passport has a biometric chip, look for the rectangular biometric chip symbol on the front cover"
    private val warning = "You cannot use your passport if it has expired"
    private val selectionItems = persistentListOf("Yes", "No")

    override val values = sequenceOf(
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBody.AnnotatedText(
                    buildAnnotatedString {
                        append(textShort)
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" 26 June 2024 at 2:56pm (UK time)")
                        }
                        append(". $textShort")
                    },
                ),
                NotLazyLeftAlignedScreenBody.Title(
                    text = "Title2 - Left Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.LeftAligned,
                ),
                NotLazyLeftAlignedScreenBody.Title(
                    text = "Title2 - Center Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.CenterAligned,
                ),
                NotLazyLeftAlignedScreenBody.Title(
                    text = "Title2 - Right Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.RightAligned,
                ),
                NotLazyLeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                NotLazyLeftAlignedScreenBody.BulletList(
                    persistentListOf(
                        "Bullet 1",
                        "Bullet 2",
                        textShort,
                    ),
                ),
                NotLazyLeftAlignedScreenBody.SecondaryButton(
                    "Secondary Button",
                    {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBody.Divider(),
                NotLazyLeftAlignedScreenBody.NumberedList(
                    persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
                    ),
                ),
                NotLazyLeftAlignedScreenBody.SecondaryButton(
                    "Secondary Button",
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBody.Text(
                    textLong,
                ),
                NotLazyLeftAlignedScreenBody.Warning(
                    warning,
                ),
                NotLazyLeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                NotLazyLeftAlignedScreenBody.Selection(
                    items = selectionItems,
                    selectedItem = null,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = "Primary Button",
            primaryButtonIsEnabled = false,
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBody.Text(
                    textLong,
                ),
                NotLazyLeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(top = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                NotLazyLeftAlignedScreenBody.SecondaryButton(
                    "Read more about the types of photo ID you can use",
                    {},
                ),
                NotLazyLeftAlignedScreenBody.Selection(
                    items = selectionItems,
                    selectedItem = 1,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBody.Text(
                    textLong,
                ),
                NotLazyLeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBody.Text(
                    textLong,
                    modifier = Modifier.padding(
                        bottom = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = "Primary Button",
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            body = listOf(
                NotLazyLeftAlignedScreenBody.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBody.Text(
                    textExtraLong,
                ),
                NotLazyLeftAlignedScreenBody.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBody.Image(
                    R.drawable.preview__gdsvectorimage,
                    "Image description",
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = "Primary Button",
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            primaryButton = "Primary Button",
        ),
        NotLazyLeftAlignedScreenContent(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}

internal class NotLazyLeftAlignedScreenContentAccessibilityProvider :
    PreviewParameterProvider<NotLazyLeftAlignedScreenContent> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val textExtraLong = LoremIpsum(50).values.first()

    override val values: Sequence<NotLazyLeftAlignedScreenContent> = sequenceOf(
        NotLazyLeftAlignedScreenContent(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}
