package uk.gov.android.ui.patterns.loadingscreen.v2

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class LoadingScreenPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "Loading",
        "Custom loading message that is long enough to wrap onto multiple lines",
    )
}
