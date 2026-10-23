package uk.gov.android.ui.patterns.utils

import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_UP
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Suppress("LabeledExpression")
@Composable
internal fun Modifier.keyboardScrollable(
    scrollState: ScrollableState,
    moveFocusOnBoundary: Boolean,
    scrollAmount: Int = 100,
): Modifier {
    val scope: CoroutineScope = rememberCoroutineScope()
    val focusManager: FocusManager = LocalFocusManager.current
    return this.onPreviewKeyEvent { keyEvent ->
        dispatchKeyEvent(
            keyEvent,
            scrollState,
            scrollAmount,
            moveFocusOnBoundary,
            focusManager,
            scope,
        )
    }
}

@Suppress("LongParameterList")
internal fun dispatchKeyEvent(
    keyEvent: KeyEvent,
    scrollState: ScrollableState,
    scrollAmount: Int,
    moveFocusOnBoundary: Boolean,
    focusManager: FocusManager,
    coroutineScope: CoroutineScope,
): Boolean = when {
    keyEvent.isActionDown() && keyEvent.isDpadUp() ->
        handleScrollUp(scrollState, scrollAmount, moveFocusOnBoundary, focusManager, coroutineScope)

    keyEvent.isActionDown() && keyEvent.isDpadDown() ->
        handleScrollDown(
            scrollState,
            scrollAmount,
            moveFocusOnBoundary,
            focusManager,
            coroutineScope,
        )

    keyEvent.isActionDown() && keyEvent.isTabForward() && !moveFocusOnBoundary ->
        focusManager.moveFocus(FocusDirection.Next)

    keyEvent.isActionDown() && keyEvent.isTabBackward() && !moveFocusOnBoundary ->
        focusManager.moveFocus(FocusDirection.Previous)

    else -> false
}

internal fun handleScrollUp(
    scrollState: ScrollableState,
    scrollAmount: Int,
    moveFocusOnBoundary: Boolean,
    focusManager: FocusManager,
    coroutineScope: CoroutineScope,
): Boolean {
    if (!scrollState.canScrollBackward) {
        if (moveFocusOnBoundary) focusManager.moveFocus(FocusDirection.Up)
        return false
    } else {
        coroutineScope.launch {
            scrollState.animateScrollBy(-scrollAmount.toFloat())
//            scrollState.scroll {
//                scrollBy(- scrollAmount.toFloat())
//            }
        }
    }
    return true
}

internal fun handleScrollDown(
    scrollState: ScrollableState,
    scrollAmount: Int,
    moveFocusOnBoundary: Boolean,
    focusManager: FocusManager,
    coroutineScope: CoroutineScope,
): Boolean {
    if (!scrollState.canScrollForward) {
        if (moveFocusOnBoundary) focusManager.moveFocus(FocusDirection.Down)
        return false
    } else {
        coroutineScope.launch {
            scrollState.animateScrollBy(scrollAmount.toFloat())
//            scrollState.scroll {
//                scrollBy(scrollAmount.toFloat())
//            }
        }
    }
    return true
}

private fun KeyEvent.isActionDown() = nativeKeyEvent.action == ACTION_DOWN

private fun KeyEvent.isTabForward() = key == Key.Tab && !isShiftPressed

private fun KeyEvent.isTabBackward() = key == Key.Tab && isShiftPressed

private fun KeyEvent.isDpadDown() = nativeKeyEvent.keyCode == KEYCODE_DPAD_DOWN

private fun KeyEvent.isDpadUp() = nativeKeyEvent.keyCode == KEYCODE_DPAD_UP

