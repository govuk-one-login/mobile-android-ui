package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Convenience function for padding that should also consume window insets.
 */
fun Modifier.windowInsetsPadding(paddingValues: PaddingValues): Modifier = padding(paddingValues)
    .consumeWindowInsets(paddingValues)

/**
 * The set of window insets that may be applied at the top level (i.e. screen level).
 *
 * Includes [WindowInsets.safeDrawing] and the vertical component of [WindowInsets.safeGestures].
 *
 * Components displayed within [WindowInsets.safeScreen] may apply [Modifier.safeComponentPadding].
 *
 * @see Modifier.safeScreenPadding
 */
val WindowInsets.Companion.safeScreen: WindowInsets
    @Composable get() =
        safeDrawing
            .union(safeGestures.only(WindowInsetsSides.Vertical))

/**
 * Modifier that applies and consumes [WindowInsets.safeScreen].
 */
@Composable
fun Modifier.safeScreenPadding() = windowInsetsPadding(WindowInsets.safeScreen)

/**
 * The set of window insets that may be applied per-component, if the component shouldn't display
 * edge to edge.
 *
 * Usually this should be used inside a screen pattern that already consumes [safeScreen] insets.
 *
 * @see Modifier.safeComponentPadding
 * @param minHorizontalPadding If given, the window insets will have a minimum horizontal component.
 */
@Composable
fun WindowInsets.Companion.safeComponent(minHorizontalPadding: Dp = 0.dp): WindowInsets =
    safeContent
        .union(WindowInsets(left = minHorizontalPadding, right = minHorizontalPadding))

/**
 * Like [safeComponent], but designed to be resolved eagerly before inset consumption is known.
 *
 * This padding can only be used within a parent that consumes [safeScreen] insets.
 *
 * @param minHorizontalPadding If given, the window insets will have a minimum horizontal component.
 */
@Composable
internal fun WindowInsets.Companion.safeComponentAsPadding(
    minHorizontalPadding: Dp = 0.dp,
): PaddingValues = safeComponent(minHorizontalPadding)
    .exclude(safeScreen)
    .asPaddingValues()

/**
 * Modifier that applies and consumes [WindowInsets.safeComponent].
 */
@Composable
fun Modifier.safeComponentPadding(minHorizontalPadding: Dp = 0.dp) =
    windowInsetsPadding(WindowInsets.safeComponent(minHorizontalPadding))
