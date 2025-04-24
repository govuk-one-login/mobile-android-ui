package uk.gov.android.ui.componentsv2.list

import kotlinx.collections.immutable.ImmutableList

enum class TitleType {
    BoldText, Heading, Text
}

data class ListTitle(
    val text: String,
    val titleType: TitleType,
)

internal data class ListWrapper(
    val items: ImmutableList<String>,
    val title: ListTitle? = null,
)
