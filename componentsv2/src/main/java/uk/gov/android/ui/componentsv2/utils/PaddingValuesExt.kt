package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.coerceAtLeast

@Composable
fun PaddingValues.coerceAtLeast(other: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current

    return PaddingValues(
        start =
            calculateStartPadding(layoutDirection).coerceAtLeast(
                other.calculateStartPadding(layoutDirection),
            ),
        top = calculateTopPadding().coerceAtLeast(other.calculateTopPadding()),
        end =
            calculateEndPadding(layoutDirection).coerceAtLeast(
                other.calculateEndPadding(layoutDirection),
            ),
        bottom = calculateBottomPadding().coerceAtLeast(other.calculateBottomPadding()),
    )
}
