import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * [Scaffold] wrapper to make enclosed Composable functions compliant with
 * edge to edge requirements
 */
@Composable
@Deprecated(
    message = "This composable has been moved to a more appropriate package. Aim to be removed " +
        "by 05/04/2026",
    replaceWith = ReplaceWith(
        "uk.gov.android.ui.patterns.edgetoedgescreen.EdgeToEdgeWrapperScreen",
    ),
    level = DeprecationLevel.WARNING,
)
fun EdgeToEdgeWrapperScreen(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .navigationBarsPadding()
        .statusBarsPadding()
        .windowInsetsPadding(WindowInsets.displayCutout),
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(modifier = modifier) { paddingValues ->
        content(paddingValues)
    }
}
