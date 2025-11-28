package uk.gov.android.ui.componentsv2.listcomponent

import androidx.compose.ui.Alignment

sealed interface ListComponentTrailingIcon {
    class OpenInNew(
        val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
    ) : ListComponentTrailingIcon

    class NavigateNext(
        val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
    ) : ListComponentTrailingIcon
}
