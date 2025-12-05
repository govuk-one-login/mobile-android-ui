package uk.gov.android.ui.componentsv2.row

import androidx.compose.ui.Alignment

sealed interface RowTrailingIcon {
    class OpenInNew(
        val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    ) : RowTrailingIcon

    class NavigateNext(
        val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    ) : RowTrailingIcon
}
