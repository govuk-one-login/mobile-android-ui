package uk.gov.android.ui.testwrapper.patterns.error

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton
import uk.gov.android.ui.patterns.errorscreen.ErrorScreen
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenIcon
import uk.gov.android.ui.testwrapper.FullScreenBackHandler

@Composable
@Suppress("ComposeModifierMissing")
fun ErrorScreenDeprecatedDemo(
    isFullScreen: Boolean,
    displayTabRow: (Boolean) -> Unit = {},
) {
    if (isFullScreen) {
        FullScreenBackHandler(displayTabRow)
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
