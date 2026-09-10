package uk.gov.android.ui.testwrapper.componentsv2.progress

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.progress.GdsProgressIndicator
import uk.gov.android.ui.patterns.loadingscreen.v2.LoadingScreen
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.GdsThemeDefaults
import uk.gov.android.ui.theme.meta.ContentPreviews

private val govUkBlue = Color(color = 0xFF1D70B8)

@Composable
fun GdsProgressIndicatorCustomThemeDemo(modifier: Modifier = Modifier) = GdsTheme(
    colorScheme = GdsThemeDefaults.colorScheme().copy(
        surface = govUkBlue,
        onSurface = Color.White,
    ),
    extendedColorScheme = GdsThemeDefaults.extendedColorScheme(
        spinnerIcon = Color.White,
    ),
) {
    LoadingScreen(
        modifier = modifier,
    ) {
        GdsProgressIndicator()
    }
}

@Composable
@PreviewLightDark
@ContentPreviews
internal fun GdsProgressIndicatorCustomThemeDemoPreview() = GdsTheme {
    GdsProgressIndicatorCustomThemeDemo()
}
