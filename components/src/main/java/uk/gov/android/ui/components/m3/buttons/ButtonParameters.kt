package uk.gov.android.ui.components.m3.buttons

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

data class ButtonParameters(
    val modifier: Modifier = Modifier,
    val buttonType: ButtonType,
    val textStyle: TextStyle? = null,
    val textAlign: TextAlign = TextAlign.Center,
    val isEnabled: Boolean = true,
    val text: String,
    val onClick: () -> Unit,
) {
    override fun toString(): String = "GDS ${buttonType.javaClass.simpleName.lowercase()} Button(" +
        "isEnabled: $isEnabled, text: $text)"
}
