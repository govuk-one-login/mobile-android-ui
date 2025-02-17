package uk.gov.android.ui.patterns.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

internal object ComposableUtil {
    @Composable
    fun isComposableHeightOverAThirdOfScreen(
        composable: @Composable () -> Unit,
    ): State<Boolean> {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val thresholdHeight = screenHeight * FULL_SCREEN / ONE_THIRD
        val density = LocalDensity.current
        val isOverThreshold = remember { mutableStateOf(false) }

        SubcomposeLayout { constraints ->
            val measurable = subcompose("content") { composable() }
            val placeable = measurable.first().measure(constraints)
            val contentHeight = with(density) { placeable.height.toDp() }
            isOverThreshold.value = contentHeight > thresholdHeight
            layout(0, 0) {}
        }

        return isOverThreshold
    }

    private const val FULL_SCREEN = 1f
    private const val ONE_THIRD = 3f
}
