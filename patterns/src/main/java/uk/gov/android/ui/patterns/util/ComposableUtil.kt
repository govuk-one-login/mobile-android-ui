package uk.gov.android.ui.patterns.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

internal object ComposableUtil {
    @SuppressLint("ComposeContentEmitterReturningValues")
    @Composable
    fun isComposableHeightOverAThirdOfScreen(
        composable: @Composable () -> Unit,
    ): State<Boolean> {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val thresholdHeight = screenHeight * FULL_SCREEN / ONE_THIRD
        val density = LocalDensity.current
        val isOverThreshold = remember { mutableStateOf(false) }

        Layout(content = composable) { measurables, constraints ->
            if (measurables.isNotEmpty()) {
                val contentHeight = measurables.sumOf { it.measure(constraints).height }
                val contentHeightDp = with(density) { contentHeight.toDp() }
                isOverThreshold.value = contentHeightDp > thresholdHeight
            }

            layout(0, 0) {}
        }

        return isOverThreshold
    }

    private const val FULL_SCREEN = 1f
    private const val ONE_THIRD = 3f
}
