package uk.gov.android.ui.pages.modal.v2

data class ModalDialogParametersV2(
    val title: String? = null,
    val onClose: () -> Unit = {},
)
