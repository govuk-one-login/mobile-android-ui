package uk.gov.ui.components

import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode.SHRINK
import org.junit.Rule
import org.junit.Test

/**
 * Base implementation for tests that utilise the Paparazzi testing library.
 *
 * Implementations of [BaseScreenshotTest] would at minimum override the [generateComposeLayout]
 * property to verify behaviour.
 *
 * Implementations may also use the [org.junit.runners.Parameterized] runner if requiring more than
 * one UI composition for screenshot purposes.
 *
 * @sample IconScreenshotTest
 */
abstract class BaseScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        renderingMode = SHRINK,
        showSystemUi = false
    )

    @Test
    fun testScreenshot() {
        paparazzi.snapshot(composable = generateComposeLayout)
    }

    protected abstract val generateComposeLayout: @Composable () -> Unit
}
