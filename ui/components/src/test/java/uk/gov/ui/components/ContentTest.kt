package uk.gov.ui.components

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.content.ContentProvider
import uk.gov.ui.components.content.GdsContent
import uk.gov.ui.theme.GdsTheme

@RunWith(Parameterized::class)
class ContentTest(
    private val contentParameters: ContentParameters
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
                GdsContent(
                    contentParameters
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = ContentProvider().values.toList()
    }
}
