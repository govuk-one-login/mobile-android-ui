package uk.gov.android.ui.componentsv2.text.annotatedstringparameters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.Typography

internal data class GdsAnnotatedStringPreviewData(
    val text: String = "GdsAnnotatedString",
    val fontWeight: FontWeight = FontWeight.Normal,
    val icon: Int = R.drawable.ic_external_site,
    val iconId: Int = R.string.in_line_icon_id,
    val textStyle: TextStyle = Typography.labelLarge,
    val iconContentDescription: Int = R.string.icon_content_desc,
    val color: Color,
    val iconColor: Color? = null,
    val isIconTrailing: Boolean = true,
    val iconBackgroundColor: Color,
)
