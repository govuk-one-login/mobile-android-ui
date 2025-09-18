package uk.gov.android.ui.componentsv2.status

import android.annotation.SuppressLint
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography

@Composable
fun GdsStatusOverlay(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = hostState,
    ) { snackbarData: SnackbarData ->
        GdsSnackBar(snackbarData.visuals.message)
    }
}

@SuppressLint("ComposeModifierMissing")
@Composable
fun GdsSnackBar(
    message: String,
) {
    Snackbar(
        containerColor = GdsLocalColorScheme.current.statusOverlayBackground,
    ) {
        Text(
            text = message,
            style = Typography.bodySmall.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = GdsLocalColorScheme.current.statusOverlayContent,
        )
    }
}

@PreviewLightDark
@Composable
internal fun PreviewStatusOverlay() {
    GdsTheme {
        GdsSnackBar("Sample text")
    }
}
