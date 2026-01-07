package uk.gov.android.ui.testwrapper.patterns.loading

import androidx.compose.runtime.Composable
import uk.gov.android.ui.patterns.loadingscreen.LoadingScreen
import uk.gov.android.ui.testwrapper.FullScreenBackHandler
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun LoadingFullScreenDemo(displayTabRow: (Boolean) -> Unit) {
    FullScreenBackHandler(displayTabRow)
    LoadingScreen()
}
