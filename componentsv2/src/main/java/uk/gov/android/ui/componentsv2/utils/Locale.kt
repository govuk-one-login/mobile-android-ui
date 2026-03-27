package uk.gov.android.ui.componentsv2.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalResources
import java.util.Locale

/**
 * Get the [Locale] that is currently active.
 */
@Composable
fun primaryLocale(): Locale =
    LocalResources.current.configuration.locale
