package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.inputs.radio.GdsSelection
import uk.gov.android.ui.componentsv2.inputs.radio.RadioSelectionTitle
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.warning.GdsWarningText
import uk.gov.android.ui.theme.buttonContentHorizontal
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

    data class Text(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class Warning(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class BulletList(val bullets: ImmutableList<String>) : LeftAlignedScreenBody()

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
internal fun LazyListScope.toBodyContent(
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

            is LeftAlignedScreenBody.Title -> {
                item {
                    GdsHeading(
                        text = it.text,
                        modifier = it.modifier.padding(itemPadding),
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
        }
    }
}
