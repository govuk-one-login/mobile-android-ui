package uk.gov.android.ui.componentsv2.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.WindowInsets
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode.SHRINK
import com.android.resources.Density
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class WindowInsetsScreenshotTest(val scenario: Scenario) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig(
            density = Density.MEDIUM,
            screenHeight = 480,
            screenWidth = 320,
        ),
        renderingMode = SHRINK,
        showSystemUi = false,
    )

    @Test
    fun testSafeWindowInsetsModifiers() = paparazzi.snapshot {
        DeviceConfigurationOverride(
            DeviceConfigurationOverride.WindowInsets(
                scenario.windowInsets,
            ),
        ) {
            val screenModifier = Modifier.safeScreenPadding()
            val componentModifier = Modifier.safeComponentPadding(scenario.minHorizontalPadding)

            Content(
                screenModifier = screenModifier,
                componentModifier = componentModifier,
            )
        }
    }

    /**
     * This test documents the correct way to combine [safeScreen] and [safeComponent] it's
     * necessary to resolve the values eagerly (i.e. using [asPaddingValues]).
     *
     * It should only be necessary to do this within the Design System so that we can provide
     * padding values to screen pattern users. For most other use cases, we can use the [Modifier]
     * extensions ([Modifier.safeComponentPadding] and [Modifier.safeScreenPadding]).
     */
    @Test
    fun testSafeWindowInsetsAsPaddingModifiers() = paparazzi.snapshot {
        DeviceConfigurationOverride(
            DeviceConfigurationOverride.WindowInsets(
                scenario.windowInsets,
            ),
        ) {
            val screenModifier =
                Modifier.windowInsetsPadding(WindowInsets.safeScreen.asPaddingValues())
            val componentModifier =
                Modifier.windowInsetsPadding(
                    WindowInsets.safeComponentAsPadding(
                        scenario.minHorizontalPadding,
                    ),
                )

            Content(
                screenModifier = screenModifier,
                componentModifier = componentModifier,
            )
        }
    }

    @SuppressLint("ComposeModifierWithoutDefault", "ModifierParameter")
    @Composable
    private fun Content(screenModifier: Modifier, componentModifier: Modifier) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Cyan),
        ) {
            Box(
                screenModifier
                    .fillMaxSize()
                    .background(Color.Magenta),
            ) {
                Box(
                    componentModifier
                        .fillMaxSize()
                        .background(Color.Green),
                ) {
                    Legend(scenario)
                }
            }
        }
    }

    @Composable
    private fun Legend(scenario: Scenario) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("Min horizontal padding: ${scenario.minHorizontalPadding}", color = Color.White)
            LegendItem(Color.Cyan, "WindowInsets.safeScreen")
            LegendItem(Color.Magenta, "WindowInsets.safeComponent")
            LegendItem(Color.Green, "Content area")
        }
    }

    @Composable
    private fun LegendItem(color: Color, text: String) {
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .background(color)
                .padding(4.dp),
        )
    }

    data class Scenario(val minPaddingExceedsInsets: Boolean) {
        val windowInsets = createWindowInsets(
            statusBarTop = 20,
            navigationBarBottom = 50,
            displayCutoutLeft = 30,
            gestureLeft = 20,
            gestureRight = 20,
        )

        val minHorizontalPadding
            get() = when (minPaddingExceedsInsets) {
                true -> 40.dp
                else -> 20.dp
            }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): Array<Scenario> = arrayOf(
            Scenario(
                minPaddingExceedsInsets = true,
            ),
            Scenario(
                minPaddingExceedsInsets = false,
            ),
        )
    }
}
