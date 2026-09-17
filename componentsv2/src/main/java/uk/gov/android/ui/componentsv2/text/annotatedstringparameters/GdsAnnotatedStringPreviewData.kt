package uk.gov.android.ui.componentsv2.text.annotatedstringparameters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.Typography

data class GdsAnnotatedStringPreviewData(
    val text: Int,
    val fontWeight: FontWeight,
    val icon: Int,
    val iconId: Int = R.string.in_line_icon_id,
    val textStyle: TextStyle = Typography.labelLarge,
    val iconContentDescription: Int,
    val color: Color = Color.Unspecified,
    val iconColor: Color = Color.Unspecified,
    val iconBackgroundColor: Color = Color.Unspecified,
    val isIconTrailing: Boolean = true,
    val nightMode: Boolean = false,
)
