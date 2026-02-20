package uk.gov.android.ui.patterns.utils

import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_UP
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Modifier.keyboardScrollable(
    scrollState: ScrollState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scrollAmount: Int = 100,
): Modifier =
    this.onPreviewKeyEvent { keyEvent ->
        if (keyEvent.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false

        val topPosition = 0
        val bottomPosition = scrollState.maxValue

        when (keyEvent.nativeKeyEvent.keyCode) {
            KEYCODE_DPAD_UP -> {
                if (scrollState.value == topPosition) {
                    false
                } else {
                    coroutineScope.launch {
                        val newPosition = (scrollState.value - scrollAmount).coerceAtLeast(topPosition)
                        scrollState.animateScrollTo(newPosition)
                    }
                    true
                }
            }

            KEYCODE_DPAD_DOWN -> {
                if (scrollState.value == bottomPosition) {
                    false
                } else {
                    coroutineScope.launch {
                        val newPosition = (scrollState.value + scrollAmount).coerceAtMost(bottomPosition)
                        scrollState.animateScrollTo(newPosition)
                    }
                    true
                }
            }

            else -> false
        }
    }