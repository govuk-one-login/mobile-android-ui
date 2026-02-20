package uk.gov.android.ui.patterns.leftalignedscreen.v3

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
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType
import uk.gov.android.ui.componentsv2.row.RowData
import uk.gov.android.ui.componentsv2.row.RowTrailingIcon
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.theme.spacingTriple
import uk.gov.android.ui.componentsv2.R as componentsV2R

internal class LeftAlignedScreenContentProviderV3 :
    PreviewParameterProvider<LeftAlignedScreenContentV3> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    internal val textShort = LoremIpsum(5).values.first()
    private val textLong = LoremIpsum(25).values.first()
    private val textExtraLong = LoremIpsum(100).values.first()
    private val supportingText =
        "Check if your passport has a biometric chip, look for the rectangular biometric chip symbol on the front cover"
    private val warning = "You cannot use your passport if it has expired"
    private val selectionItems = persistentListOf("Yes", "No")
    private val imageDescription = "Image description"
    private val secondaryButtonText = "Secondary Button"
    private val primaryButtonText = "Primary Button"

    override val values = sequenceOf(
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBodyV3.AnnotatedText(
                    buildAnnotatedString {
                        append(textShort)
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" 26 June 2024 at 2:56pm (UK time)")
                        }
                        append(". $textShort")
                    },
                ),
                LeftAlignedScreenBodyV3.Title(
                    text = "Title2 - Left Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.LeftAligned,
                ),
                LeftAlignedScreenBodyV3.Title(
                    text = "Title2 - Center Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.CenterAligned,
                ),
                LeftAlignedScreenBodyV3.Title(
                    text = "Title2 - Right Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.RightAligned,
                ),
                LeftAlignedScreenBodyV3.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBodyV3.BulletList(
                    persistentListOf(
                        ListItem("Bullet 1"),
                        ListItem("Bullet 2"),
                        ListItem(textShort),
                    ),
                ),
                LeftAlignedScreenBodyV3.SecondaryButton(
                    secondaryButtonText,
                    {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBodyV3.Divider(),
                LeftAlignedScreenBodyV3.NumberedList(
                    persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
                    ),
                ),
                LeftAlignedScreenBodyV3.SecondaryButton(
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                ),
                LeftAlignedScreenBodyV3.Text(
                    textLong,
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                ),
                LeftAlignedScreenBodyV3.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBodyV3.Selection(
                    items = selectionItems,
                    selectedItem = null,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            primaryButtonIsEnabled = false,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                ),
                LeftAlignedScreenBodyV3.Text(
                    textLong,
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                    Modifier.padding(top = spacingTriple),
                ),
                LeftAlignedScreenBodyV3.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                LeftAlignedScreenBodyV3.SecondaryButton(
                    "Read more about the types of photo ID you can use",
                    {},
                ),
                LeftAlignedScreenBodyV3.Selection(
                    items = selectionItems,
                    selectedItem = 1,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                ),
                LeftAlignedScreenBodyV3.Text(
                    textLong,
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBodyV3.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                ),
                LeftAlignedScreenBodyV3.Text(
                    textLong,
                    modifier = Modifier.padding(
                        bottom = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBodyV3.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = primaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                ),
                LeftAlignedScreenBodyV3.Text(
                    textExtraLong,
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBodyV3.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = primaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            primaryButton = primaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBodyV3.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                LeftAlignedScreenBodyV3.Divider(),
                LeftAlignedScreenBodyV3.BulletList(
                    items = persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
                    ),
                    title = ListTitle("Bullet List Title", TitleType.Text),
                ),
                LeftAlignedScreenBodyV3.SecondaryButton(
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        LeftAlignedScreenContentV3(
            title = title,
            body = persistentListOf(
                LeftAlignedScreenBodyV3.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                LeftAlignedScreenBodyV3.RowList(
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
                                drawable = componentsV2R.drawable.placeholder_leading_image,
                                contentDescription = "",
                            ),
                            scaleLeadingImageWithFontSize = true,
                            trailingText = "100+",
                            trailingIcon = RowTrailingIcon.OpenInNew(),
                            onClick = {},
                        ),
                    ),
                ),
                LeftAlignedScreenBodyV3.SecondaryButton(
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

internal class LeftAlignedScreenContentAccessibilityProviderV3 :
    PreviewParameterProvider<LeftAlignedScreenContentV3> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val textExtraLong = LoremIpsum(50).values.first()

    override val values: Sequence<LeftAlignedScreenContentV3> = sequenceOf(
        LeftAlignedScreenContentV3(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}
