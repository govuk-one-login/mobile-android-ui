package uk.gov.android.ui.patterns.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object ModifierExtensions {
    internal val HasKeyboardScrollKey: SemanticsPropertyKey<Boolean> =
        SemanticsPropertyKey("IsScrollableWithKeyboard")
    internal var SemanticsPropertyReceiver.hasKeyboardScroll: Boolean by HasKeyboardScrollKey

    /**
     * Adds a downwards and upwards scroll when a keyboard down or up arrow is pressed.
     *
     * This modifier intercepts arrow-key events that bubble up from any focused child inside the
     * container. It does **not** make the container itself focusable, so keyboard focus always
     * lands on a real interactive child (button, text field, etc.) and the focus indicator is
     * never lost between elements.
     *
     * If you need to check for the presence of this modifier in tests, use the
     * `hasKeyboardScroll` matcher from the test fixtures.
     *
     * @param scrollState [ScrollableState] represents the scroll state
     * @return augmented [Modifier]
     */
    @Composable
    fun Modifier.keyboardScroll(scrollState: ScrollableState): Modifier {
        val coroutineScope = rememberCoroutineScope()
        return this
            .semantics { hasKeyboardScroll = true }
            .onPreviewKeyEvent { scrollOnArrow(it, scrollState, coroutineScope) }
    }

    private fun scrollOnArrow(
        event: KeyEvent,
        scrollState: ScrollableState,
        coroutineScope: CoroutineScope,
    ): Boolean {
        if (event.type != KeyEventType.KeyDown) return false
        return when {
            event.key == Key.DirectionDown && scrollState.canScrollForward -> {
                coroutineScope.launch {
                    scrollState.animateScrollBy(SCROLL_MULTIPLIER * scrollState.viewportHeight())
                }
                true
            }

            event.key == Key.DirectionUp && scrollState.canScrollBackward -> {
                coroutineScope.launch {
                    scrollState.animateScrollBy(-SCROLL_MULTIPLIER * scrollState.viewportHeight())
                }
                true
            }

            else -> false
        }
    }

    private fun ScrollableState.viewportHeight(): Float = when (this) {
        is LazyListState -> layoutInfo.viewportSize.height.toFloat()
        is ScrollState -> viewportSize.toFloat()
        else -> error("scrollable type not yet supported")
    }

    private const val SCROLL_MULTIPLIER = 0.4f
}
