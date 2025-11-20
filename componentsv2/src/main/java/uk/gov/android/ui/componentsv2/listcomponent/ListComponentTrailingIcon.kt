package uk.gov.android.ui.componentsv2.listcomponent

sealed class ListComponentTrailingIcon() {
    class OpenInNew(
        onClick: () -> Unit,
    )

    class NavigateNext(
        onClick: () -> Unit,
    )

    class Switch(
        checked: Boolean,
        onToggle: () -> Unit,
    )
}
