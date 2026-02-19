package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.launch
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
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenConstants.SCROLL_MULTIPLIER
import uk.gov.android.ui.theme.dividerThickness

internal data class LeftAlignedScreenContentV3(
    val title: String,
    val body: PersistentList<LeftAlignedScreenBodyV3>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val primaryButtonIsEnabled: Boolean = true,
    val secondaryButton: String? = null,
)

sealed class LeftAlignedScreenBodyV3 {
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
        val enabled: Boolean = true,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBodyV3()

    data class AnnotatedText(
        val text: AnnotatedString,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBodyV3()

    data class Text(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBodyV3()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier,
        val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
        val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    ) : LeftAlignedScreenBodyV3()

    data class Warning(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBodyV3()

    data class BulletList(
        val items: PersistentList<ListItem>,
        val title: ListTitle? = null,
    ) : LeftAlignedScreenBodyV3()

    data class NumberedList(val list: PersistentList<ListItem>) : LeftAlignedScreenBodyV3()

    data class Image(
        val image: Int,
        val contentDescription: String,
        val modifier: Modifier = Modifier,
        val contentScale: ContentScale = ContentScale.FillWidth,
    ) : LeftAlignedScreenBodyV3()

    data class Selection(
        val items: PersistentList<String>,
        val selectedItem: Int?,
        val onItemSelected: (Int) -> Unit,
        val modifier: Modifier = Modifier,
        val title: RadioSelectionTitle? = null,
    ) : LeftAlignedScreenBodyV3()

    data class Divider(
        val thickness: Dp = dividerThickness,
        val color: Color? = null,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBodyV3()

    data class RowList(
        val rowData: PersistentList<RowData>,
    ) : LeftAlignedScreenBodyV3()
}

data class LeftAlignedScreenButtonConfiguration(
    val text: String,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val enabled: Boolean = true,
)

@Composable
internal fun LeftAlignedScreenV3FromContentParamsV3(content: LeftAlignedScreenContentV3) {
    LeftAlignedScreenV3(
        title = { horizontalPadding ->
            GdsHeading(
                text = content.title,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
            )
        },
        body = { horizontalItemPadding ->
            toBodyContentV3(
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
 * The toBodyContent function aims to abstract away the repetitive logic used to render
 * [LeftAlignedScreenBodyV3]
 *
 * @param body nullable list of [LeftAlignedScreenBodyV3]
 * @param horizontalItemPadding horizontal padding in [Dp]
 */
@Suppress("LongMethod", "CyclomaticComplexMethod")
@Composable
fun toBodyContentV3(
    body: PersistentList<LeftAlignedScreenBodyV3>?,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEach {
        when (it) {
            is LeftAlignedScreenBodyV3.BulletList -> {
                GdsBulletedList(
                    bulletListItems = it.items,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(itemPadding),
                    title = it.title,
                )
            }

            is LeftAlignedScreenBodyV3.NumberedList -> {
                GdsNumberedList(
                    numberedListItems = it.list,
                    modifier = Modifier.padding(itemPadding),
                )
            }

            is LeftAlignedScreenBodyV3.Image -> {
                Image(
                    painter = painterResource(it.image),
                    contentDescription = it.contentDescription,
                    contentScale = it.contentScale,
                    modifier = it.modifier,
                )
            }

            is LeftAlignedScreenBodyV3.Text -> {
                Text(
                    text = it.text,
                    modifier = it.modifier.padding(itemPadding),
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            is LeftAlignedScreenBodyV3.AnnotatedText -> {
                toAnnotatedText(it, itemPadding)
            }

            is LeftAlignedScreenBodyV3.Title -> {
                GdsHeading(
                    text = it.text,
                    modifier = it.modifier.padding(itemPadding),
                    style = it.style,
                    textAlign = it.textAlign,
                )
            }

            is LeftAlignedScreenBodyV3.Warning -> {
                GdsWarningText(
                    text = it.text,
                    modifier = it.modifier.padding(itemPadding),
                )
            }

            // TODO update button color with GdsThemeV2, once available
            // (https://github.com/govuk-one-login/mobile-android-ui/pull/293)
            is LeftAlignedScreenBodyV3.SecondaryButton -> {
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

            is LeftAlignedScreenBodyV3.Selection -> {
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
            is LeftAlignedScreenBodyV3.Divider -> {
                HorizontalDivider(
                    thickness = it.thickness,
                    color = it.color ?: colorScheme.surface,
                    modifier = it.modifier.padding(itemPadding),
                )
            }

            is LeftAlignedScreenBodyV3.RowList -> {
                RowList(
                    rows = it.rowData,
                    horizontalPadding = itemPadding,
                )
            }
        }
    }
}

@Composable
fun Modifier.bringIntoView(scrollState: ScrollState): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    var focusEnabled by remember { mutableStateOf(true) }
    return this
        .onKeyEvent {
            if (it.type == KeyEventType.KeyUp && it.key == Key.DirectionDown) {
                if (scrollState.canScrollForward) {
                    coroutineScope.launch {
                        scrollState.animateScrollBy(SCROLL_MULTIPLIER * scrollState.viewportSize)
                    }
                }
                focusEnabled = false
            } else if (it.type == KeyEventType.KeyUp && it.key == Key.DirectionUp) {
                focusEnabled = true
            }
            false
        }
        .focusRequester(focusRequester)
        .focusable(enabled = focusEnabled, interactionSource = interactionSource)
}

@Composable
private fun toAnnotatedText(
    it: LeftAlignedScreenBodyV3.AnnotatedText,
    itemPadding: PaddingValues,
) {
    Text(
        text = it.text,
        modifier = it.modifier.padding(itemPadding),
        color = colorScheme.onBackground,
        style = MaterialTheme.typography.bodyLarge,
    )
}
