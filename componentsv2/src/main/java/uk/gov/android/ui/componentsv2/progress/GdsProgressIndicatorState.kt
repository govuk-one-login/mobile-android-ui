package uk.gov.android.ui.componentsv2.progress

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

/**
 * The state for [GdsProgressIndicator] which transitions over time.
 *
 * Create instances with [rememberProgressIndicatorState].
 *
 * @param initialWaitedFor The [ProgressWaitLength] to show initially
 */
@Stable
class GdsProgressIndicatorState(initialWaitedFor: ProgressWaitLength = ProgressWaitLength.Short) {
    var waitedFor: ProgressWaitLength by mutableStateOf(initialWaitedFor)
        private set

    suspend fun runAnimation() {
        if (waitedFor == ProgressWaitLength.Short) {
            delay(GdsProgressIndicatorDefaults.ShortDuration)
            waitedFor = ProgressWaitLength.Long
        }
        if (waitedFor == ProgressWaitLength.Long) {
            delay(GdsProgressIndicatorDefaults.LongDuration)
            waitedFor = ProgressWaitLength.Longer
        }
    }

    companion object {
        fun Saver(): Saver<GdsProgressIndicatorState, ProgressWaitLength> = Saver(
            save = { curState -> curState.waitedFor },
            restore = { savedPhase -> GdsProgressIndicatorState(savedPhase) },
        )
    }
}

/**
 * @param initialPhase The [ProgressWaitLength] to display initially
 */
@Composable
fun rememberProgressIndicatorState(
    initialPhase: ProgressWaitLength = ProgressWaitLength.Short,
): GdsProgressIndicatorState = rememberSaveable(
    saver = GdsProgressIndicatorState.Saver(),
) {
    GdsProgressIndicatorState(initialWaitedFor = initialPhase)
}
