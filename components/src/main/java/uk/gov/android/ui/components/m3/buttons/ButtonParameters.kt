package uk.gov.android.ui.components.m3.buttons

import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Keep
data class ButtonParameters(
    val buttonType: ButtonType,
    val textStyle: TextStyle? = null,
    val textAlign: TextAlign? = TextAlign.Center,
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    val onClick: () -> Unit,
    @StringRes
    val text: Int,
    val contentPadding: PaddingValues? = null
) {
    override fun toString(): String = "GDS Button"
}
