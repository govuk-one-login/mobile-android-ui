package uk.gov.android.ui.patterns.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.launch

object ModifierExtensions {
    internal val HasKeyboardScrollKey: SemanticsPropertyKey<Boolean> =
        SemanticsPropertyKey("IsScrollableWithKeyboard")
    internal var SemanticsPropertyReceiver.hasKeyboardScroll: Boolean by HasKeyboardScrollKey

    /**
     * Adds a downwards and upwards scroll when a keyboard down or up arrow is pressed.
     *
     * If you need to check for the presence of this modifier in tests, use the
     * `hasKeyboardScroll` matcher from the test fixtures.
     *
     * @param scrollState [ScrollState] represents the list state
     * @return augmented [Modifier]
     */
    @Composable
    fun Modifier.keyboardScroll(scrollState: ScrollableState): Modifier {
        val coroutineScope = rememberCoroutineScope()
        val interactionSource = remember { MutableInteractionSource() }
        val focusRequester = remember { FocusRequester() }
        return this
            .semantics { hasKeyboardScroll = true }
            .onKeyEvent {
                when {
                    it.type == KeyEventType.KeyDown && it.key == Key.DirectionDown &&
                        scrollState.canScrollForward -> {
                        coroutineScope.launch {
                            scrollState.animateScrollBy(
                                SCROLL_MULTIPLIER * scrollState.viewportHeight(),
                            )
                        }
                        true
                    }

                    it.type == KeyEventType.KeyDown && it.key == Key.DirectionUp &&
                        scrollState.canScrollBackward -> {
                        coroutineScope.launch {
                            scrollState.animateScrollBy(
                                -SCROLL_MULTIPLIER * scrollState.viewportHeight(),
                            )
                        }
                        true
                    }

                    else -> false
                }
            }
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource)
    }

    /**
     * Adds a downwards and upwards scroll when a keyboard down or up arrow is pressed.
     *
     * @param scrollState [ScrollState] represents the list state
     * @return augmented [Modifier]
     */
    @Deprecated(
        message = "Replace with keyboardScroll. Due to be removed 13th July 2026.",
        replaceWith = ReplaceWith("keyboardScroll(scrollState)"),
        level = DeprecationLevel.WARNING,
    )
    @Composable
    fun Modifier.bringIntoView(scrollState: ScrollableState): Modifier =
        keyboardScroll(scrollState)

    private fun ScrollableState.viewportHeight(): Float = when (this) {
        is LazyListState -> layoutInfo.viewportSize.height.toFloat()
        is ScrollState -> viewportSize.toFloat()
        else -> error("scrollable type not yet supported")
    }

    private const val SCROLL_MULTIPLIER = 0.4f
}
