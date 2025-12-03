package uk.gov.android.ui.componentsv2.row

import androidx.compose.ui.Modifier
import uk.gov.android.ui.componentsv2.images.Image

@Suppress("LongParameterList")
data class RowData(
    val title: String,
    val modifier: Modifier = Modifier,
    val leadingImage: Image? = null,
    val scaleLeadingImageWithFontSize: Boolean = false,
    val subtitle: String? = null,
    val trailingText: String? = null,
    val trailingIcon: RowTrailingIcon? = null,
    val clickEnabled: Boolean = true,
    val onClick: () -> Unit,
)
