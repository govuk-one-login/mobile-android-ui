package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

/**
 * Convenience function for padding that should also consume window insets.
 */
fun Modifier.windowInsetsPadding(paddingValues: PaddingValues): Modifier = padding(paddingValues)
    .consumeWindowInsets(paddingValues)
