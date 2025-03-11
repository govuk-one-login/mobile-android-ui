package uk.gov.android.ui.componentsv2.inputs.radio

enum class TitleType {
    BoldText, Heading, Text
}

data class RadioSelectionTitle(
    val text: String,
    val titleType: TitleType,
)
