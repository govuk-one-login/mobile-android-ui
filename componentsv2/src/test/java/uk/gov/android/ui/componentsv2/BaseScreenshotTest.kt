package uk.gov.android.ui.componentsv2

import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode.SHRINK
import com.android.resources.NightMode
import com.android.resources.NightMode.NIGHT
import com.android.resources.NightMode.NOTNIGHT
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.theme.m3.GdsTheme

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
abstract class BaseScreenshotTest(
    nightMode: NightMode = NOTNIGHT,
    locale: String? = null,
    fontScale: Float = FONT_SCALE_M,
) {

    constructor(config: Config) : this(
        nightMode = config.nightMode,
        locale = config.locale,
        fontScale = config.fontScale,
    )

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6.copy(
            nightMode = nightMode,
            locale = locale,
            fontScale = fontScale,
        ),
        renderingMode = SHRINK,
        showSystemUi = false,
    )

    @Test
    fun testScreenshot() {
        paparazzi.snapshot {
            GdsTheme {
                generateComposeLayout()
            }
        }
    }

    protected abstract val generateComposeLayout: @Composable () -> Unit

    companion object {
        const val LOCALE_EN = "en"
        const val LOCALE_CY = "cy"

        const val FONT_SCALE_M = 1f
        const val FONT_SCALE_L = 2f

        @JvmStatic
        fun <T : Any> applyNightMode(
            result: MutableList<Pair<T, NightMode>>,
        ): (
            T,
        ) -> Unit = { parameters ->
            result.add(parameters to NOTNIGHT)
            result.add(parameters to NIGHT)
        }

        val defaultConfig = Config("default", NOTNIGHT, LOCALE_EN, FONT_SCALE_M)
        val darkConfig = defaultConfig.copy(name = "dark", nightMode = NIGHT)
        val welshConfig = defaultConfig.copy(name = "welsh", locale = LOCALE_CY)
        val largeFontConfig = defaultConfig.copy(name = "large_font", fontScale = FONT_SCALE_L)

        /**
         * All combinations of parameters for the screenshot test
         */
        val allConfigs: Iterable<Config> = arrayListOf(
            defaultConfig,
            darkConfig,
            welshConfig,
            largeFontConfig,
        )
    }

    data class Config(
        val name: String,
        val nightMode: NightMode,
        val locale: String,
        val fontScale: Float,
    ) {
        override fun toString(): String = name
    }
}
