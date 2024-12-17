package uk.gov.ui.components.appbar

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * Wrapper data class for the [TopAppBar] Composable.
 *
 * - [Developer guide](https://developer.android.com/Jetpack/compose/components/app-bars)
 * - [Material 3 design](https://m3.material.io/components/top-app-bar/overview)
 */
@OptIn(ExperimentalMaterial3Api::class)
data class GdsTopAppBar
constructor(
    private val title: @Composable () -> Unit,
    private val modifier: Modifier = Modifier,
    private val actions: @Composable RowScope.() -> Unit = {},
    private val colors: @Composable () -> TopAppBarColors = { TopAppBarDefaults.topAppBarColors() },
    private val navigationIcon: @Composable () -> Unit = {},
    private val scrollBehavior: TopAppBarScrollBehavior? = null,
    private val windowInsets: @Composable () -> WindowInsets = { TopAppBarDefaults.windowInsets },
) {
    @OptIn(ExperimentalMaterial3Api::class)
    val generate: @Composable () -> Unit
        get() = {
            CenterAlignedTopAppBar(
                title = title,
                modifier = modifier,
                actions = actions,
                colors = colors(),
                navigationIcon = navigationIcon,
                scrollBehavior = scrollBehavior,
                windowInsets = windowInsets(),
            )
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
fun GdsTopAppBarPreview(
    @PreviewParameter(GdsTopAppBarProvider::class)
    parameters: GdsTopAppBar,
) {
    GdsTheme {
        parameters.generate()
    }
}
