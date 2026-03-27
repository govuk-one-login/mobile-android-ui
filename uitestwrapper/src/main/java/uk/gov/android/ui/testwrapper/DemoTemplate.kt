package uk.gov.android.ui.testwrapper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.android.ui.theme.smallPadding

@Composable
@Suppress("ComposeModifierMissing")
fun DemoTemplate(
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(smallPadding),
    ) {
        content()
    }
}
