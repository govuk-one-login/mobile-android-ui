package uk.gov.android.ui.patterns.leftalignedscreen.v4

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.ButtonIcon
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.ExternalDestination
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadios
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.row.RowList
import uk.gov.android.ui.componentsv2.utils.windowInsetsPadding
import uk.gov.android.ui.componentsv2.warning.GdsWarningText

/**
 * Holds the main (scrollable) content of [LeftAlignedScreen] along with its layout configuration.
 *
 * @param titleContent the title slot, rendered as the first item in the column (optional).
 * @param bodyContent the body slot, a [ColumnScope] lambda contributing composables directly
 * (optional). Unlike the previous [LazyListScope]-based API, items are placed directly without
 * `item { }` wrappers.
 */
data class MainContentParameters(
    val titleContent: (@Composable (horizontalPadding: PaddingValues) -> Unit)? = null,
    val bodyContent: (
        @Composable ColumnScope.(
            horizontalItemPadding: PaddingValues,
        ) -> Unit
    )? = null,
) {
    companion object {
        @Composable
        fun generateDefault(
            title: String?,
            body: ImmutableList<LeftAlignedScreenBody>?,
        ): MainContentParameters = MainContentParameters(
            titleContent = title?.let { text ->
                { horizontalPadding ->
                    GdsHeading(
                        text = text,
                        modifier = Modifier.windowInsetsPadding(horizontalPadding),
                        textAlign = GdsHeadingAlignment.LeftAligned,
                    )
                }
            },
            bodyContent = { horizontalPadding ->
                body?.forEach {
                    it.GenerateDefault(horizontalPadding)
                }
            },
        )

        @SuppressLint("ComposeModifierMissing")
        @Suppress("LongMethod")
        @Composable
        fun LeftAlignedScreenBody.GenerateDefault(horizontalItemPadding: PaddingValues) =
            when (this) {
                is LeftAlignedScreenBody.BulletList -> {
                    GdsBulletedList(
                        bulletListItems = items,
                        modifier = Modifier
                            .fillMaxWidth()
                            .windowInsetsPadding(horizontalItemPadding),
                        title = title,
                    )
                }

                is LeftAlignedScreenBody.NumberedList -> {
                    GdsNumberedList(
                        title = title,
                        numberedListItems = list,
                        modifier = Modifier.windowInsetsPadding(horizontalItemPadding),
                    )
                }

                is LeftAlignedScreenBody.Image -> {
                    Image(
                        painter = painterResource(image),
                        contentDescription = contentDescription,
                        contentScale = contentScale,
                        modifier = modifier,
                    )
                }

                is LeftAlignedScreenBody.Text -> {
                    Text(
                        text = text,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                        color = colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                is LeftAlignedScreenBody.AnnotatedText -> {
                    Text(
                        text = text,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                        color = colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                is LeftAlignedScreenBody.Title -> {
                    GdsHeading(
                        text = text,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                        style = style,
                        textAlign = textAlign,
                    )
                }

                is LeftAlignedScreenBody.Warning -> {
                    GdsWarningText(
                        text = text,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                    )
                }

                is LeftAlignedScreenBody.SecondaryButton -> {
                    val icon = if (showIcon) {
                        ButtonIcon.opensExternalDestination(ExternalDestination.WebBrowser)
                    } else {
                        null
                    }

                    GdsButton(
                        text = text,
                        icon = icon,
                        buttonType = ButtonTypeV2.Secondary(),
                        onClick = onClick,
                        enabled = enabled,
                        textAlign = TextAlign.Start,
                        contentPosition = Arrangement.Start,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                        contentModifier = Modifier.fillMaxWidth(),
                    )
                }

                is LeftAlignedScreenBody.Selection -> {
                    GdsRadios(
                        items = items,
                        selectedItem = selectedItem,
                        onItemSelected = onItemSelected,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                        title = title,
                    )
                }

                is LeftAlignedScreenBody.Divider -> {
                    HorizontalDivider(
                        thickness = thickness,
                        color = color
                            .takeUnless { it == Color.Unspecified }
                            ?: colorScheme.onSurface,
                        modifier = modifier.windowInsetsPadding(horizontalItemPadding),
                    )
                }

                is LeftAlignedScreenBody.RowList -> {
                    RowList(
                        rows = rowData,
                        horizontalPadding = horizontalItemPadding,
                    )
                }
            }
    }
}
