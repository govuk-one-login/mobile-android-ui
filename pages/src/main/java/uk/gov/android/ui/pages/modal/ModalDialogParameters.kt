package uk.gov.android.ui.pages.modal

import androidx.compose.ui.text.AnnotatedString
import uk.gov.android.ui.components.m3.buttons.ButtonType

data class ModalDialogParameters(
    val title: String,
    val header: AnnotatedString,
    val bullets: List<String>,
    val footer: AnnotatedString,
    val buttonParams: ButtonParameters,
    val onClose: () -> Unit = {}
) {
    data class ButtonParameters(
        val buttonType: ButtonType = ButtonType.PRIMARY(),
        val text: String,
        val isEnabled: Boolean = true,
        val onClick: () -> Unit
    )
}
