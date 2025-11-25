package uk.gov.android.ui.componentsv2.listcomponent

sealed interface ListComponentTrailingIcon {
    class OpenInNew(
        val onClick: () -> Unit,
    ) : ListComponentTrailingIcon

    class NavigateNext(
        val onClick: () -> Unit,
    ) : ListComponentTrailingIcon
}
