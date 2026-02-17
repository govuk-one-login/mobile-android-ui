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
import kotlinx.collections.immutable.immutableListOf
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
import uk.gov.android.ui.componentsv2.R as componentsv2R

internal class NotLazyLeftAlignedScreenContentProviderV2 :
    PreviewParameterProvider<NotLazyLeftAlignedScreenContentV2> {
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
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.AnnotatedText(
                    buildAnnotatedString {
                        append(textShort)
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" 26 June 2024 at 2:56pm (UK time)")
                        }
                        append(". $textShort")
                    },
                ),
                NotLazyLeftAlignedScreenBodyV2.Title(
                    text = "Title2 - Left Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.LeftAligned,
                ),
                NotLazyLeftAlignedScreenBodyV2.Title(
                    text = "Title2 - Center Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.CenterAligned,
                ),
                NotLazyLeftAlignedScreenBodyV2.Title(
                    text = "Title2 - Right Aligned",
                    style = GdsHeadingStyle.Title2,
                    textAlign = GdsHeadingAlignment.RightAligned,
                ),
                NotLazyLeftAlignedScreenBodyV2.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                NotLazyLeftAlignedScreenBodyV2.BulletList(
                    persistentListOf(
                        ListItem("Bullet 1"),
                        ListItem("Bullet 2"),
                        ListItem(textShort),
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.SecondaryButton(
                    secondaryButtonText,
                    {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBodyV2.Divider(),
                NotLazyLeftAlignedScreenBodyV2.NumberedList(
                    persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.SecondaryButton(
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textLong,
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                ),
                NotLazyLeftAlignedScreenBodyV2.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                NotLazyLeftAlignedScreenBodyV2.Selection(
                    items = selectionItems,
                    selectedItem = null,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            primaryButtonIsEnabled = false,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textLong,
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                    Modifier.padding(top = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBodyV2.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
                NotLazyLeftAlignedScreenBodyV2.SecondaryButton(
                    "Read more about the types of photo ID you can use",
                    {},
                ),
                NotLazyLeftAlignedScreenBodyV2.Selection(
                    items = selectionItems,
                    selectedItem = 1,
                    onItemSelected = {},
                ),
            ),
            supportingText = supportingText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textLong,
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBodyV2.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textLong,
                    modifier = Modifier.padding(
                        bottom = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBodyV2.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = primaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                ),
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textExtraLong,
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBodyV2.Image(
                    R.drawable.preview__gdsvectorimage,
                    imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                ),
            ),
            primaryButton = primaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            primaryButton = primaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.Warning(
                    warning,
                    Modifier.padding(vertical = spacingTriple),
                ),
                NotLazyLeftAlignedScreenBodyV2.Divider(),
                NotLazyLeftAlignedScreenBodyV2.BulletList(
                    items = persistentListOf(
                        ListItem("Number 1"),
                        ListItem("Number 2"),
                        ListItem(textShort),
                    ),
                    title = ListTitle("Bullet List Title", TitleType.Text),
                ),
                NotLazyLeftAlignedScreenBodyV2.SecondaryButton(
                    secondaryButtonText,
                    {},
                    showIcon = true,
                ),
            ),
            supportingText = supportingText,
            primaryButton = primaryButtonText,
            secondaryButton = secondaryButtonText,
        ),
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            body = persistentListOf(
                NotLazyLeftAlignedScreenBodyV2.Text(
                    textShort,
                    modifier = Modifier.padding(
                        top = spacingTriple,
                    ),
                ),
                NotLazyLeftAlignedScreenBodyV2.RowList(
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
                NotLazyLeftAlignedScreenBodyV2.SecondaryButton(
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

internal class NotLazyLeftAlignedScreenContentAccessibilityProviderV2 :
    PreviewParameterProvider<NotLazyLeftAlignedScreenContentV2> {
    private val title = "Do you have a UK passport or passport with a biometric chip?"
    private val textExtraLong = LoremIpsum(50).values.first()

    override val values: Sequence<NotLazyLeftAlignedScreenContentV2> = sequenceOf(
        NotLazyLeftAlignedScreenContentV2(
            title = title,
            supportingText = "Supporting Text - $textExtraLong",
            primaryButton = "Primary Button",
            secondaryButton = "Secondary Button",
        ),
    )
}
