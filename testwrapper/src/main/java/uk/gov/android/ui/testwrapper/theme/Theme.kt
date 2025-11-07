package uk.gov.android.ui.testwrapper.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.android.ui.testwrapper.TabDestination

@Composable
fun Theme(
    modifier: Modifier = Modifier,
    onNavigate: (Any) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            "Coming soon",
            modifier =
            Modifier.clickable {
                // Navigate to itself for now
                onNavigate(TabDestination.Theme)
            },
        )
    }
}
