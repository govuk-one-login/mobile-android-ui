package uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters

import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadiosTitle

internal data class GdsRadiosPreviewData(
    val items: ImmutableList<String>,
    val title: GdsRadiosTitle? = null,
    val selectedIndex: Int? = null,
    val focusedIndex: Int? = null,
)

data class GdsRadiosContent(
    val items: ImmutableList<String>,
    val title: GdsRadiosTitle? = null,
    val selectedIndex: Int? = null,
)


internal data class GdsRadioOptionItemPreviewData(
    val text: String,
    val isSelected: Boolean,
    val isFocused: Boolean,
)
