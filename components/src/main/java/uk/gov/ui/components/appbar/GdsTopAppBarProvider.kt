package uk.gov.ui.components.appbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class GdsTopAppBarProvider : PreviewParameterProvider<GdsTopAppBar> {
    @OptIn(ExperimentalMaterial3Api::class)
    override val values: Sequence<GdsTopAppBar> = sequenceOf(
        GdsTopAppBar(title = { Text(text = "OneLogin") }),
    )
}
