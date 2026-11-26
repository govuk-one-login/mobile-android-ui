package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ContentPreviews
import uk.gov.android.ui.theme.preview.DefaultPreview
import uk.gov.android.ui.theme.spacingDouble

/**
 * A card is a container that groups related content and actions about a single subject.
 *
 * [content] is a slot laid out in a [ColumnScope], so the card makes no assumptions about what it
 * contains. Text, icons, buttons, images, edge-to-edge headers and nested layouts are all
 * supported, and the card applies **no padding** of its own - spacing is the caller's
 * responsibility.
 *
 * @sample GdsCardPreview
 * @sample GdsCardWithBorderPreview
 * @sample GdsCardWithShadowPreview
 *
 * @param modifier Compose [Modifier] to apply to the card
 * @param shape the shape of the card's container, border and shadow - defaults to a corner radius
 *   of 16.dp, see [GdsCardDefaults.shape]
 * @param colors the container and content colours - defaults to the Design System card background,
 *   see [GdsCardDefaults.colors]
 * @param elevation the Material elevation of the card - defaults to none, see
 *   [GdsCardDefaults.elevation]. For the GDS shadow add [gdsCardShadow] to [modifier]
 * @param border the border drawn around the card - defaults to no border, see
 *   [GdsCardDefaults.border]
 * @param content the content of the card
 */
@Composable
fun GdsCard(
    modifier: Modifier = Modifier,
    shape: Shape = GdsCardDefaults.shape,
    colors: CardColors = GdsCardDefaults.colors(),
    elevation: CardElevation = GdsCardDefaults.elevation(),
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit,
) = Card(
    modifier = modifier,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = border,
    content = content,
)

/**
 * A card that handles click events, calling [onClick] when tapped.
 *
 * See the non-clickable [GdsCard] overload for details of the [content] slot and the defaults.
 *
 * @sample GdsCardClickablePreview
 *
 * @param onClick called when the card is clicked
 * @param modifier Compose [Modifier] to apply to the card
 * @param enabled whether the card responds to user input - when `false` the card is also disabled
 *   to accessibility services. No disabled state is specified for a GDS card, so this does not
 *   change the card's appearance, see [GdsCardDefaults.colors]
 * @param shape the shape of the card's container, border and shadow - defaults to a corner radius
 *   of 16.dp, see [GdsCardDefaults.shape]
 * @param colors the container and content colours - defaults to the Design System card background,
 *   see [GdsCardDefaults.colors]
 * @param elevation the Material elevation of the card - defaults to none, see
 *   [GdsCardDefaults.elevation]. For the GDS shadow add [gdsCardShadow] to [modifier]
 * @param border the border drawn around the card - defaults to no border, see
 *   [GdsCardDefaults.border]
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting interactions for the card
 * @param content the content of the card
 */
@Composable
fun GdsCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = GdsCardDefaults.shape,
    colors: CardColors = GdsCardDefaults.colors(),
    elevation: CardElevation = GdsCardDefaults.elevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) = Card(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = border,
    interactionSource = interactionSource,
    content = content,
)

@Composable
@PreviewLightDark
@ContentPreviews
internal fun GdsCardPreview() = DefaultPreview {
    GdsCard(modifier = Modifier.padding(spacingDouble)) {
        PreviewContent(title = "Card", body = "A card with the default styling.")
    }
}

@Composable
@PreviewLightDark
internal fun GdsCardWithBorderPreview() = DefaultPreview {
    GdsCard(
        modifier = Modifier.padding(spacingDouble),
        border = GdsCardDefaults.border(),
    ) {
        PreviewContent(title = "Card", body = "A card with a border.")
    }
}

@Composable
@PreviewLightDark
internal fun GdsCardWithShadowPreview() = DefaultPreview {
    GdsCard(
        modifier = Modifier
            .padding(spacingDouble)
            .gdsCardShadow(),
    ) {
        PreviewContent(title = "Card", body = "A card with a shadow.")
    }
}

@Composable
@Preview
internal fun GdsCardClickablePreview() = DefaultPreview {
    GdsCard(
        onClick = {},
        modifier = Modifier.padding(spacingDouble),
    ) {
        PreviewContent(title = "Card", body = "A card that handles taps.")
    }
}

@Composable
private fun PreviewContent(title: String, body: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacingDouble),
        verticalArrangement = Arrangement.spacedBy(spacingDouble),
    ) {
        Text(text = title, style = Typography.headlineMedium)
        Text(text = body, style = Typography.bodyLarge)
    }
}
