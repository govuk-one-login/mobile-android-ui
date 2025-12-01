package uk.gov.android.ui.componentsv2.row

import androidx.compose.ui.Modifier

@Suppress("LongParameterList")
class RowData(
    val title: String,
    val modifier: Modifier = Modifier,
    val leadingImage: uk.gov.android.ui.componentsv2.images.Image? = null,
    val scaleLeadingImageWithFontSize: Boolean = false,
    val subtitle: String? = null,
    val trailingText: String? = null,
    val trailingIcon: RowTrailingIcon? = null,
    val clickEnabled: Boolean = true,
    val onClick: () -> Unit,
)
