package uk.gov.android.ui.theme.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * Use [DefaultPreview] at the root of a Compose preview to include the default
 * [GdsTheme] and a [Surface] container.
 */
@Composable
fun DefaultPreview(modifier: Modifier = Modifier, content: @Composable () -> Unit) = GdsTheme {
    Surface(
        modifier = modifier,
    ) {
        content()
    }
}
