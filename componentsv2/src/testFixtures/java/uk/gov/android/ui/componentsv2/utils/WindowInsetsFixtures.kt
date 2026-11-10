package uk.gov.android.ui.componentsv2.utils

import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.WindowInsets
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat

/**
 * Builds a [WindowInsetsCompat] for tests that need to override window insets using
 * [DeviceConfigurationOverride.WindowInsets]
 */
@Suppress("LongParameterList")
fun createWindowInsets(
    statusBarTop: Int = 0,
    navigationBarBottom: Int = 0,
    displayCutoutLeft: Int = 0,
    gestureLeft: Int = 0,
    gestureRight: Int = 0,
    gestureTop: Int = 0,
    gestureBottom: Int = 0,
): WindowInsetsCompat = WindowInsetsCompat.Builder()
    .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, statusBarTop, 0, 0))
    .setInsets(
        WindowInsetsCompat.Type.navigationBars(),
        Insets.of(0, 0, 0, navigationBarBottom),
    )
    .setInsets(WindowInsetsCompat.Type.displayCutout(), Insets.of(displayCutoutLeft, 0, 0, 0))
    .setInsets(
        WindowInsetsCompat.Type.systemGestures(),
        Insets.of(gestureLeft, gestureTop, gestureRight, gestureBottom),
    )
    .build()
