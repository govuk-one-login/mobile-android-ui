package uk.gov.android.ui.patterns.lefalignedscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class LeftAlignedContentProvider :
    PreviewParameterProvider<LeftAlignedContent> {
    override val values: Sequence<LeftAlignedContent>
        get() = sequenceOf(
            LeftAlignedContent("Do you have a UK passport or passport with a biometric chip?"),
            LeftAlignedContent("Title 2"),
        )
}

internal data class LeftAlignedContent(
    val title: String,
    val body: String? = null,
    val image: Int? = null,
    val supporting: String? = null,
    val primaryButton: String? = null,
)
