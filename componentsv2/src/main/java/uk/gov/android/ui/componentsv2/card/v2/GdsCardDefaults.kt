package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.componentsv2.button.GdsButtonDefaults
import uk.gov.android.ui.theme.cardBorderWidth
import uk.gov.android.ui.theme.cardShadow
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.tileCornerRadius

/**
 * The GDS Design System defaults applied by [GdsCard].
 *
 * Each of these can be passed to [GdsCard] explicitly to override a single default while keeping
 * the others, or replaced entirely with the equivalent [CardDefaults] value to fall back to plain
 * Material 3 behaviour.
 */
object GdsCardDefaults {
    /**
     * The default shape of a card - a [RoundedCornerShape] with a radius of **16.dp**.
     */
    val shape: Shape = RoundedCornerShape(tileCornerRadius)

    /**
     * The default colours of a card - the **Card** background use case from the Design System.
     *
     * No disabled state is specified for a GDS card, so the disabled colours deliberately match
     * the enabled ones. Passing [enabled][GdsCard] as `false` therefore removes the click handling
     * without changing the card's appearance.
     *
     * @param containerColor the background colour of the card
     * @param contentColor the colour applied to content that does not set its own colour
     */
    @Composable
    fun colors(
        containerColor: Color = GdsLocalColorScheme.current.cardBackground,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
    ): CardColors = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = containerColor,
        disabledContentColor = contentColor,
    )

    /**
     * The colours of a clickable card while it is focused - the GDS focus state colours (yellow
     * container, black content), the same as GDS buttons. Applied automatically by the clickable
     * [GdsCard] when it has focus.
     *
     * @param containerColor the background colour of the focused card
     * @param contentColor the colour applied to content that does not set its own colour
     */
    @Composable
    fun focusedColors(
        containerColor: Color = GdsLocalColorScheme.current.focusState,
        contentColor: Color = GdsLocalColorScheme.current.focusStateContent,
    ): CardColors = colors(containerColor = containerColor, contentColor = contentColor)

    /**
     * The ripple shown when a clickable card is pressed (and, through its alpha, hovered or
     * dragged) - the GDS highlighted colour. Applied by the clickable [GdsCard].
     *
     * @param color the colour of the ripple
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun rippleConfiguration(
        color: Color = GdsLocalColorScheme.current.secondaryTextAndSymbolButtonHighlighted,
    ): RippleConfiguration = GdsButtonDefaults.gdsRippleConfig(color)

    /**
     * The ripple shown on a clickable card while it has focus - the GDS focus highlight. Applied
     * by the clickable [GdsCard].
     *
     * @param color the colour of the ripple
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun focusedRippleConfiguration(
        color: Color = GdsLocalColorScheme.current.focusButtonHighlighted,
    ): RippleConfiguration = GdsButtonDefaults.gdsRippleConfig(color)

    /**
     * The default elevation of a card - no shadow, matching the Material Design filled card
     * container elevation of `md.sys.elevation.level0`.
     */
    @Composable
    fun elevation(): CardElevation = CardDefaults.cardElevation()

    /**
     * The elevation of the shadow drawn by [gdsCardShadow] - **1.dp**, the same value used for
     * the shadow of the original cards.
     */
    val shadowElevation: Dp = cardShadow

    /**
     * The border to use when a card needs one - **1.dp** of the **Card** border colour from the
     * Design System, which is Grey 3 in both light and dark mode.
     *
     * This is not applied by default. Pass it to [GdsCard] as the `border` argument.
     *
     * @param width the thickness of the border
     * @param color the colour of the border
     */
    @Composable
    fun border(
        width: Dp = cardBorderWidth,
        color: Color = GdsLocalColorScheme.current.cardBorder,
    ): BorderStroke = BorderStroke(width, color)
}
