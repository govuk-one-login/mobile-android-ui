package uk.gov.android.ui.patterns.loadingscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

internal class LoadingScreenContentProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "Loading",
        LoremIpsum(25).values.first()
    )
}
