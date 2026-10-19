package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
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
import uk.gov.android.ui.componentsv2.warning.GdsWarningText
import uk.gov.android.ui.theme.spacingDouble

/**
 *  A data class containing the composables to be arranged and configured,
 *  as well as other configurable parameters.
 *
 * @param titleContent [Composable] first composable in column (optional).
 * @param bodyContent [LazyListScope] the rest of the composables (optional).
 * @param horizontalPadding [Dp] defaults to spacingDouble (optional).
 * @param verticalPadding [Dp] defaults to spacingDouble (optional).
 * @param modifier A [Modifier] to be applied to the root composable of the MainContent (optional).
 */
data class MainContentParameters(
    val titleContent: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    val bodyContent: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    val horizontalPadding: Dp = spacingDouble,
    val verticalPadding: Dp = spacingDouble,
    val modifier: Modifier = Modifier,
) {
    companion object {
        @Composable
        internal fun empty() = MainContentParameters()

        @Composable
        fun generateDefault(
            title: String?,
            body: ImmutableList<LeftAlignedScreenBody>?,
            horizontalPadding: Dp = spacingDouble,
            verticalPadding: Dp = spacingDouble,
        ): MainContentParameters = MainContentParameters(
            titleContent = title?.let { text ->
                { horizontalPadding ->
                    GdsHeading(
                        text = text,
                        modifier = Modifier.padding(horizontal = horizontalPadding),
                        textAlign = GdsHeadingAlignment.LeftAligned,
                    )
                }
            },
            bodyContent = { horizontalPadding ->
                body?.forEach {
                    item {
                        it.GenerateDefault(horizontalPadding)
                    }
                }
            },
            horizontalPadding = horizontalPadding,
            verticalPadding = verticalPadding,
        )

        @SuppressLint("ComposeModifierMissing")
        @Suppress("LongMethod")
        @Composable
        fun LeftAlignedScreenBody.GenerateDefault(horizontalItemPadding: Dp) = when (this) {
            is LeftAlignedScreenBody.BulletList -> {
                GdsBulletedList(
                    bulletListItems = items,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalItemPadding),
                    title = title,
                )
            }

            is LeftAlignedScreenBody.NumberedList -> {
                GdsNumberedList(
                    title = title,
                    numberedListItems = list,
                    modifier = Modifier.padding(horizontal = horizontalItemPadding),
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
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            is LeftAlignedScreenBody.AnnotatedText -> {
                Text(
                    text = text,
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            is LeftAlignedScreenBody.Title -> {
                GdsHeading(
                    text = text,
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
                    style = style,
                    textAlign = textAlign,
                )
            }

            is LeftAlignedScreenBody.Warning -> {
                GdsWarningText(
                    text = text,
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
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
                    textAlign = TextAlign.Start,
                    contentPosition = Arrangement.Start,
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
                    contentModifier = Modifier.fillMaxWidth(),
                )
            }

            is LeftAlignedScreenBody.Selection -> {
                GdsRadios(
                    items = items,
                    selectedItem = selectedItem,
                    onItemSelected = onItemSelected,
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
                    title = title,
                )
            }

            is LeftAlignedScreenBody.Divider -> {
                HorizontalDivider(
                    thickness = thickness,
                    color = color
                        .takeUnless { it == Color.Unspecified }
                        ?: colorScheme.onSurface,
                    modifier = modifier.padding(horizontal = horizontalItemPadding),
                )
            }

            is LeftAlignedScreenBody.RowList -> {
                RowList(
                    rows = rowData,
                    horizontalPadding = PaddingValues(horizontal = horizontalItemPadding),
                )
            }
        }
    }
}
