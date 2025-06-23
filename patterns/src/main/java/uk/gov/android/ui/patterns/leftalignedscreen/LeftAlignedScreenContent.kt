package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsSelection
import uk.gov.android.ui.componentsv2.inputs.radio.RadioSelectionTitle
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.warning.GdsWarningText
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.dividerThickness
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

internal data class LeftAlignedScreenContent(
    val title: String,
    val body: List<LeftAlignedScreenBody>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val primaryButtonIsEnabled: Boolean = true,
    val secondaryButton: String? = null,
)

sealed class LeftAlignedScreenBody {
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class AnnotatedText(
        val text: AnnotatedString,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class Text(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier,
        val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
        val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    ) : LeftAlignedScreenBody()

    data class Warning(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class BulletList(val bullets: ImmutableList<String>) : LeftAlignedScreenBody()
    data class NumberedList(val list: ImmutableList<ListItem>) : LeftAlignedScreenBody()

    data class Image(
        val image: Int,
        val contentDescription: String,
        val modifier: Modifier = Modifier,
        val contentScale: ContentScale = ContentScale.FillWidth,
    ) : LeftAlignedScreenBody()

    data class Selection(
        val items: ImmutableList<String>,
        val selectedItem: Int?,
        val onItemSelected: (Int) -> Unit,
        val modifier: Modifier = Modifier,
        val title: RadioSelectionTitle? = null,
    ) : LeftAlignedScreenBody()

    data class Divider(
        val thickness: Dp = dividerThickness,
        val color: Color? = null,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()
}

data class LeftAlignedScreenButton(
    val text: String,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val enabled: Boolean = true,
)

@OptIn(UnstableDesignSystemAPI::class)
@Composable
internal fun LeftAlignedScreenFromContentParams(content: LeftAlignedScreenContent) {
    LeftAlignedScreen(
        title = { horizontalPadding ->
            GdsHeading(
                text = content.title,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
            )
        },
        body = { horizontalItemPadding ->
            toBodyContent(
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

@OptIn(UnstableDesignSystemAPI::class)
@Suppress("LongMethod")
fun LazyListScope.toBodyContent(
    body: List<LeftAlignedScreenBody>?,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEach {
        when (it) {
            is LeftAlignedScreenBody.BulletList -> {
                item {
                    GdsBulletedList(
                        bulletListItems = it.bullets,
                        modifier = Modifier.padding(itemPadding),
                    )
                }
            }

            is LeftAlignedScreenBody.NumberedList -> {
                item {
                    GdsNumberedList(
                        numberedListItems = it.list,
                        modifier = Modifier.padding(itemPadding),
                    )
                }
            }

            is LeftAlignedScreenBody.Image -> {
                item {
                    Image(
                        painter = painterResource(it.image),
                        contentDescription = it.contentDescription,
                        contentScale = it.contentScale,
                        modifier = it.modifier,
                    )
                }
            }

            is LeftAlignedScreenBody.Text -> {
                item {
                    Text(
                        text = it.text,
                        modifier = it.modifier.padding(itemPadding),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            is LeftAlignedScreenBody.AnnotatedText -> {
                toAnnotatedText(it, itemPadding)
            }

            is LeftAlignedScreenBody.Title -> {
                item {
                    GdsHeading(
                        text = it.text,
                        modifier = it.modifier.padding(itemPadding),
                        style = it.style,
                        textAlign = it.textAlign,
                    )
                }
            }

            is LeftAlignedScreenBody.Warning -> {
                item {
                    GdsWarningText(
                        text = it.text,
                        modifier = it.modifier.padding(itemPadding),
                    )
                }
            }

            is LeftAlignedScreenBody.SecondaryButton -> {
                item {
                    GdsButton(
                        text = it.text,
                        buttonType = ButtonType.Secondary,
                        onClick = it.onClick,
                        textAlign = TextAlign.Start,
                        contentPosition = Arrangement.Start,
                        modifier = it.modifier.padding(
                            horizontal = horizontalItemPadding - buttonContentHorizontal,
                        ),
                    )
                }
            }

            is LeftAlignedScreenBody.Selection -> {
                item {
                    GdsSelection(
                        items = it.items,
                        selectedItem = it.selectedItem,
                        onItemSelected = it.onItemSelected,
                        modifier = it.modifier,
                        title = it.title,
                    )
                }
            }

            // TODO update color with GdsThemeV2 once available
            // (https://github.com/govuk-one-login/mobile-android-ui/pull/293)
            is LeftAlignedScreenBody.Divider -> {
                item {
                    HorizontalDivider(
                        thickness = it.thickness,
                        color = it.color ?: MaterialTheme.colorScheme.surface,
                        modifier = it.modifier.padding(itemPadding),
                    )
                }
            }
        }
    }
}

private fun LazyListScope.toAnnotatedText(
    it: LeftAlignedScreenBody.AnnotatedText,
    itemPadding: PaddingValues,
) {
    item {
        Text(
            text = it.text,
            modifier = it.modifier.padding(itemPadding),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

/**
 * Adds a downwards scroll when a keyboard down arrow is pressed
 *
 * @param scrollState [LazyListState] represents the list state
 * @return augmented [Modifier]
 */
@Composable
fun Modifier.bringIntoView(scrollState: LazyListState): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    var focusEnabled by remember { mutableStateOf(true) }
    return this
        .onKeyEvent {
            if (it.type == KeyEventType.KeyUp && it.key == Key.DirectionDown) {
                if (scrollState.canScrollForward) {
                    coroutineScope.launch {
                        scrollState.animateScrollBy(SCROLL_MULTIPLIER * scrollState.layoutInfo.viewportSize.height)
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

private const val SCROLL_MULTIPLIER = 0.8f
