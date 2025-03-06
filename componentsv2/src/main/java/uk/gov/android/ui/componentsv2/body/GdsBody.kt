package uk.gov.android.ui.componentsv2.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

@Composable
fun GdsBody(
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(spacingDouble),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = arrangement,
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewGdsBody() {
    GdsTheme {
        GdsBody(arrangement = Arrangement.spacedBy(32.dp)) {
            Text("Test1")
            Text("Test1")
            Text("Test1")
            Text("Test1")
        }
    }
}
