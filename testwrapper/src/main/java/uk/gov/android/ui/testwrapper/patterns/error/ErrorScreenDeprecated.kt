package uk.gov.android.ui.testwrapper.patterns.error

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton
import uk.gov.android.ui.patterns.errorscreen.ErrorScreen
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenIcon

@Composable
@Suppress("ComposeModifierMissing")
fun ErrorScreenDeprecatedDemo(
    isFullScreen: Boolean,
    displayTabRow: (Boolean) -> Unit = {},
) {
    if (isFullScreen) {
        val onBackPressedDispatcher =
            LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        var backPressHandled by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        BackHandler(enabled = !backPressHandled) {
            backPressHandled = true
            displayTabRow(true)
            coroutineScope.launch {
                awaitFrame()
                onBackPressedDispatcher?.onBackPressed()
                backPressHandled = false
            }
        }
    }
    ErrorScreen(
        icon = ErrorScreenIcon.ErrorIcon,
        title = "This is an Error View title",
        body =
        persistentListOf(
            CentreAlignedScreenBodyContent.Text("Body single line (regular)"),
            CentreAlignedScreenBodyContent.Text("Body single line (bold)", true),
        ),
        primaryButton = CentreAlignedScreenButton(
            text = "Primary button",
            onClick = {},
            showIcon = false,
        ),
    )
}
