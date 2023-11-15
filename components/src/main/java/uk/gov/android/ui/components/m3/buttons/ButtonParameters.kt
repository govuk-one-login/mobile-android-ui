package uk.gov.android.ui.components.m3.buttons

import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Keep
data class ButtonParameters(
    val buttonType: ButtonType,
    val textStyle: TextStyle? = null,
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    val onClick: () -> Unit,
    @StringRes
    val text: Int
) {
    override fun toString(): String = "GDS Button"
}
