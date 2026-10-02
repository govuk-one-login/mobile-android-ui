package uk.gov.android.ui.componentsv2.progress

import androidx.compose.runtime.Immutable

/**
 * The three labels shown by [GdsProgressIndicator] over time.
 *
 * @param shortWait The initial label
 * @param longWait The label shown after waiting for some time
 * @param longerWait The label shown after waiting for a very long time
 */
@Immutable
data class ProgressLabels(val shortWait: String, val longWait: String, val longerWait: String) {
    fun get(waitLength: ProgressWaitLength) = when (waitLength) {
        ProgressWaitLength.Short -> shortWait
        ProgressWaitLength.Long -> longWait
        ProgressWaitLength.Longer -> longerWait
    }
}
