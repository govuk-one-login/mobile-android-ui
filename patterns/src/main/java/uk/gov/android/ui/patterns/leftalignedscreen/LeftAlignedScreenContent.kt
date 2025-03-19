package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.inputs.radio.GdsSelection
import uk.gov.android.ui.componentsv2.inputs.radio.RadioSelectionTitle
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.warning.GdsWarning
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

internal data class LeftAlignedScreenContent(
    val title: String,
    val body: List<LeftAlignedScreenBody>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val secondaryButton: String? = null,
)

sealed class LeftAlignedScreenBody {
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val modifier: Modifier = Modifier.padding(horizontal = spacingSingle),
    ) : LeftAlignedScreenBody()

    data class Text(
        val text: String,
        val modifier: Modifier = Modifier.padding(horizontal = spacingDouble),
    ) : LeftAlignedScreenBody()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier.padding(horizontal = spacingDouble),
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
)

@OptIn(UnstableDesignSystemAPI::class)
@Composable
internal fun LeftAlignedScreenFromContentParams(content: LeftAlignedScreenContent) {
    LeftAlignedScreen(
        title = { GdsHeading(content.title) },
        body = {
            toBodyContent(content.body)
        },
        supportingText = content.supportingText?.let {
            { GdsSupportingText(it) }
        },
        primaryButton = content.primaryButton?.let {
            {
                GdsButton(
                    text = it,
                    onClick = {},
                    buttonType = ButtonType.Primary,
                    modifier = Modifier
                        .fillMaxWidth(),
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
internal fun LazyListScope.toBodyContent(body: List<LeftAlignedScreenBody>?) {
    body?.forEach {
        when (it) {
            is LeftAlignedScreenBody.BulletList -> {
                item { GdsBulletedList(it.bullets) }
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
                        it.text,
                        it.modifier,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            is LeftAlignedScreenBody.Title -> {
                item {
                    GdsHeading(
                        it.text,
                        it.modifier,
                    )
                }
            }

            is LeftAlignedScreenBody.Warning -> {
                item {
                    GdsWarning(
                        it.text,
                        it.modifier,
                    )
                }
            }

            is LeftAlignedScreenBody.SecondaryButton -> {
                item {
                    Box(modifier = it.modifier) {
                        GdsButton(
                            it.text,
                            ButtonType.Secondary,
                            it.onClick,
                            textAlign = TextAlign.Start,
                        )
                    }
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
