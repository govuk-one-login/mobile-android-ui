package uk.gov.android.ui.patterns.notlazyleftalignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.PersistentList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsSelection
import uk.gov.android.ui.componentsv2.inputs.radio.RadioSelectionTitle
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.row.RowData
import uk.gov.android.ui.componentsv2.row.RowList
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.warning.GdsWarningText
import uk.gov.android.ui.theme.dividerThickness

internal data class NotLazyLeftAlignedScreenContentV2(
    val title: String,
    val body: PersistentList<NotLazyLeftAlignedScreenBodyV2>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val primaryButtonIsEnabled: Boolean = true,
    val secondaryButton: String? = null,
)

sealed class NotLazyLeftAlignedScreenBodyV2 {
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
        val enabled: Boolean = true,
        val modifier: Modifier = Modifier,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class AnnotatedText(
        val text: AnnotatedString,
        val modifier: Modifier = Modifier,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class Text(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier,
        val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
        val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class Warning(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class BulletList(
        val items: PersistentList<ListItem>,
        val title: ListTitle? = null,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class NumberedList(val list: PersistentList<ListItem>) : NotLazyLeftAlignedScreenBodyV2()

    data class Image(
        val image: Int,
        val contentDescription: String,
        val modifier: Modifier = Modifier,
        val contentScale: ContentScale = ContentScale.FillWidth,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class Selection(
        val items: PersistentList<String>,
        val selectedItem: Int?,
        val onItemSelected: (Int) -> Unit,
        val modifier: Modifier = Modifier,
        val title: RadioSelectionTitle? = null,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class Divider(
        val thickness: Dp = dividerThickness,
        val color: Color? = null,
        val modifier: Modifier = Modifier,
    ) : NotLazyLeftAlignedScreenBodyV2()

    data class RowList(
        val rowData: PersistentList<RowData>,
    ) : NotLazyLeftAlignedScreenBodyV2()
}

@Composable
internal fun NotLazyLeftAlignedScreenFromContentParamsV2(content: NotLazyLeftAlignedScreenContentV2) {
    NotLazyLeftAlignedScreen(
        title = { horizontalPadding ->
            GdsHeading(
                text = content.title,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
            )
        },
        body = { horizontalItemPadding ->
            toBodyContentV2(
                horizontalItemPadding = horizontalItemPadding,
                body = content.body,
            )
        },
        supportingText = content.supportingText?.let { text ->
            { horizontalPadding ->
                GdsSupportingText(
                    text = text,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
        },
        primaryButton = content.primaryButton?.let {
            {
                GdsButton(
                    text = it,
                    onClick = {},
                    buttonType = ButtonType.Primary,
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = content.primaryButtonIsEnabled,
                )
            }
        },
        secondaryButton = content.secondaryButton?.let {
            {
                GdsButton(
                    text = it,
                    onClick = {},
                    buttonType = ButtonType.Secondary,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        },
    )
}

/**
 * The toBodyContent function is an extension function on the LazyListScope that aims to
 * abstract away the repetitive logic used to render a Lazy list of NotLazyLeftAlignedScreenBody
 *
 * @param body [List<NotLazyLeftAlignedScreenBodyV2>?] represents the list of NotLazyLeftAlignedScreenBodyV2
 * @param horizontalItemPadding [Dp] represents the horizontal padding
 */
@Suppress("LongMethod", "CyclomaticComplexMethod")
@Composable
fun toBodyContentV2(
    body: PersistentList<NotLazyLeftAlignedScreenBodyV2>?,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEach {
        when (it) {
            is NotLazyLeftAlignedScreenBodyV2.BulletList -> {
                GdsBulletedList(
                    bulletListItems = it.items,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(itemPadding),
                    title = it.title,
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.NumberedList -> {
                GdsNumberedList(
                    numberedListItems = it.list,
                    modifier = Modifier.padding(itemPadding),
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.Image -> {
                Image(
                    painter = painterResource(it.image),
                    contentDescription = it.contentDescription,
                    contentScale = it.contentScale,
                    modifier = it.modifier,
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.Text -> {
                Text(
                    text = it.text,
                    modifier = it.modifier.padding(itemPadding),
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.AnnotatedText -> {
                toAnnotatedText(it, itemPadding)
            }

            is NotLazyLeftAlignedScreenBodyV2.Title -> {
                GdsHeading(
                    text = it.text,
                    modifier = it.modifier.padding(itemPadding),
                    style = it.style,
                    textAlign = it.textAlign,
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.Warning -> {
                GdsWarningText(
                    text = it.text,
                    modifier = it.modifier.padding(itemPadding),
                )
            }

            // TODO update button color with GdsThemeV2, once available
            // (https://github.com/govuk-one-login/mobile-android-ui/pull/293)
            is NotLazyLeftAlignedScreenBodyV2.SecondaryButton -> {
                val buttonType = if (it.showIcon) {
                    val contentColor = colorScheme.secondary
                    ButtonType.Icon(
                        buttonColors = customButtonColors(
                            contentColor = contentColor,
                            containerColor = colorScheme.background,
                        ),
                        iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
                        contentDescription = stringResource(R.string.opens_in_external_browser),
                    )
                } else {
                    ButtonType.Secondary
                }

                GdsButton(
                    text = it.text,
                    buttonType = buttonType,
                    onClick = it.onClick,
                    textAlign = TextAlign.Start,
                    contentPosition = Arrangement.Start,
                    modifier = it.modifier.padding(horizontal = horizontalItemPadding),
                    contentModifier = Modifier.fillMaxWidth(),
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.Selection -> {
                GdsSelection(
                    items = it.items,
                    selectedItem = it.selectedItem,
                    onItemSelected = it.onItemSelected,
                    modifier = it.modifier,
                    title = it.title,
                )
            }

            // TODO update color with GdsThemeV2 once available
            // (https://github.com/govuk-one-login/mobile-android-ui/pull/293)
            is NotLazyLeftAlignedScreenBodyV2.Divider -> {
                HorizontalDivider(
                    thickness = it.thickness,
                    color = it.color ?: colorScheme.surface,
                    modifier = it.modifier.padding(itemPadding),
                )
            }

            is NotLazyLeftAlignedScreenBodyV2.RowList -> {
                RowList(
                    rows = it.rowData,
                    horizontalPadding = itemPadding,
                )
            }
        }
    }
}

@Composable
private fun toAnnotatedText(
    it: NotLazyLeftAlignedScreenBodyV2.AnnotatedText,
    itemPadding: PaddingValues,
) {
    Text(
        text = it.text,
        modifier = it.modifier.padding(itemPadding),
        color = colorScheme.onBackground,
        style = MaterialTheme.typography.bodyLarge,
    )
}
