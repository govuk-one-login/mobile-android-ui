package uk.gov.android.ui.componentsv2.button.buttonparameters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import uk.gov.android.ui.componentsv2.button.ButtonIconPreview
import uk.gov.android.ui.componentsv2.button.ButtonTypePreview
import uk.gov.android.ui.componentsv2.button.GdsButtonDefaults

internal data class ButtonParametersV2(
    val text: String,
    val icon: ButtonIconPreview? = null,
    val buttonType: ButtonTypePreview,
    val contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    val textAlign: TextAlign = TextAlign.Center,
    val modifier: Modifier = Modifier,
    val contentModifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val loading: Boolean = false,
    val shape: Shape = GdsButtonDefaults.defaultShape,
) {
    override fun toString(): String = buildString {
        append(buttonType.name)
        if (icon != null) append("_Icon")
        if (!enabled) append("_Disabled")
        if (loading) append("_Loading")
    }
}
