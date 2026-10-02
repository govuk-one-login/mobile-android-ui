package uk.gov.android.ui.componentsv2.progress

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlin.time.Duration.Companion.seconds
import uk.gov.android.ui.componentsv2.R

object GdsProgressIndicatorDefaults {
    @Composable
    fun labels(
        short: String = stringResource(R.string.progress_indicator_loading_short),
        long: String = stringResource(R.string.progress_indicator_loading_long),
        longer: String = stringResource(R.string.progress_indicator_loading_longer),
    ): ProgressLabels = ProgressLabels(short, long, longer)

    internal val ShortDuration = 5.seconds
    internal val LongDuration = 5.seconds
}
