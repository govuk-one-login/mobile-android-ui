package uk.gov.android.ui.patterns.camera.qr

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode.NORMAL
import com.android.resources.NightMode
import com.android.resources.NightMode.NIGHT
import com.android.resources.NightMode.NOTNIGHT
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.qrScannerOverlay
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.Borders
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.toMappedColors

@RunWith(Parameterized::class)
class QrScannerOverlayScreenshotTest(
    private val nightMode: NightMode,
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6.copy(
            nightMode = nightMode,
        ),
    )

    @Test
    fun qrScannerOverlay() {
        paparazzi.snapshot {
            GdsTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .qrScannerOverlay(
                            overlayTint = Backgrounds.qrScanner.toMappedColors(),
                            qrBorderColor = Borders.qrScanner.toMappedColors(),
                            density = LocalDensity.current,
                        ),
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun values() = listOf(NOTNIGHT, NIGHT)
    }
}
