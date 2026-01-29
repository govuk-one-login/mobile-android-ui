package uk.gov.android.ui.componentsv2.button.buttonparameters

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import uk.gov.android.ui.componentsv2.button.ButtonTypePreview
import uk.gov.android.ui.componentsv2.button.GdsButtonDefaults

internal data class ButtonParametersV2(
    @StringRes
    val text: Int,
    val buttonType: ButtonTypePreview,
    val contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
    val modifier: Modifier? = null,
    val contentModifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val loading: Boolean = false,
    val shape: Shape = GdsButtonDefaults.defaultShape,
)
