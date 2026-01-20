package uk.gov.android.ui.componentsv2.button.buttonparameters

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import uk.gov.android.ui.componentsv2.button.ButtonTypePreview

internal data class ButtonParameters(
    @StringRes
    val text: Int,
    val buttonType: ButtonTypePreview,
    val modifier: Modifier = Modifier,
    val contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    val contentModifier: Modifier = Modifier,
    val enabled: Boolean = true,
)
