package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R as componentsv2R
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadiosTitle
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType
import uk.gov.android.ui.componentsv2.row.RowData
import uk.gov.android.ui.componentsv2.row.RowTrailingIcon
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.spacingTriple

internal class LeftAlignedScreenContentProvider :
    PreviewParameterProvider<LeftAlignedScreenContent> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    internal val textShort = LoremIpsum(5).values.first()
    private val textLong = LoremIpsum(25).values.first()
    private val textExtraLong = LoremIpsum(100).values.first()
    private val supportingText =
        "Check if your passport has a biometric chip, look for the " +
            "rectangular biometric chip symbol on the front cover"
    private val warning = "You cannot use your passport if it has expired"
    private val selectionItems = persistentListOf("Yes", "No")
    private val imageDescription = "Image description"
    private val secondaryButtonText = "Secondary Button"
    private val primaryButtonText = "Primary Button"

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
                        withStyle(
                            SpanStyle(fontFamily = ExtraTypography.bodyLargeBold.fontFamily),
                        ) {
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
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBody.BulletList(
                    persistentListOf(
                        ListItem("Bullet 1"),
                        ListItem("Bullet 2"),
                        ListItem(textShort),
                    ),
                ),
                LeftAlignedScreenBody.SecondaryButton(
                    secondaryButtonText,
                    {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
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
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
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
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBody.Selection(
                    items = selectionItems,
                    selectedItem = null,
                    onItemSelected = {},
                    title = GdsRadiosTitle(text = "Title", style = GdsHeadingStyle.Title1),
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
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
                    imageDescription,
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
                    imageDescription,
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
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = primaryButtonText,
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
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = primaryButtonText,
        ),
        LeftAlignedScreenContent(
            title = title,
            primaryButton = primaryButtonText,
        ),
        LeftAlignedScreenContent(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
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
                LeftAlignedScreenBody.BulletList(
                    items = persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
                    ),
                    title = ListTitle("Bullet List Title", TitleType.Text),
                ),
                LeftAlignedScreenBody.SecondaryButton(
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
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
                LeftAlignedScreenBody.RowList(
                    rowData = persistentListOf(
                        RowData(
                            title = "Basic Row component",
                            onClick = {},
                        ),
                        RowData(
                            title = "Row component with click disabled",
                            clickEnabled = false,
                            onClick = {},
                        ),
                        RowData(
                            title = "Full Row component",
                            subtitle = "Subtitle String",
                            leadingImage = Image(
                                drawable = componentsv2R.drawable.placeholder_leading_image,
                                contentDescription = "",
                            ),
                            scaleLeadingImageWithFontSize = true,
                            trailingText = "100+",
                            trailingIcon = RowTrailingIcon.OpenInNew(),
                            onClick = {},
                        ),
                    ),
                ),
                LeftAlignedScreenBody.SecondaryButton(
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
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
