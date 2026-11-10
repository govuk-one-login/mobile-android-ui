package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.WindowInsets
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

class WindowInsetsScreenshotTest : BaseScreenshotTest() {
    override val generateComposeLayout: @Composable (() -> Unit) = {
        DeviceConfigurationOverride(
            DeviceConfigurationOverride.WindowInsets(
                createWindowInsets(
                    statusBarTop = 120,
                    navigationBarBottom = 160,
                    displayCutoutLeft = 20,
                    gestureLeft = 60,
                    gestureRight = 60,
                ),
            ),
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Cyan),
            ) {
                Box(
                    Modifier
                        .safeScreenPadding()
                        .fillMaxSize()
                        .background(Color.Magenta),
                ) {
                    Box(
                        Modifier
                            .safeComponentPadding()
                            .fillMaxSize()
                            .background(Color.Green),
                        contentAlignment = Alignment.Center,
                    ) {
                        Legend()
                    }
                }
            }
        }
    }

    @Composable
    private fun Legend() {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Example showing WindowInsets.safeScreen and WindowInsets.safeComponent",
                color = Color.White,
            )
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
}
