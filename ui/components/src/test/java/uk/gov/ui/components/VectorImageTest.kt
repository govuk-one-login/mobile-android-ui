package uk.gov.ui.components

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.theme.GdsTheme

@RunWith(Parameterized::class)
class VectorImageTest(
    private val vectorImageParameters: VectorImageParameters
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        renderingMode = SessionParams.RenderingMode.SHRINK,
        showSystemUi = false
    )

    @Test
    fun buttonTest() {
        paparazzi.snapshot {
            GdsTheme {
                GdsVectorImage(
                    vectorImageParameters
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = VectorImageProvider().values.toList()
    }
}
