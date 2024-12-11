package uk.gov.ui.components.navigation

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * Wrapper data class for the [NavigationBar] Composable.
 *
 * - [Developer guide](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#navigationbar)
 * - [Material 3 Design](https://m3.material.io/components/navigation-bar/overview)
 */
data class GdsNavigationBar(
    private val items: List<GdsNavigationItem>,
    private val modifier: Modifier = Modifier,
    private val containerColor: @Composable () -> Color = {
        NavigationBarDefaults.containerColor
    },
    private val contentColor: @Composable () -> Color = {
        MaterialTheme.colorScheme.contentColorFor(containerColor())
    },
    private val tonalElevation: Dp = NavigationBarDefaults.Elevation,
    private val windowInsets: @Composable () -> WindowInsets = {
        NavigationBarDefaults.windowInsets
    },
) {
    /**
     * Converts the [GdsNavigationBar] into a [NavigationBar] Composable.
     */
    val generate: @Composable () -> Unit
        get() = {
            NavigationBar(
                modifier = modifier,
                containerColor = containerColor(),
                contentColor = contentColor(),
                tonalElevation = tonalElevation,
                windowInsets = windowInsets(),
            ) {
                items.forEach { gdsNavigationItem ->
                    gdsNavigationItem.generate(this)
                }
            }
        }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun GdsNavigationBarPreview(
    @PreviewParameter(GdsNavigationBarProvider::class)
    parameters: GdsNavigationBar,
) {
    GdsTheme {
        parameters.generate()
    }
}
