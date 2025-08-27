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
